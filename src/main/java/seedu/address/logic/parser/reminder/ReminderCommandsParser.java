package seedu.address.logic.parser.reminder;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.reminder.AddCommand;
import seedu.address.logic.commands.reminder.DeleteCommand;
import seedu.address.logic.commands.reminder.EditCommand;
import seedu.address.logic.commands.reminder.ListCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Reminder Command Object corresponding to the command word.
 */
public class ReminderCommandsParser implements GroupCommandsParser {

    public static final List<String> ALL_REMINDER_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD
    );

    /**
     * Parses the command word and arguments to create the appropriate Command Object for Reminder.
     *
     * @param commandWord The command to be executed for Reminder.
     * @param arguments   The arguments to be executed with the commandWord.
     * @return Command Object for Reminder.
     * @throws ParseException If the commandWord given is unknown or if the arguments given are invalid.
     */
    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        default:
            return new UnknownCommand(commandWord);
        }
    }
}
