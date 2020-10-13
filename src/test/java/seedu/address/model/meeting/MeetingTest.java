package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class MeetingTest {
    private static final String VALID_MESSAGE = "Some dummy message";
    private static final LocalDateTime VALID_DATETIME = LocalDateTime.of(2020, 10, 30, 15, 19);
    private static final Duration VALID_DURATION = Duration.ofMinutes(30);

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Meeting(ALICE, null, null, null));
        assertThrows(NullPointerException.class, () -> new Meeting(null, VALID_MESSAGE, null, null));
        assertThrows(NullPointerException.class, () -> new Meeting(null, null, VALID_DATETIME, null));
        assertThrows(NullPointerException.class, () -> new Meeting(null, null, null, VALID_DURATION));
    }

    @Test
    public void constructor_messageWithWhiteSpace_returnsMeetingWithWhiteSpaceStripped() {
        String message = WHITESPACE + VALID_MESSAGE + WHITESPACE;
        Meeting meeting = new Meeting(ALICE, message, VALID_DATETIME, VALID_DURATION);
        assertEquals(meeting.getMessage(), VALID_MESSAGE);
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        Meeting meeting = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertEquals(meeting, meeting);
    }

    @Test
    public void equals_sameFields_returnsTrue() {
        Meeting meeting1 = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting meeting2 = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertEquals(meeting1, meeting2);
    }

    @Test
    public void equals_sameFieldsWithDifferentCasing_returnsTrue() {
        Meeting meeting1 = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting meeting2 = new Meeting(ALICE, VALID_MESSAGE.toLowerCase(), VALID_DATETIME, VALID_DURATION);
        assertEquals(meeting1, meeting2);
    }

    @Test
    public void equals_differentFields_returnsFalse() {
        Meeting meeting1 = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting meeting2 = new Meeting(BOB, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertNotEquals(meeting1, meeting2);
    }
}