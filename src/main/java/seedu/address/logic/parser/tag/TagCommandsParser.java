package seedu.address.logic.parser.tag;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.tag.DeleteCommand;
import seedu.address.logic.commands.tag.EditCommand;
import seedu.address.logic.commands.tag.FindCommand;
import seedu.address.logic.commands.tag.ListCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class TagCommandsParser implements GroupCommandsParser {

    public static final List<String> ALL_TAG_COMMAND_WORDS = Arrays.asList(
            EditCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD
    );

    @Override
    public Command parse(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        default:
            return new UnknownCommand(commandWord);
        }
    }
}
