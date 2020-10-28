package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALES_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tag.DeleteCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object for Tag.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SALES_TAG, PREFIX_CONTACT_TAG);

        if (argMultimap.getValue(PREFIX_CONTACT_TAG).isEmpty()
                && argMultimap.getValue(PREFIX_SALES_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(PREFIX_CONTACT_TAG).isPresent()
                && argMultimap.getValue(PREFIX_SALES_TAG).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        Index index;

        if (argMultimap.getValue(PREFIX_CONTACT_TAG).isPresent()) {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_TAG).get());
            return new DeleteCommand(index, true);
        } else if (argMultimap.getValue(PREFIX_SALES_TAG).isPresent()) {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SALES_TAG).get());
            return new DeleteCommand(index, false);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }
    }
}
