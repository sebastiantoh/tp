package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;
import static seedu.address.testutil.meeting.TypicalMeetings.PRESENT_PROPOSAL_BENSON;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.FIONA;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Message;
import seedu.address.model.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.testutil.person.PersonBuilder;


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
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        int oldMonthMeetingsCount = uniqueMeetingList.getMonthMeetingsCount(month, year);
        uniqueMeetingList.add(MEET_ALICE);
        assertTrue(uniqueMeetingList.contains(MEET_ALICE));
        assertEquals(oldMonthMeetingsCount + 1, uniqueMeetingList.getMonthMeetingsCount(month, year));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        uniqueMeetingList.add(MEET_ALICE);
        int oldMonthMeetingsCount = uniqueMeetingList.getMonthMeetingsCount(month, year);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(MEET_ALICE));
        assertEquals(oldMonthMeetingsCount, uniqueMeetingList.getMonthMeetingsCount(month, year));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        int oldMonthMeetingsCount = uniqueMeetingList.getMonthMeetingsCount(month, year);
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(MEET_ALICE));
        assertEquals(oldMonthMeetingsCount, uniqueMeetingList.getMonthMeetingsCount(month, year));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        int oldMonthMeetingsCount = uniqueMeetingList.getMonthMeetingsCount(month, year);
        uniqueMeetingList.add(MEET_ALICE);
        assertEquals(oldMonthMeetingsCount + 1, uniqueMeetingList.getMonthMeetingsCount(month, year));
        uniqueMeetingList.remove(MEET_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
        assertEquals(oldMonthMeetingsCount, uniqueMeetingList.getMonthMeetingsCount(month, year));
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
        uniqueMeetingList.add(new Meeting(ALICE, new Message("Second meeting with Alice"),
                LocalDateTime.of(2021, 10, 30, 10, 19),
                Duration.ofMinutes(60)));

        uniqueMeetingList.removeMeetingsWithContact(ALICE);

        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);

        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void updateMeetingsWithContact_contactWithMultipleMeetings_associatedMeetingsUpdated() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        Meeting secondMeetingWithAlice = new Meeting(ALICE, new Message("Second meeting with Alice"),
                LocalDateTime.of(2021, 10, 30, 10, 19),
                Duration.ofMinutes(60));
        uniqueMeetingList.add(secondMeetingWithAlice);

        Person aliceRenamedToAlicia = new PersonBuilder(ALICE).withName("Alicia Pauline").build();
        uniqueMeetingList.updateMeetingsWithContact(aliceRenamedToAlicia);

        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        Meeting meetAlicia = new Meeting(aliceRenamedToAlicia, MEET_ALICE.getMessage(), MEET_ALICE.getStartDate(),
                MEET_ALICE.getDuration());
        expectedUniqueMeetingList.add(meetAlicia);
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        Meeting secondMeetingWithAlicia =
                new Meeting(aliceRenamedToAlicia, secondMeetingWithAlice.getMessage(),
                        secondMeetingWithAlice.getStartDate(),
                        secondMeetingWithAlice.getDuration());
        expectedUniqueMeetingList.add(secondMeetingWithAlicia);

        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void updateMeetingsWithContact_contactNotAssociatedWithReminders_noChange() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);

        uniqueMeetingList.updateMeetingsWithContact(FIONA);

        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEET_ALICE);
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);

        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, MEET_ALICE));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(MEET_ALICE, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(MEET_ALICE, MEET_ALICE));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.setMeeting(MEET_ALICE, MEET_ALICE);

        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEET_ALICE);

        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingIsDifferent_success() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.setMeeting(MEET_ALICE, PRESENT_PROPOSAL_BENSON);

        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);

        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingExists_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(MEET_ALICE);
        uniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);

        assertThrows(DuplicateMeetingException.class, () ->
                uniqueMeetingList.setMeeting(MEET_ALICE, PRESENT_PROPOSAL_BENSON));
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());

        Month month1 = PRESENT_PROPOSAL_BENSON.getStartDate().getMonth();
        Year year1 = Year.of(PRESENT_PROPOSAL_BENSON.getStartDate().getYear());

        uniqueMeetingList.add(MEET_ALICE);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        uniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
        assertEquals(0, uniqueMeetingList.getMonthMeetingsCount(month, year));
        assertEquals(1, uniqueMeetingList.getMonthMeetingsCount(month1, year1));
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());

        Month month1 = PRESENT_PROPOSAL_BENSON.getStartDate().getMonth();
        Year year1 = Year.of(PRESENT_PROPOSAL_BENSON.getStartDate().getYear());

        uniqueMeetingList.add(MEET_ALICE);
        List<Meeting> meetingsList = Collections.singletonList(PRESENT_PROPOSAL_BENSON);
        uniqueMeetingList.setMeetings(meetingsList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(PRESENT_PROPOSAL_BENSON);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
        assertEquals(0, uniqueMeetingList.getMonthMeetingsCount(month, year));
        assertEquals(1, uniqueMeetingList.getMonthMeetingsCount(month1, year1));
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
