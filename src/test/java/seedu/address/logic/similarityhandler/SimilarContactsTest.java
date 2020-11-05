package seedu.address.logic.similarityhandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class SimilarContactsTest {

    private final SimilarContacts similarContacts = new SimilarContacts("");

    @Test
    public void constructor_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SimilarContacts(null, 1.0));
        assertThrows(NullPointerException.class, () -> new SimilarContacts(null));
    }

    @Test
    public void constructor_validInput_correctResult() {
        assertDoesNotThrow(() -> new SimilarContacts("", 1.0));
        assertDoesNotThrow(() -> new SimilarContacts(""));
    }

    @Test
    public void getAttributeAsStr_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> similarContacts.getAttributeAsStr(null));
    }

    @Test
    public void getAttributeAsStr_validInput_correctResult() {
        assertEquals(ALICE.getName().fullName, similarContacts.getAttributeAsStr(ALICE));
    }
}
