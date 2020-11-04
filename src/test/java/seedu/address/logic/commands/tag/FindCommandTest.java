package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import java.text.NumberFormat;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

class FindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexFindByContactTag_success() {
        Tag tagToFind = model.getContactTagList().get(INDEX_FIRST_ITEM.getZeroBased());

        FindCommand findCommand = new FindCommand(INDEX_FIRST_ITEM, true);

        String expectedMessage = "Listing 3 contacts associated with: [friends]\n"
                + "1. Alice Pauline Phone: 94351253 Email: alice@example.com "
                + "Address: 123, Jurong West Ave 6, #08-111 Tags: [friends] Remark: Likes chocolates\n"
                + "2. Benson Meier Phone: 98765432 Email: johnd@example.com "
                + "Address: 311, Clementi Ave 2, #02-25 Tags: [owesMoney][friends] Remark: Owes me $10\n"
                + "3. Daniel Meier Phone: 87652533 Email: cornelia@example.com "
                + "Address: 10th street Tags: [friends]\n";

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.findByContactTag(tagToFind);

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFindBySalesTag_success() {
        Tag tagToFind = model.getSaleTagList().get(INDEX_FIRST_ITEM.getZeroBased());

        FindCommand findCommand = new FindCommand(INDEX_FIRST_ITEM, false);

        String expectedMessage = "Listing 1 sales items associated with: [electronics]\n"
                + "1. Camera (Date of Purchase: Sun, 01 Nov 2020, 09:05, "
                + "Quantity: 2, Unit Price: " + NumberFormat.getCurrencyInstance().format(1000.50)
                + ", Tags: [[electronics]]) (Client: Carl Kurz)\n";

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.findSalesBySaleTag(tagToFind);

        assertCommandSuccess(findCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFindByContactTag_failure() {
        Index outOfBoundIndex =
                Index.fromOneBased(model.getContactTagList().size() + 1);

        FindCommand findCommand = new FindCommand(outOfBoundIndex, true);

        assertCommandFailure(findCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFindBySaleTag_failure() {
        Index outOfBoundIndex =
                Index.fromOneBased(model.getSaleTagList().size() + 1);

        FindCommand findCommand = new FindCommand(outOfBoundIndex, false);

        assertCommandFailure(findCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FindCommand findFirstCommand = new FindCommand(INDEX_FIRST_ITEM, true);
        FindCommand findSecondCommand = new FindCommand(INDEX_SECOND_ITEM, true);
        FindCommand findThirdCommand = new FindCommand(INDEX_FIRST_ITEM, false);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same indices -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(INDEX_FIRST_ITEM, true);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different model types -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different indices -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
}
