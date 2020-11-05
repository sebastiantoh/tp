package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALES_TAG;

import seedu.address.logic.commands.tag.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddCommandParser implements Parser<AddCommand> {

    @Override
    public AddCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_SALES_TAG, PREFIX_CONTACT_TAG);

        if (argMultimap.getValue(PREFIX_CONTACT_TAG).isEmpty()
                && argMultimap.getValue(PREFIX_SALES_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        } else if (argMultimap.getValue(PREFIX_CONTACT_TAG).isPresent()
                && argMultimap.getValue(PREFIX_SALES_TAG).isPresent()) {
            throw new ParseException(AddCommand.MESSAGE_CONFLICT_TYPES);
        }

        String tagName;
        if (argMultimap.getValue(PREFIX_SALES_TAG).isPresent()
                && argMultimap.getValue(PREFIX_SALES_TAG).get().length() > 0) {
            tagName = argMultimap.getValue(PREFIX_SALES_TAG).get();
            return new AddCommand(ParserUtil.parseTag(tagName), false);
        } else if (argMultimap.getValue(PREFIX_CONTACT_TAG).isPresent()
                && argMultimap.getValue(PREFIX_CONTACT_TAG).get().length() > 0) {
            tagName = argMultimap.getValue(PREFIX_CONTACT_TAG).get();
            return new AddCommand(ParserUtil.parseTag(tagName), true);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
}
