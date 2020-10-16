package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person specifiedPerson = model.getSortedPersonList().get(INDEX_SECOND_ITEM.getZeroBased());
        Sale saleToDelete = specifiedPerson.getSalesList().asUnmodifiableObservableList().get(1);
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_SECOND_ITEM, INDEX_SECOND_ITEM);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SALE_SUCCESS, saleToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
        expectedModel.removeSaleFromPerson(specifiedPerson, saleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, INDEX_FIRST_ITEM);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSaleIndex_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_SECOND_ITEM, INDEX_THIRD_ITEM);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_SECOND_ITEM);

        Person specifiedPerson = model.getSortedPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        Sale saleToDelete = specifiedPerson.getSalesList().asUnmodifiableObservableList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SALE_SUCCESS, saleToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeSaleFromPerson(specifiedPerson, saleToDelete);

        showPersonAtIndex(expectedModel, INDEX_SECOND_ITEM);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);

        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex, INDEX_FIRST_ITEM);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstSaleCommand = new DeleteCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);
        DeleteCommand deleteSecondSaleCommand = new DeleteCommand(INDEX_FIRST_ITEM, INDEX_SECOND_ITEM);
        DeleteCommand deleteFromSecondPersonCommand = new DeleteCommand(INDEX_SECOND_ITEM, INDEX_FIRST_ITEM);

        // same object -> returns true
        assertTrue(deleteFirstSaleCommand.equals(deleteFirstSaleCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM);
        assertTrue(deleteFirstSaleCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstSaleCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstSaleCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstSaleCommand.equals(deleteSecondSaleCommand));
        assertFalse(deleteFirstSaleCommand.equals(deleteFromSecondPersonCommand));
    }
}
