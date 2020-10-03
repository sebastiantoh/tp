package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a parser that is able to parse commandWord and arguments to create Command Object.
 */
public interface GroupCommandsParser {

    Command parse(String commandWord, String arguments) throws ParseException;
}
