package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.sale.DeleteCommand.MESSAGE_NO_SALES_DISPLAYED;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import java.util.ArrayList;
import java.util.Arrays;

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
        model.updateFilteredSaleList(x -> false);
        DeleteCommand deleteCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)));

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.removeSale(saleToDelete);

        assertCommandFailure(deleteCommand, model, MESSAGE_NO_SALES_DISPLAYED);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        model.updateFilteredSaleList(x -> true);

        Sale saleToDelete = model.getSortedSaleList().get(1);
        DeleteCommand deleteCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)));

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_SALE_SUCCESS
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(saleToDelete)));
        ModelManager expectedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
        expectedModel.updateFilteredSaleList(x -> true);

        Person toEdit = expectedModel.getSortedPersonList().stream()
                .filter(person -> person.equals(saleToDelete.getBuyer()))
                .findAny()
                .orElse(null);

        assertNotNull(toEdit);

        Person newPerson = new PersonBuilder(toEdit).build();

        expectedModel.setPerson(toEdit, newPerson);
        expectedModel.removeSale(saleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSaleIndex_throwsCommandException() {
        model.updateFilteredSaleList(x -> true);
        DeleteCommand deleteCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(Index.fromOneBased(10))));
        String expectedMessage = Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX + ": 10";
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_emptyFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_ITEM);
        model.updateFilteredSaleList(x -> false);
        DeleteCommand deleteCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)));
        String expectedMessage = "No sales displayed, use `sale list` to display sales "
                + "before executing the `sale delete` command";

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstSaleCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)));
        DeleteCommand deleteSecondSaleCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)));

        // same object -> returns true
        assertTrue(deleteFirstSaleCommand.equals(deleteFirstSaleCommand));

        // same values -> returns true
        DeleteCommand deleteFirstSaleCommandCopy = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)));
        assertTrue(deleteFirstSaleCommand.equals(deleteFirstSaleCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstSaleCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstSaleCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstSaleCommand.equals(deleteSecondSaleCommand));
    }
}
