package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.contact.AddCommand;
import seedu.address.logic.commands.contact.DeleteCommand;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.contact.FindCommand;
import seedu.address.logic.commands.contact.ListCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class ContactCommandsParser implements GroupCommandsParser {

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

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
