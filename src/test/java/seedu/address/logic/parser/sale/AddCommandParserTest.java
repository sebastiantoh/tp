package seedu.address.logic.parser.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITEM_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIT_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.SALE_DATE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.SALE_TAG_FRUITS;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_PRICE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALE_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALE_TAG_FRUITS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_APPLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sale.AddCommand;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = CONTACT_INDEX_SECOND
                + ITEM_NAME_DESC_APPLE
                + SALE_DATE_DESC_APPLE
                + QUANTITY_DESC_APPLE
                + UNIT_PRICE_DESC_APPLE
                + VALID_SALE_TAG;

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_SALE_TAG_FRUITS));

        AddCommand expectedCommand = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)),
                new ItemName(VALID_ITEM_NAME_APPLE), LocalDateTime.parse(VALID_DATE_APPLE,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                new Quantity(VALID_QUANTITY_APPLE), new UnitPrice(new BigDecimal(VALID_UNIT_PRICE_APPLE)), tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing contact prefix
        assertParseFailure(parser, INDEX_SECOND_ITEM.getOneBased() + ITEM_NAME_DESC_APPLE
                + SALE_DATE_DESC_APPLE + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, CONTACT_INDEX_SECOND + VALID_ITEM_NAME_APPLE + VALID_DATE_APPLE
                + SALE_DATE_DESC_APPLE + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE, expectedMessage);

        // missing item name prefix
        assertParseFailure(parser, CONTACT_INDEX_SECOND + VALID_ITEM_NAME_APPLE + SALE_DATE_DESC_APPLE
                + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE, expectedMessage);

        // missing quantity prefix
        assertParseFailure(parser, CONTACT_INDEX_SECOND + ITEM_NAME_DESC_APPLE + SALE_DATE_DESC_APPLE
                + VALID_QUANTITY_APPLE + UNIT_PRICE_DESC_APPLE, expectedMessage);

        // missing unit price prefix
        assertParseFailure(parser, CONTACT_INDEX_SECOND + ITEM_NAME_DESC_APPLE + SALE_DATE_DESC_APPLE
                + QUANTITY_DESC_APPLE + VALID_UNIT_PRICE_APPLE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, INDEX_SECOND_ITEM.getOneBased() + VALID_ITEM_NAME_APPLE + VALID_DATE_APPLE
                        + VALID_QUANTITY_APPLE + VALID_UNIT_PRICE_APPLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid item name
        assertParseFailure(parser, CONTACT_INDEX_SECOND + INVALID_ITEM_NAME + SALE_DATE_DESC_APPLE
                + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE + SALE_TAG_FRUITS, ItemName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, CONTACT_INDEX_SECOND + ITEM_NAME_DESC_APPLE + INVALID_SALE_DATE
                + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE + SALE_TAG_FRUITS, MESSAGE_INVALID_DATETIME);

        // invalid quantity
        assertParseFailure(parser, CONTACT_INDEX_SECOND + ITEM_NAME_DESC_APPLE + SALE_DATE_DESC_APPLE
                + INVALID_QUANTITY + UNIT_PRICE_DESC_APPLE + SALE_TAG_FRUITS, Quantity.MESSAGE_CONSTRAINTS);

        // invalid unit price
        assertParseFailure(parser, CONTACT_INDEX_SECOND + ITEM_NAME_DESC_APPLE + SALE_DATE_DESC_APPLE
                + QUANTITY_DESC_APPLE + INVALID_UNIT_PRICE + SALE_TAG_FRUITS, UnitPrice.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CONTACT_INDEX_SECOND + ITEM_NAME_DESC_APPLE
                        + SALE_DATE_DESC_APPLE + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE + SALE_TAG_FRUITS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
