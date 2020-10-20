package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Message(null));
    }

    @Test
    public void constructor_invalidMessage_throwsIllegalArgumentException() {
        String invalidMessage = "";
        assertThrows(IllegalArgumentException.class, () -> new Message(invalidMessage));
    }

    @Test
    public void isValidMessage() {
        // null message
        assertThrows(NullPointerException.class, () -> Message.isValidMessage(null));

        // invalid message
        assertFalse(Message.isValidMessage("")); // empty string
        assertFalse(Message.isValidMessage(" ")); // spaces only
        assertFalse(Message.isValidMessage("^")); // only non-alphanumeric characters
        assertFalse(Message.isValidMessage("call peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Message.isValidMessage("peter jack")); // alphabets only
        assertTrue(Message.isValidMessage("12345")); // numbers only
        assertTrue(Message.isValidMessage("peter the 2nd")); // alphanumeric characters
        assertTrue(Message.isValidMessage("Capital Tan")); // with capital letters
        assertTrue(Message.isValidMessage("David Roger Jackson Ray Jr 2nd")); // long messages
    }

    @Test
    public void equals() {
        String msgWithMixedCase = "nLDSfdkfas";
        assertTrue(new Message(msgWithMixedCase).equals(new Message(msgWithMixedCase.toLowerCase())));
    }
}
