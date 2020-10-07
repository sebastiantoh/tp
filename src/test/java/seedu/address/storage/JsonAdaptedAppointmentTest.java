package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.storage.JsonAdaptedAppointment.DESERIALISING_DURATION_ERROR_MESSAGE;
import static seedu.address.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.MEET_ALICE;
import static seedu.address.testutil.person.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;

public class JsonAdaptedAppointmentTest {
    private static final String INVALID_DATE_1 = "2020/10/10 10AM";
    private static final String INVALID_DATE_2 = "30/10/2020 12:12";
    private static final String INVALID_DURATION = "30 minutes";
    private static final String INVALID_DURATION_DECIMAL = "PT30.5M";

    private static final JsonAdaptedPerson VALID_PERSON = new JsonAdaptedPerson(ALICE);
    private static final String VALID_MESSAGE = "Lunch";
    private static final String VALID_DATE = "2020-10-30T15:19";
    private static final String VALID_DURATION_MINUTE = "PT1H40M";

    @Test
    public void toModelType_validAppointmentDetails_returnsAppointment() throws Exception {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(MEET_ALICE);
        assertEquals(MEET_ALICE, appointment.toModelType());
    }

    @Test
    public void toModelType_nullPerson_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment =
            new JsonAdaptedAppointment(null, VALID_MESSAGE, VALID_DATE, VALID_DURATION_MINUTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullMessage_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PERSON, null, VALID_DATE,
            VALID_DURATION_MINUTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Message");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PERSON, VALID_MESSAGE, null,
            VALID_DURATION_MINUTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Date");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment1 =
            new JsonAdaptedAppointment(VALID_PERSON, VALID_MESSAGE, INVALID_DATE_1, VALID_DURATION_MINUTE);
        JsonAdaptedAppointment appointment2 =
            new JsonAdaptedAppointment(VALID_PERSON, VALID_MESSAGE, INVALID_DATE_2, VALID_DURATION_MINUTE);
        String expectedMessage = MESSAGE_INVALID_DATETIME;
        assertThrows(IllegalValueException.class, expectedMessage, appointment1::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, appointment2::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment = new JsonAdaptedAppointment(VALID_PERSON, VALID_MESSAGE, VALID_DATE,
            null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Duration");
        assertThrows(IllegalValueException.class, expectedMessage, appointment::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedAppointment appointment1 =
            new JsonAdaptedAppointment(VALID_PERSON, VALID_MESSAGE, VALID_DATE, INVALID_DURATION);
        JsonAdaptedAppointment appointment2 =
            new JsonAdaptedAppointment(VALID_PERSON, VALID_MESSAGE, VALID_DATE, INVALID_DURATION_DECIMAL);
        String expectedMessage = DESERIALISING_DURATION_ERROR_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, appointment1::toModelType);
        assertThrows(IllegalValueException.class, expectedMessage, appointment2::toModelType);
    }

}
