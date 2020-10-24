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

class FindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_completedReminderList_success() {
        FindCommand findCommand = new FindCommand(false);

        String expectedMessage = String.format(FindCommand.MESSAGE_FIND_REMINDER_SUCCESS, "pending");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredRemindersList(reminder -> !reminder.isCompleted());

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_pendingReminderList_success() {
        FindCommand findCommand = new FindCommand(true);

        String expectedMessage = String.format(FindCommand.MESSAGE_FIND_REMINDER_SUCCESS, "completed");
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredRemindersList(Reminder::isCompleted);

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        FindCommand findCompletedCommand = new FindCommand(true);
        FindCommand findPendingCommand = new FindCommand(false);

        // same object -> returns true
        assertEquals(findCompletedCommand, findCompletedCommand);

        // same values -> returns true
        FindCommand findCompletedCommandCopy = new FindCommand(true);
        assertEquals(findCompletedCommand, findCompletedCommandCopy);

        // different types -> returns false
        assertNotEquals(findCompletedCommand, 1);

        // null -> returns false
        assertNotEquals(findCompletedCommand, null);

        // different status -> returns false
        assertNotEquals(findCompletedCommand, findPendingCommand);
    }
}
