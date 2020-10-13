package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.logic.commands.meeting.DeleteCommand;
import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Meeting Command Object corresponding to the command word.
 */
public class MeetingCommandsParser implements GroupCommandsParser {

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
            return new ListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}