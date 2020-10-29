package seedu.address.logic.parser.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sale.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object for Sale.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SALE_CONTACT_INDEX, PREFIX_SALE_NAME,
                PREFIX_SALE_DATE, PREFIX_SALE_QUANTITY, PREFIX_TAG, PREFIX_SALE_UNIT_PRICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_SALE_CONTACT_INDEX, PREFIX_SALE_NAME, PREFIX_SALE_DATE,
                PREFIX_SALE_QUANTITY, PREFIX_SALE_UNIT_PRICE, PREFIX_TAG)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        List<Index> indexList;

        try {
            indexList = ParserUtil.parseIndexes(argMultimap.getAllValues(PREFIX_SALE_CONTACT_INDEX));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE), pe);
        }

        ItemName itemName = ParserUtil.parseItemName(argMultimap.getValue(PREFIX_SALE_NAME).get());
        LocalDateTime dateOfPurchase = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_SALE_DATE).get());
        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_SALE_QUANTITY).get());
        UnitPrice unitPrice = ParserUtil.parseUnitPrice(argMultimap.getValue(PREFIX_SALE_UNIT_PRICE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new AddCommand(indexList, itemName, dateOfPurchase, quantity, unitPrice, tagList);
    }
}
