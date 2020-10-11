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
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

class EditCommandTest {

    private final static String MINIONS = "minions";
    private final static String BANANAS = "bananas";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexContactTagList_success() {
        Tag tagToEdit = model.getContactTagList().get(INDEX_FIRST_ITEM.getZeroBased());
        Tag editedTag = new Tag(MINIONS);
        EditCommand.EditTagDescriptor descriptor = new EditCommand.EditTagDescriptor();
        descriptor.setTagName(MINIONS);

        EditCommand editCommand = new EditCommand(INDEX_FIRST_ITEM, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TAG_SUCCESS, tagToEdit, editedTag);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.editContactTag(tagToEdit, editedTag);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexContactTagList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getContactTagList().size() + model.getSaleTagList().size() + 1);
        EditCommand.EditTagDescriptor descriptor = new EditCommand.EditTagDescriptor();
        descriptor.setTagName(MINIONS);

        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditCommand.EditTagDescriptor firstDescriptor = new EditCommand.EditTagDescriptor();
        firstDescriptor.setTagName(MINIONS);

        EditCommand.EditTagDescriptor secondDescriptor = new EditCommand.EditTagDescriptor();
        EditCommand editFirstCommand = new EditCommand(INDEX_FIRST_ITEM, firstDescriptor);
        EditCommand editSecondCommand = new EditCommand(INDEX_SECOND_ITEM, secondDescriptor);

        // same object -> returns true
        assertTrue(editFirstCommand.equals(editFirstCommand));

        // same values -> returns true
        EditCommand editFirstCommandCopy = new EditCommand(INDEX_FIRST_ITEM, firstDescriptor);
        assertTrue(editFirstCommand.equals(editFirstCommandCopy));

        // different types -> returns false
        assertFalse(editFirstCommand.equals(1));

        // null -> returns false
        assertFalse(editFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(editFirstCommand.equals(editSecondCommand));
    }
}
