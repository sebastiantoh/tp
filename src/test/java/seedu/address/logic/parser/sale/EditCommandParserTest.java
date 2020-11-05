package seedu.address.logic.parser.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITEM_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SALE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIT_PRICE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_NAME_DESC_BALL;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.QUANTITY_DESC_BALL;
import static seedu.address.logic.commands.CommandTestUtil.SALE_DATE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.SALE_DATE_DESC_BALL;
import static seedu.address.logic.commands.CommandTestUtil.SALE_TAG_FRUITS;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_PRICE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.UNIT_PRICE_DESC_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALE_TAG_FRUITS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_APPLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sale.EditCommand;
import seedu.address.logic.commands.sale.EditCommand.EditSaleDescriptor;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.sale.EditSaleDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ITEM_NAME_APPLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_preamble_failure() {
        // existing preamble
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1"
                + INVALID_ITEM_NAME, ItemName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1"
                + INVALID_SALE_DATE, MESSAGE_INVALID_DATETIME); // invalid phone
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1"
                + INVALID_QUANTITY, Quantity.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1"
                + INVALID_UNIT_PRICE, UnitPrice.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1"
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid item name followed by valid quantity
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1" + INVALID_ITEM_NAME
                + QUANTITY_DESC_APPLE, ItemName.MESSAGE_CONSTRAINTS);

        // valid item name followed by invalid item name.
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1" + ITEM_NAME_DESC_APPLE
                + INVALID_ITEM_NAME, ItemName.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Sale} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1" + TAG_DESC_FRIEND
                + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1" + TAG_DESC_FRIEND
                + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1" + TAG_EMPTY
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX.toString() + "1" + INVALID_ITEM_NAME
                + INVALID_SALE_DATE + INVALID_QUANTITY + VALID_UNIT_PRICE_APPLE, ItemName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + " "
                + PREFIX_SALE_CONTACT_INDEX.toString() + targetIndex.getOneBased() + ITEM_NAME_DESC_APPLE
                + SALE_DATE_DESC_BALL + UNIT_PRICE_DESC_APPLE + QUANTITY_DESC_APPLE + SALE_TAG_FRUITS;

        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_APPLE)
                .withDatetimeOfPurchase(VALID_DATE_BALL).withUnitPrice(VALID_UNIT_PRICE_APPLE)
                .withTags(VALID_SALE_TAG_FRUITS).withQuantity(VALID_QUANTITY_APPLE).build();
        EditCommand expectedCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(targetIndex)), descriptor, INDEX_SECOND_ITEM);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased()
                + QUANTITY_DESC_APPLE + UNIT_PRICE_DESC_APPLE;

        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLE)
                .withUnitPrice(VALID_UNIT_PRICE_APPLE).build();
        EditCommand expectedCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(targetIndex)), descriptor, null);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_ITEM;
        List<Index> targetIndexList = new ArrayList<>(Arrays.asList(targetIndex));

        // item name
        String userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + ITEM_NAME_DESC_APPLE;
        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_APPLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndexList, descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // buyer
        userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + " "
                + PREFIX_SALE_CONTACT_INDEX.toString() + INDEX_FIRST_ITEM.getOneBased();
        descriptor = new EditSaleDescriptorBuilder().build();
        expectedCommand = new EditCommand(targetIndexList, descriptor, INDEX_FIRST_ITEM);
        assertParseSuccess(parser, userInput, expectedCommand);

        // datetime of purchase
        userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + DATE_1;
        descriptor = new EditSaleDescriptorBuilder().withDatetimeOfPurchase(VALID_DATE_1).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + QUANTITY_DESC_APPLE;
        descriptor = new EditSaleDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLE).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // unit price
        userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + UNIT_PRICE_DESC_APPLE;
        descriptor = new EditSaleDescriptorBuilder().withUnitPrice(VALID_UNIT_PRICE_APPLE).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + SALE_TAG_FRUITS;
        descriptor = new EditSaleDescriptorBuilder().withTags(VALID_SALE_TAG_FRUITS).build();
        expectedCommand = new EditCommand(targetIndexList, descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + " "
                + PREFIX_SALE_CONTACT_INDEX.toString() + targetIndex.getOneBased() + ITEM_NAME_DESC_BALL
                + ITEM_NAME_DESC_APPLE + SALE_DATE_DESC_APPLE + SALE_DATE_DESC_BALL + UNIT_PRICE_DESC_BALL
                + UNIT_PRICE_DESC_APPLE + QUANTITY_DESC_BALL + QUANTITY_DESC_APPLE + SALE_TAG_FRUITS;

        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_APPLE)
                .withDatetimeOfPurchase(VALID_DATE_BALL).withUnitPrice(VALID_UNIT_PRICE_APPLE)
                .withTags(VALID_SALE_TAG_FRUITS).withQuantity(VALID_QUANTITY_APPLE).build();
        EditCommand expectedCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(targetIndex)), descriptor, INDEX_FIRST_ITEM);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased()
                + INVALID_QUANTITY + QUANTITY_DESC_APPLE;
        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLE).build();
        EditCommand expectedCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(targetIndex)), descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = " " + PREFIX_SALE_INDEX.toString() + targetIndex.getOneBased() + UNIT_PRICE_DESC_APPLE
                + INVALID_QUANTITY + QUANTITY_DESC_APPLE + ITEM_NAME_DESC_BALL;
        descriptor = new EditSaleDescriptorBuilder().withQuantity(VALID_QUANTITY_APPLE)
                .withItemName(VALID_ITEM_NAME_BALL).withUnitPrice(VALID_UNIT_PRICE_APPLE).build();
        expectedCommand = new EditCommand(new ArrayList<>(Arrays.asList(targetIndex)), descriptor, null);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
