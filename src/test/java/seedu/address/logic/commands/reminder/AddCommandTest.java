package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.TYPICAL_DATE_1;
import static seedu.address.testutil.TypicalDates.TYPICAL_DATE_2;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Message;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithSortedPersonList;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.person.PersonBuilder;

public class AddCommandTest {
    private static final Message MESSAGE = new Message("message");

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, MESSAGE, TYPICAL_DATE_1));
    }

    @Test
    public void constructor_nullMessage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(INDEX_FIRST_ITEM, null,
                TYPICAL_DATE_1));
    }

    @Test
    public void constructor_nullScheduledDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(INDEX_FIRST_ITEM, MESSAGE,
                null));
    }

    @Test
    public void execute_reminderAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingReminderAdded modelStub = new ModelStubAcceptingReminderAdded();
        Person validPerson = new PersonBuilder(BOB).build();

        Reminder reminder = new Reminder(validPerson, MESSAGE, TYPICAL_DATE_1);
        CommandResult commandResult =
                new AddCommand(INDEX_FIRST_ITEM, MESSAGE, TYPICAL_DATE_1).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, reminder),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(reminder), modelStub.remindersAdded);
    }

    @Test
    public void execute_duplicateReminder_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).build();
        Reminder reminder = new Reminder(validPerson, MESSAGE, TYPICAL_DATE_1);
        ModelStub modelStub = new ModelStubWithReminder(reminder);

        AddCommand addCommand = new AddCommand(INDEX_FIRST_ITEM, MESSAGE, TYPICAL_DATE_1);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_REMINDER, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Message callAlice = new Message("Call Alice");
        AddCommand addCallAliceReminderCommand =
                new AddCommand(INDEX_FIRST_ITEM, callAlice, TYPICAL_DATE_1);

        // same object -> returns true
        assertEquals(addCallAliceReminderCommand, addCallAliceReminderCommand);

        // same values -> returns true
        AddCommand addCallAliceReminderCommandCopy =
                new AddCommand(INDEX_FIRST_ITEM, callAlice, TYPICAL_DATE_1);
        assertEquals(addCallAliceReminderCommand, addCallAliceReminderCommandCopy);

        // different types -> returns false
        assertNotEquals(addCallAliceReminderCommand, 1);

        // null -> returns false
        assertNotEquals(addCallAliceReminderCommand, null);

        // different index -> returns false
        assertNotEquals(addCallAliceReminderCommand, new AddCommand(INDEX_SECOND_ITEM, callAlice,
                TYPICAL_DATE_1));

        // different message -> returns false
        assertNotEquals(addCallAliceReminderCommand, new AddCommand(INDEX_FIRST_ITEM, new Message("Call Bob"),
                TYPICAL_DATE_1));

        // different date -> returns false
        assertNotEquals(addCallAliceReminderCommand, new AddCommand(INDEX_FIRST_ITEM, callAlice,
                TYPICAL_DATE_2));
    }

    /**
     * A Model stub that always accept the reminder being added.
     */
    private class ModelStubAcceptingReminderAdded extends ModelStubWithSortedPersonList {
        final ArrayList<Reminder> remindersAdded = new ArrayList<>();

        @Override
        public boolean hasReminder(Reminder reminder) {
            requireNonNull(reminder);
            return remindersAdded.stream().anyMatch(reminder::equals);
        }

        @Override
        public void addReminder(Reminder reminder) {
            requireNonNull(reminder);
            remindersAdded.add(reminder);
        }
    }

    /**
     * A Model stub that contains a single reminder.
     */
    private class ModelStubWithReminder extends ModelStubWithSortedPersonList {
        private final Reminder reminder;

        ModelStubWithReminder(Reminder reminder) {
            requireNonNull(reminder);
            this.reminder = reminder;
        }

        @Override
        public boolean hasReminder(Reminder reminder) {
            requireNonNull(reminder);
            return this.reminder.equals(reminder);
        }
    }
}
