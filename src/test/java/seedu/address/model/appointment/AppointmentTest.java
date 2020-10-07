package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
    private static final String VALID_MESSAGE = "Some dummy message";
    private static final LocalDateTime VALID_DATETIME = LocalDateTime.of(2020, 10, 30, 15, 19);
    private static final Duration VALID_DURATION = Duration.ofMinutes(30);

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Appointment(ALICE, null, null, null));
        assertThrows(NullPointerException.class, () -> new Appointment(null, VALID_MESSAGE, null, null));
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, VALID_DATETIME, null));
        assertThrows(NullPointerException.class, () -> new Appointment(null, null, null, VALID_DURATION));
    }

    @Test
    public void constructor_messageWithWhiteSpace_returnsAppointmentWithWhiteSpaceStripped() {
        String message = WHITESPACE + VALID_MESSAGE + WHITESPACE;
        Appointment appointment = new Appointment(ALICE, message, VALID_DATETIME, VALID_DURATION);
        assertEquals(appointment.getMessage(), VALID_MESSAGE);
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        Appointment appointment = new Appointment(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertEquals(appointment, appointment);
    }

    @Test
    public void equals_sameFields_returnsTrue() {
        Appointment appointment1 = new Appointment(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Appointment appointment2 = new Appointment(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertEquals(appointment1, appointment2);
    }

    @Test
    public void equals_sameFieldsWithDifferentCasing_returnsTrue() {
        Appointment appointment1 = new Appointment(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Appointment appointment2 = new Appointment(ALICE, VALID_MESSAGE.toLowerCase(), VALID_DATETIME, VALID_DURATION);
        assertEquals(appointment1, appointment2);
    }

    @Test
    public void equals_differentFields_returnsFalse() {
        Appointment appointment1 = new Appointment(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Appointment appointment2 = new Appointment(BOB, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertNotEquals(appointment1, appointment2);
    }
}
