package seedu.address.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.reminder.Reminder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_defaultReminderList_success() {
        ListCommand listCommand = new ListCommand(null);

        String expectedMessage = ListCommand.MESSAGE_LIST_ALL_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredRemindersList(reminder -> true);

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_completedReminderList_success() {
        ListCommand listCommand = new ListCommand(false);

        String expectedMessage = String.format(ListCommand.MESSAGE_LIST_REMINDER_SUCCESS, 3, "pending");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredRemindersList(reminder -> !reminder.isCompleted());

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_pendingReminderList_success() {
        ListCommand listCommand = new ListCommand(true);

        String expectedMessage = String.format(ListCommand.MESSAGE_LIST_REMINDER_SUCCESS, 0, "completed");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredRemindersList(Reminder::isCompleted);

        assertCommandSuccess(listCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ListCommand findCompletedCommand = new ListCommand(true);
        ListCommand findPendingCommand = new ListCommand(false);

        // same object -> returns true
        assertEquals(findCompletedCommand, findCompletedCommand);

        // same values -> returns true
        ListCommand findCompletedCommandCopy = new ListCommand(true);
        assertEquals(findCompletedCommand, findCompletedCommandCopy);

        // different types -> returns false
        assertNotEquals(findCompletedCommand, 1);

        // null -> returns false
        assertNotEquals(findCompletedCommand, null);

        // different status -> returns false
        assertNotEquals(findCompletedCommand, findPendingCommand);
    }
}
