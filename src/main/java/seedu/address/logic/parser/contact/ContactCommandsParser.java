package seedu.address.logic.parser.contact;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.contact.AddCommand;
import seedu.address.logic.commands.contact.DeleteCommand;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.commands.contact.ListCommand;
import seedu.address.logic.commands.contact.SortCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Contact Command Object corresponding to the command word.
 */
public class ContactCommandsParser implements GroupCommandsParser {

    public static final List<String> ALL_CONTACT_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            EditCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            SortCommand.COMMAND_WORD
    );

    /**
     * Parses the command word and arguments to create the appropriate Command Object for Contact.
     * @param commandWord
     * @param arguments
     * @return Command Object for Contact
     * @throws ParseException
     */
    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        default:
            return new UnknownCommand(commandWord);
        }
    }
}
