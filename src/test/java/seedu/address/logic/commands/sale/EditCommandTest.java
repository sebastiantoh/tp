package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALE_TAG_FRUITS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showSaleAtIndex;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;
import static seedu.address.testutil.person.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.commands.sale.EditCommand.EditSaleDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.sale.EditSaleDescriptorBuilder;
import seedu.address.testutil.sale.SaleBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Sale editedSale = new SaleBuilder().withItemName("Tissue Box").withBuyer(ALICE).build();
        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder(editedSale).withItemName("Tissue Box").build();
        EditCommand editCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), descriptor, INDEX_FIRST_ITEM);

        String expectedMessage = EditCommand.MESSAGE_EDIT_SALE_SUCCESS
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(editedSale)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSale(model.getFilteredSaleList().get(2), editedSale);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model.addContactTag(new Tag(VALID_SALE_TAG_FRUITS));
        Index indexLastSale = Index.fromOneBased(model.getSortedSaleList().size());
        Sale lastSale = model.getFilteredSaleList().get(indexLastSale.getZeroBased());

        SaleBuilder saleInList = new SaleBuilder(lastSale);
        Sale editedSale = saleInList.withItemName(VALID_ITEM_NAME_BALL).withQuantity(VALID_QUANTITY_APPLE)
            .withTags(VALID_SALE_TAG_FRUITS).withBuyer(ALICE).build();

        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_BALL)
            .withQuantity(VALID_QUANTITY_APPLE).withTags(VALID_SALE_TAG_FRUITS).build();
        EditCommand editCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), descriptor, INDEX_FIRST_ITEM);

        String expectedMessage = EditCommand.MESSAGE_EDIT_SALE_SUCCESS
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(editedSale)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSale(lastSale, editedSale);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(INDEX_THIRD_ITEM)), new EditSaleDescriptor(), null);
        Sale editedSale = model.getFilteredSaleList().get(INDEX_FIRST_ITEM.getZeroBased());

        String expectedMessage = EditCommand.MESSAGE_EDIT_SALE_SUCCESS
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(editedSale)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showSaleAtIndex(model, INDEX_SECOND_ITEM);

        Sale saleInFilteredList = model.getSortedSaleList().get(INDEX_FIRST_ITEM.getZeroBased());
        Sale editedSale = new SaleBuilder(saleInFilteredList).withItemName(VALID_ITEM_NAME_APPLE)
                .build();
        EditCommand editCommand = new EditCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
            new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_APPLE).build(), null);

        String expectedMessage = EditCommand.MESSAGE_EDIT_SALE_SUCCESS
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(editedSale)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setSale(model.getSortedSaleList().get(0), editedSale);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSaleUnfilteredList_failure() {
        Sale firstSale = model.getFilteredSaleList().get(INDEX_FIRST_ITEM.getZeroBased());
        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder(firstSale).build();
        EditCommand editCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)), descriptor, INDEX_THIRD_ITEM);

        String expectedMessage = EditCommand.MESSAGE_EDIT_SALE_FAILED + "\n" + EditCommand.MESSAGE_DUPLICATE_SALE
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(firstSale)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showSaleAtIndex(model, INDEX_FIRST_ITEM);
        // edit sale in filtered list into a duplicate in address book
        Sale saleInList = model.getAddressBook().getSaleList().get(INDEX_SECOND_ITEM.getZeroBased());

        EditCommand editCommand = new EditCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)),
            new EditSaleDescriptorBuilder(saleInList).build(), INDEX_FIRST_ITEM);

        String expectedMessage = EditCommand.MESSAGE_EDIT_SALE_FAILED + "\n" + EditCommand.MESSAGE_DUPLICATE_SALE
                + MassSaleCommandUtil.listAllSales(new ArrayList<>(Arrays.asList(saleInList)));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSaleIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        EditSaleDescriptor descriptor = new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_BALL).build();
        EditCommand editCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(outOfBoundIndex)), descriptor, null);

        String expectedMessage = MassSaleCommandUtil.generateInvalidIndexMessage(
                Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX, new ArrayList<>(Arrays.asList(outOfBoundIndex)));

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidSaleIndexFilteredList_failure() {
        showSaleAtIndex(model, INDEX_FIRST_ITEM);
        Index outOfBoundIndex = INDEX_SECOND_ITEM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getSaleList().size());

        EditCommand editCommand = new EditCommand(new ArrayList<>(Arrays.asList(outOfBoundIndex)),
            new EditSaleDescriptorBuilder().withItemName(VALID_ITEM_NAME_BALL).build(), null);

        String expectedMessage = MassSaleCommandUtil.generateInvalidIndexMessage(
                Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX, new ArrayList<>(Arrays.asList(outOfBoundIndex)));

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(
                new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)), DESC_APPLE, INDEX_SECOND_ITEM);

        // same values -> returns true
        EditSaleDescriptor copyDescriptor = new EditSaleDescriptor(DESC_APPLE);
        EditCommand commandWithSameValues = new EditCommand(
                new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)), copyDescriptor, INDEX_SECOND_ITEM);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PurgeCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new ArrayList<>(Arrays.asList(INDEX_SECOND_ITEM)), DESC_APPLE, INDEX_SECOND_ITEM)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)), DESC_BALL, INDEX_SECOND_ITEM)));

        // different person index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)), DESC_APPLE, INDEX_FIRST_ITEM)));

        // null person index -> returns false
        assertFalse(standardCommand.equals(
                new EditCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM)), DESC_APPLE, null)));
    }

}
