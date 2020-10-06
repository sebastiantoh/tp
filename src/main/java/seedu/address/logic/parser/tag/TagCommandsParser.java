package seedu.address.logic.parser.tag;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.tag.DeleteCommand;
import seedu.address.logic.commands.tag.ListCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

public class TagCommandsParser implements GroupCommandsParser {

    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
       switch (commandWord) {
       case ListCommand.COMMAND_WORD:
           return new ListCommand();
       case DeleteCommand.COMMAND_WORD:
           return new DeleteCommandParser().parse(arguments);
       default:
           throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
       }
    }
}
