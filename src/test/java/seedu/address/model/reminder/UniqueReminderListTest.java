package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.FIONA;
import static seedu.address.testutil.reminder.TypicalReminders.CALL_ALICE;
import static seedu.address.testutil.reminder.TypicalReminders.EMAIL_BENSON;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Message;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.exceptions.DuplicateReminderException;
import seedu.address.model.reminder.exceptions.ReminderNotFoundException;
import seedu.address.testutil.person.PersonBuilder;

public class UniqueReminderListTest {
    private final UniqueReminderList uniqueReminderList = new UniqueReminderList();

    @Test
    public void contains_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.contains(null));
    }

    @Test
    public void contains_reminderNotInList_returnsFalse() {
        assertFalse(uniqueReminderList.contains(CALL_ALICE));
    }

    @Test
    public void contains_reminderInList_returnsTrue() {
        uniqueReminderList.add(CALL_ALICE);
        assertTrue(uniqueReminderList.contains(CALL_ALICE));
    }

    @Test
    public void add_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.add(null));
    }

    @Test
    public void add_duplicateReminder_throwsDuplicateReminderException() {
        uniqueReminderList.add(CALL_ALICE);
        assertThrows(DuplicateReminderException.class, () -> uniqueReminderList.add(CALL_ALICE));
    }

    @Test
    public void remove_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.remove(null));
    }

    @Test
    public void remove_reminderDoesNotExist_throwsReminderNotFoundException() {
        assertThrows(ReminderNotFoundException.class, () -> uniqueReminderList.remove(CALL_ALICE));
    }

    @Test
    public void remove_existingReminder_removesReminder() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.remove(CALL_ALICE);
        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void removeRemindersWithContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.removeRemindersWithContact(null));
    }

    @Test
    public void removeRemindersWithContact_noRemindersWithContact_noChange() {
        uniqueReminderList.removeRemindersWithContact(ALICE);
        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void removeRemindersWithContact_contactWithMultipleReminders_associatedRemindersRemoved() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.add(EMAIL_BENSON);
        uniqueReminderList.add(new Reminder(ALICE, new Message("Second reminder with Alice"),
                LocalDateTime.of(2021, 10, 30, 10, 19)));

        uniqueReminderList.removeRemindersWithContact(ALICE);

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        expectedUniqueReminderList.add(EMAIL_BENSON);

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void updateRemindersWithContact_contactWithMultipleReminders_associatedRemindersUpdated() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.add(EMAIL_BENSON);
        Reminder secondReminderWithAlice = new Reminder(ALICE, new Message("Second reminder with Alice"),
                LocalDateTime.of(2021, 10, 30, 10, 19));
        uniqueReminderList.add(secondReminderWithAlice);

        Person aliceRenamedToAlicia = new PersonBuilder(ALICE).withName("Alicia Pauline").build();
        uniqueReminderList.updateRemindersWithContact(aliceRenamedToAlicia);

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        Reminder callAlicia =
                new Reminder(aliceRenamedToAlicia, CALL_ALICE.getMessage(), CALL_ALICE.getScheduledDate());
        expectedUniqueReminderList.add(callAlicia);
        expectedUniqueReminderList.add(EMAIL_BENSON);
        Reminder secondReminderWithAlicia =
                new Reminder(aliceRenamedToAlicia, secondReminderWithAlice.getMessage(),
                        secondReminderWithAlice.getScheduledDate());
        expectedUniqueReminderList.add(secondReminderWithAlicia);

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void updateRemindersWithContact_contactNotAssociatedWithReminders_noChange() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.add(EMAIL_BENSON);

        uniqueReminderList.updateRemindersWithContact(FIONA);

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        expectedUniqueReminderList.add(CALL_ALICE);
        expectedUniqueReminderList.add(EMAIL_BENSON);

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }


    @Test
    public void setReminder_nullTargetReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.setReminder(null, CALL_ALICE));
    }

    @Test
    public void setReminder_nullEditedReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.setReminder(CALL_ALICE, null));
    }

    @Test
    public void setReminder_targetReminderNotInList_throwsReminderNotFoundException() {
        assertThrows(ReminderNotFoundException.class, () -> uniqueReminderList.setReminder(CALL_ALICE, CALL_ALICE));
    }

    @Test
    public void setReminder_editedReminderIsSameReminder_success() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.setReminder(CALL_ALICE, CALL_ALICE);

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        expectedUniqueReminderList.add(CALL_ALICE);

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void setReminder_editedReminderIsDifferent_success() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.setReminder(CALL_ALICE, EMAIL_BENSON);

        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        expectedUniqueReminderList.add(EMAIL_BENSON);

        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void setReminder_editedReminderExists_throwsDuplicateReminderException() {
        uniqueReminderList.add(CALL_ALICE);
        uniqueReminderList.add(EMAIL_BENSON);

        assertThrows(DuplicateReminderException.class, () -> uniqueReminderList.setReminder(CALL_ALICE, EMAIL_BENSON));
    }

    @Test
    public void setReminders_nullUniqueReminderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.setReminders((UniqueReminderList) null));
    }

    @Test
    public void setReminders_uniqueReminderList_replacesOwnListWithProvidedUniqueReminderList() {
        uniqueReminderList.add(CALL_ALICE);
        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        expectedUniqueReminderList.add(EMAIL_BENSON);
        uniqueReminderList.setReminders(expectedUniqueReminderList);
        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void setReminders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueReminderList.setReminders((List<Reminder>) null));
    }

    @Test
    public void setReminders_list_replacesOwnListWithProvidedList() {
        uniqueReminderList.add(CALL_ALICE);
        List<Reminder> reminderList = Collections.singletonList(EMAIL_BENSON);
        uniqueReminderList.setReminders(reminderList);
        UniqueReminderList expectedUniqueReminderList = new UniqueReminderList();
        expectedUniqueReminderList.add(EMAIL_BENSON);
        assertEquals(expectedUniqueReminderList, uniqueReminderList);
    }

    @Test
    public void setReminders_listWithDuplicateReminders_throwsDuplicateReminderException() {
        List<Reminder> listWithDuplicateReminders = Arrays.asList(CALL_ALICE, CALL_ALICE);
        assertThrows(DuplicateReminderException.class, ()
            -> uniqueReminderList.setReminders(listWithDuplicateReminders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueReminderList.asUnmodifiableObservableList().remove(0));
    }

}
