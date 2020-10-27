package seedu.address.logic.parser.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALES_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tag.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindCommandParser implements Parser<FindCommand> {
    @Override
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_TAG, PREFIX_SALES_TAG, PREFIX_CLIENT);

        if (argMultimap.getValue(PREFIX_CONTACT_TAG).isEmpty()
                && argMultimap.getValue(PREFIX_SALES_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Index index;
        if (argMultimap.getValue(PREFIX_CONTACT_TAG).isPresent()
                && argMultimap.getValue(PREFIX_CONTACT_TAG).get().length() > 0) {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_TAG).get());
            return new FindCommand(index, argMultimap.getValue(PREFIX_CONTACT_TAG).isPresent());
        } else if (argMultimap.getValue(PREFIX_SALES_TAG).isPresent()
                && argMultimap.getValue(PREFIX_SALES_TAG).get().length() > 0) {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SALES_TAG).get());
            return new FindCommand(index, false, argMultimap.getValue(PREFIX_CLIENT).isPresent());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
