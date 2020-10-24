package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.Message;

public class MeetingTest {
    private static final Message VALID_MESSAGE = new Message("Some dummy message");
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
    public void isConflicting_sameInterval_returnsTrue() {
        Meeting meeting = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting otherMeeting = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertTrue(meeting.isConflicting(otherMeeting));
        assertTrue(otherMeeting.isConflicting(meeting));
    }

    @Test
    public void isConflicting_disjointInterval_returnsFalse() {
        Meeting meeting = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting otherMeeting =
                new Meeting(ALICE, VALID_MESSAGE, LocalDateTime.of(2019, 10, 30, 15, 19), VALID_DURATION);
        assertFalse(meeting.isConflicting(otherMeeting));
        assertFalse(otherMeeting.isConflicting(meeting));
    }

    @Test
    public void isConflicting_oneIntervalContainedInTheOther_returnsTrue() {
        Meeting meeting = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting otherMeeting =
                new Meeting(ALICE, VALID_MESSAGE, LocalDateTime.of(2019, 10, 30, 15, 19), Duration.ofDays(1000));
        assertTrue(meeting.isConflicting(otherMeeting));
        assertTrue(otherMeeting.isConflicting(meeting));
    }

    @Test
    public void isConflicting_backToBackIntervals_returnsFalse() {
        int minutes = 20;
        Meeting meeting = new Meeting(ALICE, VALID_MESSAGE,
                LocalDateTime.of(2019, 10, 30, 15, 19),
                Duration.ofMinutes(minutes));
        Meeting otherMeeting =
                new Meeting(ALICE, VALID_MESSAGE,
                        LocalDateTime.of(2019, 10, 30, 15, 19 + minutes),
                        Duration.ofMinutes(minutes));
        assertFalse(meeting.isConflicting(otherMeeting));
        assertFalse(otherMeeting.isConflicting(meeting));
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
        Meeting meeting2 = new Meeting(ALICE, new Message(VALID_MESSAGE.message.toLowerCase()), VALID_DATETIME,
                VALID_DURATION);
        assertEquals(meeting1, meeting2);
    }

    @Test
    public void equals_differentFields_returnsFalse() {
        Meeting meeting1 = new Meeting(ALICE, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        Meeting meeting2 = new Meeting(BOB, VALID_MESSAGE, VALID_DATETIME, VALID_DURATION);
        assertNotEquals(meeting1, meeting2);
    }
}
