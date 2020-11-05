package seedu.address.model.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class UnitPriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnitPrice(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new UnitPrice(new BigDecimal("-1.43")));
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
        assertFalse(UnitPrice.isValidUnitPriceString("-1.00")); // negative unit price
        assertFalse(UnitPrice.isValidUnitPriceString("-0.00")); // negative zero
        assertFalse(UnitPrice.isValidUnitPriceString("0.00")); // zero
        assertFalse(UnitPrice.isValidUnitPriceString("10000000.00")); // ten million
        assertFalse(UnitPrice.isValidUnitPriceString("10000000.01")); // ten million

        // valid quantity
        assertTrue(UnitPrice.isValidUnitPriceString("0.01")); // 1 cent
        assertTrue(UnitPrice.isValidUnitPriceString("1.00")); // 1 dollar
        assertTrue(UnitPrice.isValidUnitPriceString("543.21")); // numbers only
        assertTrue(UnitPrice.isValidUnitPriceString("9999999.99")); // ten million minus 1 cent
    }

    @Test
    public void isValidUnitPrice() {
        // null quantity
        assertThrows(NullPointerException.class, () -> UnitPrice.isValidUnitPrice(null));

        // invalid quantity
        assertFalse(UnitPrice.isValidUnitPrice(new BigDecimal("1.999"))); // cents is greater than 99
        assertFalse(UnitPrice.isValidUnitPrice(new BigDecimal("-1.00"))); // dollars is negative
        assertFalse(UnitPrice.isValidUnitPrice(new BigDecimal("0.00"))); // zero
        assertFalse(UnitPrice.isValidUnitPrice(new BigDecimal("10000000"))); // ten million

        // valid quantity
        assertTrue(UnitPrice.isValidUnitPrice(new BigDecimal("0.01")));
        assertTrue(UnitPrice.isValidUnitPrice(new BigDecimal("2.53")));
        assertTrue(UnitPrice.isValidUnitPrice(new BigDecimal("9999999.99")));
    }
}
