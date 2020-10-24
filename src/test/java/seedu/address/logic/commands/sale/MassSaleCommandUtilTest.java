package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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


public class MassSaleCommandUtilTest {
//    @Test
//    public void listAllSales_noSalesListed_throwsCommandException() {
//        assertThrows(new AssertionError(), () -> MassSaleCommandUtil.listAllSales(new ArrayList<>()));
//    }
//
//    @Test
//    public void listAllSales_validSaleList_success() {
//        model.updateFilteredSaleList(x -> true);
//
//        Sale saleToDelete = model.getFilteredSaleList().get(1);
//        DeleteCommand deleteCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)));
//
//        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_SALE_SUCCESS, saleToDelete);
//
//        ModelManager expectedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
//        expectedModel.updateFilteredSaleList(x -> true);
//
//        Person toEdit = expectedModel.getSortedPersonList().stream()
//                .filter(person -> person.equals(saleToDelete.getBuyer()))
//                .findAny()
//                .orElse(null);
//
//        assertNotNull(toEdit);
//
//        Person newPerson = new PersonBuilder(toEdit)
//                .withTotalSalesAmount(toEdit.getTotalSalesAmount().subtract(saleToDelete.getTotalCost())).build();
//
//        expectedModel.setPerson(toEdit, newPerson);
//        expectedModel.removeSale(saleToDelete);
//
//        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
//    }
//
//    @Test
//    public void execute_invalidSaleIndex_throwsCommandException() {
//        model.updateFilteredSaleList(x -> true);
//        DeleteCommand deleteCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(Index.fromOneBased(10))));
//        String expectedMessage = Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX + ": 10";
//        assertCommandFailure(deleteCommand, model, expectedMessage);
//    }
//
//
//    @Test
//    public void equals() {
//        DeleteCommand deleteFirstSaleCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)));
//        DeleteCommand deleteSecondSaleCommand = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)));
//
//        // same object -> returns true
//        assertTrue(deleteFirstSaleCommand.equals(deleteFirstSaleCommand));
//
//        // same values -> returns true
//        DeleteCommand deleteFirstSaleCommandCopy = new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)));
//        assertTrue(deleteFirstSaleCommand.equals(deleteFirstSaleCommandCopy));
//
//        // different types -> returns false
//        assertFalse(deleteFirstSaleCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(deleteFirstSaleCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(deleteFirstSaleCommand.equals(deleteSecondSaleCommand));
//    }
}
