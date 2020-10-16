package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.person.TypicalPersons.BOB;
import static seedu.address.testutil.sale.TypicalSales.APPLE;
import static seedu.address.testutil.sale.TypicalSales.BALL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.sale.UniqueSaleList;
import seedu.address.testutil.person.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullSale_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(INDEX_SECOND_ITEM, null));
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).withSales(APPLE).build();
        ModelManager model = new ModelManager();
        model.addPerson(validPerson);

        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        AddCommand addCommand = new AddCommand(outOfBoundIndex, BALL);

        assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_saleAcceptedByModel_addSuccessful() throws Exception {
        Person validPerson = new PersonBuilder(BOB).withSales(APPLE).build();
        ModelManager model = new ModelManager();
        model.addPerson(validPerson);

        CommandResult commandResult = new AddCommand(INDEX_FIRST_ITEM, BALL).execute(model);

        UniqueSaleList newSaleList = model.getSortedPersonList().get(0).getSalesList();
        UniqueSaleList expectedSaleList = new UniqueSaleList().add(APPLE).add(BALL);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, BALL), commandResult.getFeedbackToUser());
        assertTrue(newSaleList.equals(expectedSaleList));
    }

    @Test
    public void execute_duplicateSale_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).withSales(APPLE).build();
        AddCommand addCommand = new AddCommand(INDEX_FIRST_ITEM, APPLE);
        ModelManager model = new ModelManager();
        model.addPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_SALE, () -> addCommand.execute(model));
    }

    @Test
    public void equals() {
        AddCommand addAppleToIndexOne = new AddCommand(INDEX_FIRST_ITEM, APPLE);
        AddCommand addAppleToIndexTwo = new AddCommand(INDEX_SECOND_ITEM, APPLE);
        AddCommand addBallToIndexOne = new AddCommand(INDEX_FIRST_ITEM, BALL);


        // same object -> returns true
        assertTrue(addAppleToIndexOne.equals(addAppleToIndexOne));

        // same values -> returns true
        AddCommand addAppleToIndexOneCopy = new AddCommand(INDEX_FIRST_ITEM, APPLE);
        assertTrue(addAppleToIndexOne.equals(addAppleToIndexOneCopy));

        // different types -> returns false
        assertFalse(addAppleToIndexOne.equals(1));

        // null -> returns false
        assertFalse(addAppleToIndexOne.equals(null));

        // different index -> returns false
        assertFalse(addAppleToIndexOne.equals(addAppleToIndexTwo));

        // different sale -> returns false
        assertFalse(addAppleToIndexOne.equals(addBallToIndexOne));
    }
}
