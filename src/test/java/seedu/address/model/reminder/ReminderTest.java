package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.Message;

public class ReminderTest {
    private static final LocalDateTime VALID_DATETIME = LocalDateTime.of(2020, 10, 30, 15, 19);
    private static final Message VALID_MESSAGE = new Message("Some dummy message");

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reminder(null, null, null));
        assertThrows(NullPointerException.class, () -> new Reminder(ALICE, null, null));
        assertThrows(NullPointerException.class, () -> new Reminder(null, VALID_MESSAGE, null));
        assertThrows(NullPointerException.class, () -> new Reminder(null, null, VALID_DATETIME));
        assertThrows(NullPointerException.class, () -> new Reminder(ALICE, VALID_MESSAGE, null));
        assertThrows(NullPointerException.class, () -> new Reminder(ALICE, null, VALID_DATETIME));
        assertThrows(NullPointerException.class, () -> new Reminder(null, VALID_MESSAGE, VALID_DATETIME));
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        Reminder reminder = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME);
        assertEquals(reminder, reminder);
    }

    @Test
    public void equals_sameFields_returnsTrue() {
        Reminder reminder1 = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME);
        Reminder reminder2 = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME);
        assertEquals(reminder1, reminder2);
    }

    @Test
    public void equals_sameFieldsWithDifferentCasing_returnsTrue() {
        Reminder reminder1 = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME);
        Reminder reminder2 = new Reminder(ALICE, new Message(VALID_MESSAGE.message.toLowerCase()), VALID_DATETIME);
        assertEquals(reminder1, reminder2);
    }

    @Test
    public void equals_differentFields_returnsFalse() {
        Reminder reminder1 = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME);
        Reminder reminder2 = new Reminder(BOB, VALID_MESSAGE, VALID_DATETIME);
        assertNotEquals(reminder1, reminder2);
    }

    @Test
    public void equals_differentStatus_returnsFalse() {
        Reminder completedReminder = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME, true);
        Reminder pendingReminder = new Reminder(ALICE, VALID_MESSAGE, VALID_DATETIME, false);
        assertNotEquals(completedReminder, pendingReminder);
    }
}
