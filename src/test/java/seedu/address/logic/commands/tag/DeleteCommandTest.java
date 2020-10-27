package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexContactTagList_success() {
        Tag tagToDelete = model.getContactTagList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITEM, true);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteContactTag(tagToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexContactTagList_failure() {
        Index outOfBoundIndex =
                Index.fromOneBased(model.getContactTagList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, true);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ITEM, true);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ITEM, true);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same indices -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ITEM, true);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
