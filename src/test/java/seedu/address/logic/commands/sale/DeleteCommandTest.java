package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.sale.DeleteCommand.MESSAGE_NO_SALES_DISPLAYED;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;
import seedu.address.testutil.person.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_noSalesListed_throwsCommandException() {
        Sale saleToDelete = model.getSortedSaleList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_SECOND_ITEM, INDEX_FIRST_ITEM);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.removeSale(saleToDelete);

        assertCommandFailure(deleteCommand, model, MESSAGE_NO_SALES_DISPLAYED);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.updateFilteredSaleList(x -> true);

        Sale saleToDelete = model.getFilteredSaleList().get(1);
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_SECOND_ITEM, INDEX_SECOND_ITEM);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SALE_SUCCESS, saleToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
        expectedModel.updateFilteredSaleList(x -> true);

        Person toEdit = expectedModel.getSortedPersonList().get(INDEX_SECOND_ITEM.getZeroBased());
        Person newPerson = new PersonBuilder(toEdit)
                .withTotalSalesAmount(toEdit.getTotalSalesAmount().subtract(saleToDelete.getTotalCost())).build();

        expectedModel.setPerson(toEdit, newPerson);
        expectedModel.removeSale(saleToDelete);

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
        model.updateFilteredSaleList(x -> true);
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_SECOND_ITEM, Index.fromOneBased(10));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);
        model.updateFilteredSaleList(x -> true);

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
