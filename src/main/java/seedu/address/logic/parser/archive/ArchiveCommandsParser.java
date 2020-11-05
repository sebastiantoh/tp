package seedu.address.logic.parser.archive;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.archive.AddCommand;
import seedu.address.logic.commands.archive.ListCommand;
import seedu.address.logic.commands.archive.RemoveCommand;
import seedu.address.logic.parser.GroupCommandsParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the command word to create a Archive Command Object corresponding to the command word.
 */
public class ArchiveCommandsParser implements GroupCommandsParser {

    public static final List<String> ALL_ARCHIVE_COMMAND_WORDS = Arrays.asList(
            AddCommand.COMMAND_WORD,
            ListCommand.COMMAND_WORD,
            RemoveCommand.COMMAND_WORD
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

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        default:
            return new UnknownCommand(commandWord);
        }
    }
}
