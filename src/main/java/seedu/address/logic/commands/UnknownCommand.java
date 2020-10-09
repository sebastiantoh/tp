package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MOST_SIMILAR_COMMAND;
import static seedu.address.logic.parser.contact.ContactCommandsParser.ALL_CONTACT_COMMAND_WORDS;
import static seedu.address.logic.parser.reminder.ReminderCommandsParser.ALL_REMINDER_COMMAND_WORDS;
import static seedu.address.logic.parser.tag.TagCommandsParser.ALL_TAG_COMMAND_WORDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.SimilarCommandWords;
import seedu.address.commons.SimilarItems;

import seedu.address.model.Model;

public class UnknownCommand extends Command {

    private final String unknownInput;

    private static final Map<String,String> SEARCH_TO_COMMAND_WORDS =
            Stream.of(Arrays.asList(ExitCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, PurgeCommand.COMMAND_WORD),
                    ALL_CONTACT_COMMAND_WORDS, ALL_TAG_COMMAND_WORDS, ALL_REMINDER_COMMAND_WORDS)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toMap(x -> String.join("", x.split("\\s+")), x -> x));

    public UnknownCommand(String unknownInput) {
        this.unknownInput = unknownInput;
    }

    @Override
    public CommandResult execute(Model model) {
        SimilarItems<String> similarCommandWords = new SimilarCommandWords(
                String.join("", this.unknownInput.split("\\s+")), 0.65);
//        if (this.unknownInput.split("\\s+").length == 1) {
//            similarCommandWords.fillSimilarityMapper(ALL_COMMAND_WORDS, false);
        similarCommandWords.fillSimilarityMapper(new ArrayList<>(SEARCH_TO_COMMAND_WORDS.keySet()), false);

//        } else {
//            similarCommandWords.fillSimilarityMapper(ALL_COMMAND_WORDS, true);
//        }
        String mostSimilarCommandWord = similarCommandWords.getSimilarityMapper()
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .limit(1).collect(Collectors.joining());

        if (mostSimilarCommandWord.isBlank()) {
            return new CommandResult(MESSAGE_UNKNOWN_COMMAND);
        }

        return new CommandResult(String.format(MOST_SIMILAR_COMMAND, SEARCH_TO_COMMAND_WORDS.get(mostSimilarCommandWord)));
    }
}
