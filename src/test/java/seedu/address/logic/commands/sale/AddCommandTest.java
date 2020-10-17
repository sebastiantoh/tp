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

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UniqueSaleList;
import seedu.address.testutil.person.PersonBuilder;
import seedu.address.testutil.sale.SaleBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddCommand(null, APPLE.getItemName(), APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(),
                        APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_SECOND_ITEM, null, APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(),
                        APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_SECOND_ITEM, APPLE.getItemName(), null, APPLE.getQuantity(),
                        APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_SECOND_ITEM, APPLE.getItemName(), APPLE.getDatetimeOfPurchase(), null,
                        APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_SECOND_ITEM, APPLE.getItemName(), APPLE.getDatetimeOfPurchase(),
                        APPLE.getQuantity(), null, APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_SECOND_ITEM, APPLE.getItemName(), APPLE.getDatetimeOfPurchase(),
                        APPLE.getQuantity(), APPLE.getUnitPrice(), null));
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).build();
        ModelManager model = new ModelManager();
        model.addPerson(validPerson);

        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        AddCommand addCommand = new AddCommand(outOfBoundIndex, BALL.getItemName(), BALL.getDatetimeOfPurchase(),
                BALL.getQuantity(), BALL.getUnitPrice(), BALL.getTags());

        assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_saleAcceptedByModel_addSuccessful() throws Exception {
        Person validPerson = new PersonBuilder(BOB).build();
        Sale validSale = new SaleBuilder(APPLE).build();
        Sale toAdd = new SaleBuilder(BALL).withBuyer(validPerson).build();
        ModelManager model = new ModelManager();
        model.addSale(validSale);
        model.addPerson(validPerson);

        CommandResult commandResult = new AddCommand(INDEX_FIRST_ITEM, toAdd.getItemName(), toAdd.getDatetimeOfPurchase(),
                toAdd.getQuantity(), toAdd.getUnitPrice(), toAdd.getTags()).execute(model);

        ObservableList<Sale> newSaleList = model.getSortedSaleList();
        ObservableList<Sale> expectedSaleList = new UniqueSaleList()
                .add(toAdd).add(validSale).asUnmodifiableObservableList();

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, toAdd), commandResult.getFeedbackToUser());
        assertTrue(newSaleList.equals(expectedSaleList));
    }

    @Test
    public void execute_duplicateSale_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).build();
        Sale validSale = new SaleBuilder(APPLE).withBuyer(validPerson).build();

        ModelManager model = new ModelManager();
        model.addPerson(validPerson);
        model.addSale(validSale);

        AddCommand addCommand = new AddCommand(INDEX_FIRST_ITEM, validSale.getItemName(),
                validSale.getDatetimeOfPurchase(), validSale.getQuantity(),
                validSale.getUnitPrice(), validSale.getTags());

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_SALE, () -> addCommand.execute(model));
    }

    @Test
    public void equals() {
        AddCommand addAppleToIndexOne = new AddCommand(INDEX_FIRST_ITEM, APPLE.getItemName(),
                APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(), APPLE.getUnitPrice(), APPLE.getTags());
        AddCommand addAppleToIndexTwo = new AddCommand(INDEX_SECOND_ITEM, APPLE.getItemName(),
                APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(), APPLE.getUnitPrice(), APPLE.getTags());
        AddCommand addBallToIndexOne = new AddCommand(INDEX_FIRST_ITEM, BALL.getItemName(),
                BALL.getDatetimeOfPurchase(), BALL.getQuantity(), BALL.getUnitPrice(), BALL.getTags());


        // same object -> returns true
        assertTrue(addAppleToIndexOne.equals(addAppleToIndexOne));

        // same values -> returns true
        AddCommand addAppleToIndexOneCopy = new AddCommand(INDEX_FIRST_ITEM, APPLE.getItemName(),
                APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(), APPLE.getUnitPrice(), APPLE.getTags());
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
