package seedu.address.logic.commands.sale;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndex_showsSales() {
        String saleListResult = "Sales made to Benson Meier:\n" +
                "1. Apple (Date of Purchase: 2020-10-30T15:00, Quantity: 10, Unit Price: $3.50, Tags: [[fruits]])\n" +
                "2. Ball (Date of Purchase: 2020-09-22T12:40, Quantity: 1, Unit Price: $0.80, Tags: [[sports]])\n";
        assertCommandSuccess(new ListCommand(INDEX_SECOND_ITEM), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_emptySalesList_displaysEmptySalesListMessage() {
        String saleListResult = "No sales made to Alice Pauline!";
        assertCommandSuccess(new ListCommand(INDEX_FIRST_ITEM), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        ListCommand listCommand = new ListCommand(outOfBoundIndex);

        assertCommandFailure(listCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
