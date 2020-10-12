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

class FindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexFindByContactTag_success() {
        Tag tagToFind = model.getContactTagList().get(INDEX_FIRST_ITEM.getZeroBased());

        FindCommand findCommand = new FindCommand(INDEX_FIRST_ITEM);

        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.findByContactTag(tagToFind);
        expectedModel.updateFilteredPersonList(p -> p.getTags().contains(tagToFind));

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFindByContactTag_failure() {
        Index outOfBoundIndex =
                Index.fromOneBased(model.getContactTagList().size() + model.getSaleTagList().size() + 1);

        FindCommand findCommand = new FindCommand(outOfBoundIndex);

        assertCommandFailure(findCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FindCommand findFirstCommand = new FindCommand(INDEX_FIRST_ITEM);
        FindCommand findSecondCommand = new FindCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same indices -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(INDEX_FIRST_ITEM);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}