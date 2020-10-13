package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;
import static seedu.address.testutil.meeting.TypicalMeetings.PRESENT_PROPOSAL_BENSON;
import static seedu.address.testutil.person.TypicalPersons.ALICE;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;


public class UniqueMeetingListTest {
    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(MEET_ALICE));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(MEET_ALICE);
        assertTrue(uniqueMeetingList.contains(MEET_ALICE));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(MEET_ALICE);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(MEET_ALICE));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(MEET_ALICE));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.remove(MEET_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void removeMeetingsWithContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.removeMeetingsWithContact(null));
    }

    @Test
    public void removeMeetingsWithContact_noMeetingsWithContact_noChange() {
        uniqueMeetingList.removeMeetingsWithContact(ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void removeMeetingsWithContact_contactWithMultipleMeetings_associatedMeetingsRemoved() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        uniqueMeetingList.add(new Meeting(ALICE, "Second meeting with Alice",
                LocalDateTime.of(2021, 10, 30, 10, 19),
                Duration.ofMinutes(60)));

        uniqueMeetingList.removeMeetingsWithContact(ALICE);

        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);

        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        uniqueMeetingList.add(MEET_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        uniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(MEET_ALICE);
        List<Meeting> meetingsList = Collections.singletonList(PRESENT_PROPOSAL_BENSON);
        uniqueMeetingList.setMeetings(meetingsList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(MEET_ALICE, MEET_ALICE);
        assertThrows(DuplicateMeetingException.class, ()
            -> uniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }

}
