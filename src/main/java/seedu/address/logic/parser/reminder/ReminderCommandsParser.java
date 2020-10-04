package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.reminder.AddCommand;
import seedu.address.logic.commands.reminder.ListCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Reminder Command Object corresponding to the command word.
 */
public class ReminderCommandsParser implements GroupCommandsParser {

    /**
     * Parses the command word and arguments to create the appropriate Command Object for Reminder.
     *
     * @param commandWord
     * @param arguments
     * @return Command Object for Reminder
     * @throws ParseException
     */
    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        //        case DeleteCommand.COMMAND_WORD:
        //            return new DeleteCommandParser().parse(arguments);
        //
        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
