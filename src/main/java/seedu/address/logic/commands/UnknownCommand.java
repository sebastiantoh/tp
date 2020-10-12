package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MOST_SIMILAR_COMMAND;
import static seedu.address.logic.parser.contact.ContactCommandsParser.ALL_CONTACT_COMMAND_WORDS;
import static seedu.address.logic.parser.reminder.ReminderCommandsParser.ALL_REMINDER_COMMAND_WORDS;
import static seedu.address.logic.parser.tag.TagCommandsParser.ALL_TAG_COMMAND_WORDS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.SimilarCommandWords;
import seedu.address.commons.SimilarItems;
import seedu.address.model.Model;

/**
 * Returns an approximate most similar command word to an unknown input if exists.
 */
public class UnknownCommand extends Command {

    private static final double SIMILARITY_THRESHOLD = 0.4;

    private static final Map<String, String> SEARCH_WORDS_TO_COMMAND_WORDS =
            Stream.of(Arrays.asList(ExitCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, PurgeCommand.COMMAND_WORD),
                    ALL_CONTACT_COMMAND_WORDS, ALL_TAG_COMMAND_WORDS, ALL_REMINDER_COMMAND_WORDS)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toMap(x -> String.join("", x.split("\\s+")), x -> x));

    private final String unknownInput;

    /**
     * Creates an UnknownCommand Object with the attribute {@code unknownInput}.
     *
     * @param unknownInput attribute of UnknownCommand class
     */
    public UnknownCommand(String unknownInput) {
        requireNonNull(unknownInput);
        this.unknownInput = unknownInput;
    }

    /**
     * Finds an approximate most similar command word to a user input.
     * If none exists, no suggestion will be given.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult object containing
     *  the approximate most similar command word suggestion or no suggestion
     */
    @Override
    public CommandResult execute(Model model) {
        String inputWithNoWhiteSpace = String.join("", this.unknownInput.split("\\s+"));

        SimilarItems<String> similarCommandWords =
                new SimilarCommandWords(inputWithNoWhiteSpace, SIMILARITY_THRESHOLD);

        similarCommandWords.fillSimilarityMapper(new ArrayList<>(SEARCH_WORDS_TO_COMMAND_WORDS.keySet()));

        String mostSimilarCommandWord = similarCommandWords.getSimilarityMapper()
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .limit(1).collect(Collectors.joining());

        if (mostSimilarCommandWord.isBlank()) {
            return new CommandResult(MESSAGE_UNKNOWN_COMMAND);
        }

        return new CommandResult(String.format(MOST_SIMILAR_COMMAND,
                SEARCH_WORDS_TO_COMMAND_WORDS.get(mostSimilarCommandWord)));
    }
}
