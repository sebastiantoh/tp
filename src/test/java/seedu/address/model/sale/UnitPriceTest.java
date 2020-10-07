package seedu.address.model.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UnitPriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnitPrice(null, null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new UnitPrice(1, 200));
    }

    @Test
    public void isValidUnitPriceString() {
        // null quantity
        assertThrows(NullPointerException.class, () -> UnitPrice.isValidUnitPriceString(null));

        // invalid quantity
        assertFalse(UnitPrice.isValidUnitPriceString("")); // empty string
        assertFalse(UnitPrice.isValidUnitPriceString(" ")); // spaces only
        assertFalse(UnitPrice.isValidUnitPriceString("^")); // only non-numeric characters
        assertFalse(UnitPrice.isValidUnitPriceString("11")); // without . character
        assertFalse(UnitPrice.isValidUnitPriceString("11.1")); // missing one decimal place
        assertFalse(UnitPrice.isValidUnitPriceString("11.1")); // missing one decimal place

        // valid quantity
        assertTrue(UnitPrice.isValidUnitPriceString("1.00")); // numbers only
        assertTrue(UnitPrice.isValidUnitPriceString("543.21")); // zero
    }

    @Test
    public void isValidUnitPrice() {
        // null quantity
        assertThrows(NullPointerException.class, () -> UnitPrice.isValidUnitPrice(null, null));

        // invalid quantity
        assertFalse(UnitPrice.isValidUnitPrice(0, 0)); // sums to zero
        assertFalse(UnitPrice.isValidUnitPrice(1, 100)); // cents is greater than 99
        assertFalse(UnitPrice.isValidUnitPrice(-4, 4)); // dollars is negative
        assertFalse(UnitPrice.isValidUnitPrice(4, -4)); // cents is negative

        // valid quantity
        assertTrue(UnitPrice.isValidUnitPrice(421, 0)); // numbers only
        assertTrue(UnitPrice.isValidUnitPrice(2, 53)); // zero
    }
}
