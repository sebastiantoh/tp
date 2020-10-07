package seedu.address.model.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

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
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("pencilcase")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("2b pencil")); // alphanumeric characters
        assertTrue(Name.isValidName("Notebook")); // with capital letters
        assertTrue(Name.isValidName("Gussi Classic Dotted Grid Bullet Notebook Journal Hard Cover A5")); // long names
    }
}
