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

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UniqueSaleList;
import seedu.address.testutil.TypicalSaleTags;
import seedu.address.testutil.person.PersonBuilder;
import seedu.address.testutil.sale.SaleBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddCommand(null, APPLE.getItemName(), APPLE.getDatetimeOfPurchase(),
                        APPLE.getQuantity(), APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), null,
                        APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(), APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), APPLE.getItemName(),
                        null, APPLE.getQuantity(), APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), APPLE.getItemName(),
                        APPLE.getDatetimeOfPurchase(), null, APPLE.getUnitPrice(), APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), APPLE.getItemName(),
                        APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(), null, APPLE.getTags()));
        assertThrows(NullPointerException.class, () ->
                new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), APPLE.getItemName(),
                        APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(), APPLE.getUnitPrice(), null));
    }

    @Test
    public void execute_invalidContactIndex_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).build();
        ModelManager model = new ModelManager();
        model.addPerson(validPerson);

        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        AddCommand addCommand = new AddCommand(new ArrayList<>(Arrays.asList(outOfBoundIndex)), BALL.getItemName(),
                BALL.getDatetimeOfPurchase(), BALL.getQuantity(), BALL.getUnitPrice(), BALL.getTags());

        String expectedMessage = MassSaleCommandUtil.generateInvalidIndexMessage(
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEXES, new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)));

        assertCommandFailure(addCommand, model, expectedMessage);
    }

    @Test
    public void execute_saleAcceptedByModel_addSuccessful() throws Exception {
        Person validPerson = new PersonBuilder(BOB).build();
        Sale validSale = new SaleBuilder(APPLE).build();
        Sale toAdd = new SaleBuilder(BALL).withBuyer(validPerson).build();
        ModelManager model = new ModelManager();
        model.addSale(validSale);
        model.addPerson(validPerson);
        model.addSaleTag(TypicalSaleTags.SPORTS);

        CommandResult commandResult = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
                toAdd.getItemName(), toAdd.getDatetimeOfPurchase(), toAdd.getQuantity(),
                toAdd.getUnitPrice(), toAdd.getTags()).execute(model);

        ObservableList<Sale> newSaleList = model.getSortedSaleList();
        ObservableList<Sale> expectedSaleList = new UniqueSaleList()
                .add(toAdd).add(validSale).asUnmodifiableObservableList();

        String expectedMessage = AddCommand.MESSAGE_SUCCESS
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(toAdd)));

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertTrue(newSaleList.equals(expectedSaleList));
    }

    @Test
    public void execute_duplicateSale_displaysDuplicateMessage() throws CommandException {
        Person validPerson = new PersonBuilder(BOB).build();
        Sale validSale = new SaleBuilder(APPLE).withBuyer(validPerson).build();

        ModelManager model = new ModelManager();
        model.addPerson(validPerson);
        model.addSale(validSale);

        CommandResult commandResult = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
                validSale.getItemName(), validSale.getDatetimeOfPurchase(), validSale.getQuantity(),
                validSale.getUnitPrice(), validSale.getTags()).execute(model);

        String expectedMessage = AddCommand.MESSAGE_FAILED + "\n" + AddCommand.MESSAGE_DUPLICATE_SALE
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(validSale)));

        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        AddCommand addAppleToIndexOne = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
                APPLE.getItemName(), APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(),
                APPLE.getUnitPrice(), APPLE.getTags());
        AddCommand addAppleToIndexTwo = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)),
                APPLE.getItemName(), APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(),
                APPLE.getUnitPrice(), APPLE.getTags());
        AddCommand addBallToIndexOne = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
                BALL.getItemName(), BALL.getDatetimeOfPurchase(), BALL.getQuantity(),
                BALL.getUnitPrice(), BALL.getTags());


        // same object -> returns true
        assertTrue(addAppleToIndexOne.equals(addAppleToIndexOne));

        // same values -> returns true
        AddCommand addAppleToIndexOneCopy = new AddCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
                APPLE.getItemName(), APPLE.getDatetimeOfPurchase(), APPLE.getQuantity(),
                APPLE.getUnitPrice(), APPLE.getTags());
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
