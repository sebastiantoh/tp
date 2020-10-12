package seedu.address.logic.parser.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tag.EditCommand;
import seedu.address.logic.commands.tag.EditCommand.EditTagDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object for Tag.
 */
public class EditCommandParser implements Parser<EditCommand> {

    @Override
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditTagDescriptor editTagDescriptor = new EditTagDescriptor();
        Optional<String> tag = argMultimap.getValue(PREFIX_CONTACT_TAG);
        if (tag.isPresent()) {
            editTagDescriptor.setTagName(ParserUtil.parseTag(tag.get()).getTagName());
        } else {
            throw new ParseException(EditCommand.MESSAGE_MISSING_FIELD);
        }

        return new EditCommand(index, editTagDescriptor);
    }
}
