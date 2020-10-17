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
        String saleListResult = "Sales made to Benson Meier:\n"
                + "1. Apple (Buyer: Benson Meier, Date of Purchase: Fri, 30 Oct 2020, 15:00, "
                + "Quantity: 10, Unit Price: $3.50, Tags: [[fruits]])\n";
        assertCommandSuccess(new ListCommand(INDEX_SECOND_ITEM), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_emptySalesList_displaysEmptySalesListMessage() {
        String saleListResult = "No sales made to Daniel Meier!";
        assertCommandSuccess(new ListCommand(Index.fromOneBased(4)), model, saleListResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        ListCommand listCommand = new ListCommand(outOfBoundIndex);

        assertCommandFailure(listCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
