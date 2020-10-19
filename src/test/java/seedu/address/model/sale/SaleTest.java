package seedu.address.model.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_BALL;
import static seedu.address.testutil.sale.TypicalSales.APPLE;
import static seedu.address.testutil.sale.TypicalSales.BALL;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.sale.SaleBuilder;

public class SaleTest {

    @Test
    public void isSameSale() {
        // same object -> returns true
        assertTrue(APPLE.isSameSale(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameSale(null));

        // different quantity and unit price -> returns true
        Sale editedApple = new SaleBuilder(APPLE).withItemName(VALID_ITEM_NAME_BALL).build();
        assertFalse(APPLE.isSameSale(editedApple));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Sale appleCopy = new SaleBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different sale -> returns false
        assertFalse(APPLE.equals(BALL));

        // different name -> returns false
        Sale editedApple = new SaleBuilder(APPLE).withItemName(VALID_ITEM_NAME_BALL).build();
        assertFalse(APPLE.equals(editedApple));

        // different quantity -> returns false
        editedApple = new SaleBuilder(APPLE).withQuantity(VALID_QUANTITY_BALL).build();
        assertFalse(APPLE.equals(editedApple));

        // different unit price -> returns false
        editedApple = new SaleBuilder(APPLE).withUnitPrice(new BigDecimal(VALID_UNIT_PRICE_BALL)).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
