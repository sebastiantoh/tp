package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIT_PRICE_BALL;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sale.EditCommand.EditSaleDescriptor;
import seedu.address.testutil.sale.EditSaleDescriptorBuilder;

public class EditSaleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditSaleDescriptor descriptorWithSameValues = new EditSaleDescriptor(DESC_APPLE);
        assertTrue(DESC_APPLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE.equals(DESC_APPLE));

        // null -> returns false
        assertFalse(DESC_APPLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE.equals(DESC_BALL));

        // different item name -> returns false
        EditSaleDescriptor editedApple = new EditSaleDescriptorBuilder(DESC_APPLE)
                .withItemName(VALID_ITEM_NAME_BALL).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different buyer id -> returns false
        editedApple = new EditSaleDescriptorBuilder(DESC_APPLE).withBuyer(BOB).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different datetime -> returns false
        editedApple = new EditSaleDescriptorBuilder(DESC_APPLE)
                .withDatetimeOfPurchase(VALID_DATE_BALL).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different unit price -> returns false
        editedApple = new EditSaleDescriptorBuilder(DESC_APPLE).withUnitPrice(VALID_UNIT_PRICE_BALL).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different quantity -> returns false
        editedApple = new EditSaleDescriptorBuilder(DESC_APPLE).withQuantity(VALID_QUANTITY_BALL).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new EditSaleDescriptorBuilder(DESC_APPLE).withTags().build();
        assertFalse(DESC_APPLE.equals(editedApple));
    }
}
