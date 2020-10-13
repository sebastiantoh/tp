package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedSale.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.sale.TypicalSales.BALL;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.UnitPrice;

public class JsonAdaptedSaleTest {
    private static final String INVALID_ITEM_NAME = "@pple";
    private static final String INVALID_QUANTITY = "+6";
    private static final Integer INVALID_UNIT_PRICE_DOLLAR = -1;
    private static final Integer INVALID_UNIT_PRICE_CENT = 284;

    private static final String VALID_ITEM_NAME = BALL.getItemName().toString();
    private static final String VALID_QUANTITY = BALL.getQuantity().toString();
    private static final Integer VALID_UNIT_PRICE_DOLLAR = BALL.getUnitPrice().dollars;
    private static final Integer VALID_UNIT_PRICE_CENT = BALL.getUnitPrice().cents;
    private static final List<JsonAdaptedTag> VALID_TAGS = BALL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validSaleDetails_returnsSale() throws Exception {
        JsonAdaptedSale sale = new JsonAdaptedSale(BALL);
        assertEquals(BALL, sale.toModelType());
    }

    @Test
    public void toModelType_invalidItemName_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(INVALID_ITEM_NAME,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE_DOLLAR,
                        VALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_nullItemName_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(null,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE_DOLLAR,
                        VALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        INVALID_QUANTITY,
                        VALID_UNIT_PRICE_DOLLAR,
                        VALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        null,
                        VALID_UNIT_PRICE_DOLLAR,
                        VALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_invalidUnitPriceDollar_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_QUANTITY,
                        INVALID_UNIT_PRICE_DOLLAR,
                        VALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = UnitPrice.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_nullUnitPriceDollar_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_QUANTITY,
                        null,
                        VALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_invalidUnitPriceCent_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE_DOLLAR,
                        INVALID_UNIT_PRICE_CENT,
                        VALID_TAGS);
        String expectedMessage = UnitPrice.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }

    @Test
    public void toModelType_nullUnitPriceCent_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE_DOLLAR,
                        null,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, sale::toModelType);
    }
}
