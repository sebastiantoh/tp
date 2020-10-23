package seedu.address.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MESSAGE_CALL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MESSAGE_CALL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.person.TypicalPersons.GEORGE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.commands.reminder.EditCommand.EditReminderDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Message;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reminder.Reminder;
import seedu.address.testutil.TypicalDates;
import seedu.address.testutil.reminder.EditReminderDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Reminder editedReminder = new Reminder(GEORGE, new Message(VALID_MESSAGE_CALL_AMY),
                TypicalDates.TYPICAL_DATE_1);
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                // George appears as the 6th item
                .withContactIndex(Index.fromZeroBased(6))
                .withMessage(VALID_MESSAGE_CALL_AMY)
                .withScheduledDate(TypicalDates.TYPICAL_DATE_1)
                .withStatus(false)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setReminder(model.getSortedReminderList().get(0), editedReminder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastReminder = Index.fromOneBased(model.getSortedReminderList().size());
        Reminder lastReminder = model.getSortedReminderList().get(indexLastReminder.getZeroBased());

        Reminder editedReminder =
                new Reminder(lastReminder.getPerson(), lastReminder.getMessage(), TypicalDates.TYPICAL_DATE_3, true);

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withScheduledDate(TypicalDates.TYPICAL_DATE_3)
                .withStatus(true)
                .build();

        EditCommand editCommand = new EditCommand(indexLastReminder, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setReminder(lastReminder, editedReminder);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, new EditReminderDescriptor());
        Reminder editedReminder = model.getSortedReminderList().get(INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateReminderUnfilteredList_failure() {
        Reminder firstReminder = model.getSortedReminderList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withContactIndex(Index.fromZeroBased(model.getSortedPersonList().indexOf(firstReminder.getPerson())))
                .withScheduledDate(firstReminder.getScheduledDate())
                .withMessage(firstReminder.getMessage().message)
                .withStatus(false)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ITEM, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_REMINDER);
    }

    @Test
    public void execute_invalidReminderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedReminderList().size() + 1);
        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder().withMessage(VALID_MESSAGE_CALL_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder()
                        .withMessage(VALID_MESSAGE_CALL_AMY)
                        .withContactIndex(INDEX_SECOND_ITEM)
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_3)
                        .withStatus(false)
                        .build();
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        // same values -> returns true
        EditReminderDescriptor copyDescriptor = new EditReminderDescriptor(descriptor);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ITEM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PurgeCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ITEM, descriptor)));

        // different descriptor -> returns false
        EditReminderDescriptor diffDescriptor =
                new EditReminderDescriptorBuilder()
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_2)
                        .build();
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ITEM, diffDescriptor)));
    }
}
