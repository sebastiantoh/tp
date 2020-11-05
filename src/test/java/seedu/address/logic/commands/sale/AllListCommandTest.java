package seedu.address.logic.commands.sale;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

//@@author hakujitsu
/**
 * Contains integration tests (interaction with the Model) and unit tests for AllListCommand.
 */
public class AllListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_noIndex_showsAllSales() {
        String saleListResult = "Listed all sales.";
        assertCommandSuccess(new AllListCommand(true, null), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_validIndex_showsSales() {
        String saleListResult = "Listed all sales made to Benson Meier.";
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredSaleList(x -> x.getBuyer().getId().equals(2));
        assertCommandSuccess(new AllListCommand(false, INDEX_SECOND_ITEM), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_emptySalesListWithoutIndex_displaysEmptySalesListMessage() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String saleListResult = "No sales made!";
        assertCommandSuccess(new AllListCommand(true, null), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_emptySalesListWithIndex_displaysEmptySalesListMessage() {
        String saleListResult = "No sales made to Daniel Meier!";
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredSaleList(sale -> false);
        assertCommandSuccess(new AllListCommand(false, Index.fromOneBased(4)), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        AllListCommand listCommand = new AllListCommand(false, outOfBoundIndex);

        assertCommandFailure(listCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
