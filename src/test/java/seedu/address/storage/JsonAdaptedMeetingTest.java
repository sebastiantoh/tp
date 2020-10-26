package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.storage.JsonAdaptedMeeting.DESERIALIZING_DURATION_ERROR_MESSAGE;
import static seedu.address.storage.JsonAdaptedMeeting.INVALID_CONTACT_ID;
import static seedu.address.storage.JsonAdaptedMeeting.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;
import static seedu.address.testutil.person.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;

public class JsonAdaptedMeetingTest {
    private static final String INVALID_DATE_1 = "2020/10/10 10AM";
    private static final String INVALID_DATE_2 = "30/10/2020 12:12";
    private static final String INVALID_DURATION = "30 minutes";
    private static final String INVALID_DURATION_DECIMAL = "PT30.5M";

    private static final Integer VALID_PERSON_ID = ALICE.getId();
    private static final String VALID_MESSAGE = "Lunch";
    private static final String VALID_DATE = "2020-10-30T15:19";
    private static final String VALID_DURATION_MINUTE = "PT1H40M";

    private List<Person> personList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        personList.add(ALICE);
    }

    @Test
    public void toModelType_validMeetingDetails_returnsMeeting() throws Exception {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(MEET_ALICE);
        assertEquals(MEET_ALICE, meeting.toModelType(personList));
    }

    @Test
    public void toModelType_nullPersonId_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(null, VALID_MESSAGE, VALID_DATE, VALID_DURATION_MINUTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(personList));
    }

    @Test
    public void toModelType_invalidPersonId_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting =
                new JsonAdaptedMeeting(-134, VALID_MESSAGE, VALID_DATE, VALID_DURATION_MINUTE);
        String expectedMessage = INVALID_CONTACT_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(personList));
    }

    @Test
    public void toModelType_nullMessage_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_PERSON_ID, null, VALID_DATE,
                VALID_DURATION_MINUTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Message");
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(personList));
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_PERSON_ID, VALID_MESSAGE, null,
                VALID_DURATION_MINUTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Date");
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(personList));
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting1 =
                new JsonAdaptedMeeting(VALID_PERSON_ID, VALID_MESSAGE, INVALID_DATE_1, VALID_DURATION_MINUTE);
        JsonAdaptedMeeting meeting2 =
                new JsonAdaptedMeeting(VALID_PERSON_ID, VALID_MESSAGE, INVALID_DATE_2, VALID_DURATION_MINUTE);
        String expectedMessage = MESSAGE_INVALID_DATETIME;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting1.toModelType(personList));
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting2.toModelType(personList));
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting = new JsonAdaptedMeeting(VALID_PERSON_ID, VALID_MESSAGE, VALID_DATE,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Duration");
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting.toModelType(personList));
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedMeeting meeting1 =
                new JsonAdaptedMeeting(VALID_PERSON_ID, VALID_MESSAGE, VALID_DATE, INVALID_DURATION);
        JsonAdaptedMeeting meeting2 =
                new JsonAdaptedMeeting(VALID_PERSON_ID, VALID_MESSAGE, VALID_DATE, INVALID_DURATION_DECIMAL);
        String expectedMessage = DESERIALIZING_DURATION_ERROR_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting1.toModelType(personList));
        assertThrows(IllegalValueException.class, expectedMessage, () -> meeting2.toModelType(personList));
    }

}
