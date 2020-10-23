package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.storage.JsonAdaptedReminder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.reminder.TypicalReminders.CALL_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;

public class JsonAdaptedReminderTest {
    private static final String INVALID_DATE_1 = "2020/10/10 10AM";
    private static final String INVALID_DATE_2 = "30/10/2020 12:12";

    private static final JsonAdaptedPerson VALID_PERSON = new JsonAdaptedPerson(ALICE);
    private static final String VALID_MESSAGE = "Call Alice";
    private static final String VALID_DATE = "2020-10-30T15:19";

    @Test
    public void toModelType_validReminderDetails_returnsReminder() throws Exception {
        JsonAdaptedReminder reminder = new JsonAdaptedReminder(CALL_ALICE);
        assertEquals(CALL_ALICE, reminder.toModelType());
    }

    @Test
    public void toModelType_nullPerson_throwsIllegalValueException() {
        JsonAdaptedReminder reminder =
            new JsonAdaptedReminder(null, VALID_MESSAGE, VALID_DATE, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }

    @Test
    public void toModelType_nullMessage_throwsIllegalValueException() {
        JsonAdaptedReminder reminder =
            new JsonAdaptedReminder(VALID_PERSON, null, VALID_DATE, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Message");
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedReminder reminder =
            new JsonAdaptedReminder(VALID_PERSON, VALID_MESSAGE, null, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "DateTime");
        assertThrows(IllegalValueException.class, expectedMessage, reminder::toModelType);
    }

    @Test
    public void toModelType_nullStatus_defaultsToPending() throws IllegalValueException {
        JsonAdaptedReminder reminder =
                new JsonAdaptedReminder(VALID_PERSON, VALID_MESSAGE, VALID_DATE, null);
        assertFalse(reminder.toModelType().isCompleted());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedReminder reminder1 =
            new JsonAdaptedReminder(VALID_PERSON, VALID_MESSAGE, INVALID_DATE_1, false);
        JsonAdaptedReminder reminder2 =
            new JsonAdaptedReminder(VALID_PERSON, VALID_MESSAGE, INVALID_DATE_2, false);
        String expectedMessage = MESSAGE_INVALID_DATETIME;
        assertThrows(IllegalValueException.class, expectedMessage, reminder1::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, reminder2::toModelType);
    }
}
