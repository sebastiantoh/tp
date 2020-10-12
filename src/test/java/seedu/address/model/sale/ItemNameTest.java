package seedu.address.model.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ItemNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));
    }

    @Test
    public void constructor_invalidItemName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemName(invalidName));
    }

    @Test
    public void isValidItemName() {
        // null name
        assertThrows(NullPointerException.class, () -> ItemName.isValidItemName(null));

        // invalid name
        assertFalse(ItemName.isValidItemName("")); // empty string
        assertFalse(ItemName.isValidItemName(" ")); // spaces only
        assertFalse(ItemName.isValidItemName("^")); // only non-alphanumeric characters
        assertFalse(ItemName.isValidItemName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ItemName.isValidItemName("pencilcase")); // alphabets only
        assertTrue(ItemName.isValidItemName("12345")); // numbers only
        assertTrue(ItemName.isValidItemName("2b pencil")); // alphanumeric characters
        assertTrue(ItemName.isValidItemName("Notebook")); // with capital letters
        assertTrue(ItemName.isValidItemName(
                "Gussi Classic Dotted Grid Bullet Notebook Journal Hard Cover A5")); // long names
    }

    @Test
    public void equals() {
        assertTrue(new ItemName("Apple").equals(new ItemName("Apple")));
        assertTrue(new ItemName("APPLE").equals(new ItemName("apple")));
        assertFalse(new ItemName("APPLE").equals(new ItemName("ball")));
    }
}
