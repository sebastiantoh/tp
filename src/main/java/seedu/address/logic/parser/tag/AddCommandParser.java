package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.tag.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class AddCommandParser implements Parser<AddCommand> {

    @Override
    public AddCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_SALE, PREFIX_CONTACT, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        }

        String tagName = argMultimap.getValue(PREFIX_TAG).get();

        if (tagName.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        }

        Tag tag = new Tag(tagName);

        if (!argMultimap.getValue(PREFIX_CONTACT).isEmpty() && !argMultimap.getValue(PREFIX_SALE).isEmpty()) {
            throw new ParseException(AddCommand.MESSAGE_CONFLICT_TYPES);
        } else if (!argMultimap.getValue(PREFIX_SALE).isEmpty()) {
            return new AddCommand(tag, false);
        } else if (!argMultimap.getValue(PREFIX_CONTACT).isEmpty()) {
            return new AddCommand(tag, true);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
    }
}
