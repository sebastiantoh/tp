package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;
import static seedu.address.testutil.meeting.TypicalMeetings.PRESENT_PROPOSAL_BENSON;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.IDA;
import static seedu.address.testutil.reminder.TypicalReminders.CALL_ALICE;
import static seedu.address.testutil.reminder.TypicalReminders.EMAIL_BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.exceptions.ReminderNotFoundException;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void setAddressBook_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBook(null));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void deletePerson_invalidPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deletePerson(null));
    }

    @Test
    public void deletePerson_invalidPerson_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> modelManager.deletePerson(IDA));
    }

    @Test
    public void setPerson_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(null, IDA));
        assertThrows(NullPointerException.class, () -> modelManager.setPerson(IDA, null));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different sortedList -> returns false
        modelManager.updateSortedPersonList((x, y) -> y.getName().fullName.compareTo(x.getName().fullName));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateSortedPersonList(null);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasMeeting(MEET_ALICE));
    }

    @Test
    public void hasMeeting_meetingInAddressBook_returnsTrue() {
        modelManager.addMeeting(MEET_ALICE);
        assertTrue(modelManager.hasMeeting(MEET_ALICE));
    }

    @Test
    public void deleteMeeting_invalidMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteMeeting(null));
    }

    @Test
    public void deleteMeeting_invalidMeeting_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> modelManager.deleteMeeting(MEET_ALICE));
    }

    @Test
    public void hasReminder_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasReminder(null));
    }

    @Test
    public void hasReminder_reminderNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasReminder(CALL_ALICE));
    }

    @Test
    public void hasReminder_reminderInAddressBook_returnsTrue() {
        modelManager.addReminder(CALL_ALICE);
        assertTrue(modelManager.hasReminder(CALL_ALICE));
    }

    @Test
    public void deleteReminder_invalidReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.deleteReminder(null));
    }

    @Test
    public void deleteReminder_invalidReminder_throwsReminderNotFoundException() {
        assertThrows(ReminderNotFoundException.class, () -> modelManager.deleteReminder(CALL_ALICE));
    }

    @Test
    public void setReminder_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setReminder(null, null));
        assertThrows(NullPointerException.class, () -> modelManager.setReminder(null, CALL_ALICE));
        assertThrows(NullPointerException.class, () -> modelManager.setReminder(CALL_ALICE, null));
    }


    @Test
    public void getSortedReminderList_reminderWithEarlierDateAdded_meetingInSortedOrder() {
        modelManager.addReminder(CALL_ALICE);
        modelManager.addReminder(EMAIL_BENSON);

        ObservableList<Reminder> reminderList = modelManager.getSortedReminderList();

        assertEquals(reminderList.get(0), EMAIL_BENSON);
        assertEquals(reminderList.get(1), CALL_ALICE);
    }

    @Test
    public void getSortedMeetingList_meetingWithEarlierDateAdded_meetingInSortedOrder() {
        modelManager.addMeeting(MEET_ALICE);
        modelManager.addMeeting(PRESENT_PROPOSAL_BENSON);

        ObservableList<Meeting> meetingList = modelManager.getSortedMeetingList();

        assertEquals(meetingList.get(0), PRESENT_PROPOSAL_BENSON);
        assertEquals(meetingList.get(1), MEET_ALICE);
    }

    @Test
    public void getMonthMeetingsCount_valid_success() {
        assertTrue(modelManager.getMonthMeetingsCount(Month.APRIL, Year.now()) >= 0);
    }
}
