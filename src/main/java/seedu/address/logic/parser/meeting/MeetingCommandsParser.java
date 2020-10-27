package seedu.address.logic.parser.meeting;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.logic.commands.meeting.DeleteCommand;
import seedu.address.logic.commands.meeting.EditCommand;
import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.commands.meeting.StatsCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Meeting Command Object corresponding to the command word.
 */
public class MeetingCommandsParser implements GroupCommandsParser {

    public static final List<String> ALL_MEETING_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            StatsCommand.COMMAND_WORD
    );

    /**
     * Parses the command word and arguments to create the appropriate Command Object for Meeting.
     *
     * @param commandWord The command to be executed for Meeting.
     * @param arguments   The arguments to be executed with the commandWord.
     * @return Command Object for Meeting.
     * @throws ParseException If the commandWord given is unknown or if the arguments given are invalid.
     */
    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case StatsCommand.COMMAND_WORD:
            return new StatsCommandParser().parse(arguments);

        default:
            return new UnknownCommand(commandWord);
        }
    }
}
