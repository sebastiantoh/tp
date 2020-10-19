package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

class AddCommandTest {
    private static final String BANANAS = "bananas";
    private static final String MINIONS = "minions";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validContactTag_success() {
        Tag tagToAdd = new Tag(MINIONS);
        AddCommand addCommand = new AddCommand(tagToAdd, true);

        String expectedMessage = String.format(AddCommand.MESSAGE_CONTACT_SUCCESS, tagToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addContactTag(tagToAdd);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSaleTag_success() {
        Tag tagToAdd = new Tag(BANANAS);
        AddCommand addCommand = new AddCommand(tagToAdd, false);

        String expectedMessage = String.format(AddCommand.MESSAGE_SALES_SUCCESS, tagToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addSaleTag(tagToAdd);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Tag bananas = new Tag(BANANAS);
        Tag minions = new Tag(MINIONS);
        AddCommand addContactTagCommand = new AddCommand(minions, true);
        AddCommand addSaleTagCommand = new AddCommand(bananas, false);

        // same object -> returns true
        assertTrue(addContactTagCommand.equals(addContactTagCommand));

        // same values -> returns true
        AddCommand addContactTagCommandCopy = new AddCommand(minions, true);
        assertTrue(addContactTagCommand.equals(addContactTagCommandCopy));

        // different types -> returns false
        assertFalse(addContactTagCommand.equals(1));

        // null -> returns false
        assertFalse(addContactTagCommand.equals(null));

        // different tags -> returns false
        assertFalse(addContactTagCommand.equals(addSaleTagCommand));
    }
}
