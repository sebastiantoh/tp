package seedu.address.logic.parser.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sale.EditCommand;
import seedu.address.logic.commands.sale.EditCommand.EditSaleDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object for Sale
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SALE_INDEX, PREFIX_SALE_CONTACT_INDEX, PREFIX_SALE_NAME,
                        PREFIX_SALE_DATE, PREFIX_SALE_QUANTITY, PREFIX_TAG, PREFIX_SALE_UNIT_PRICE);

        List<Index> saleIndexes;
        Index personIndex = null;

        if (!arePrefixesPresent(argMultimap, PREFIX_SALE_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        try {
            saleIndexes = ParserUtil.parseIndexes(argMultimap.getAllValues(PREFIX_SALE_INDEX));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditSaleDescriptor editSaleDescriptor = new EditSaleDescriptor();
        if (argMultimap.getValue(PREFIX_SALE_NAME).isPresent()) {
            editSaleDescriptor.setItemName(ParserUtil.parseItemName(argMultimap.getValue(PREFIX_SALE_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).isPresent()) {
            personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).get());
        }
        if (argMultimap.getValue(PREFIX_SALE_DATE).isPresent()) {
            editSaleDescriptor.setDatetimeOfPurchase(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_SALE_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_SALE_QUANTITY).isPresent()) {
            editSaleDescriptor.setQuantity(
                    ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_SALE_QUANTITY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editSaleDescriptor::setTags);
        if (argMultimap.getValue(PREFIX_SALE_UNIT_PRICE).isPresent()) {
            editSaleDescriptor.setUnitPrice(
                    ParserUtil.parseUnitPrice(argMultimap.getValue(PREFIX_SALE_UNIT_PRICE).get()));
        }

        if (!editSaleDescriptor.isAnyFieldEdited() && !argMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).isPresent()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(saleIndexes, editSaleDescriptor, personIndex);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        // No prefix with no tags specified
        if (tags.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ParserUtil.parseTags(tags));
    }

}
