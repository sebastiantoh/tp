package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.contact.SortCommand.MESSAGE_SORTING_ATTRIBUTE_INVALID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOTAL_SALES;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.person.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;

    private Model reversedModel;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        reversedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
    }

    @Test
    public void execute_nameOnly_success() throws CommandException {
        Prefix prefix = PREFIX_CONTACT_NAME;
        boolean isDesc = false;

        SortCommand sortCommand = new SortCommand(prefix, isDesc);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedPersonList((x, y) -> x.getName().fullName
                .compareToIgnoreCase(y.getName().fullName));

        CommandResult commandResult = sortCommand.execute(model);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModel, model);

        Model expectedReverseModel = new ModelManager(new AddressBook(reversedModel.getAddressBook()), new UserPrefs());
        expectedReverseModel.updateSortedPersonList((x, y) -> x.getName().fullName
                .compareToIgnoreCase(y.getName().fullName));

        commandResult = sortCommand.execute(reversedModel);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedReverseModel, reversedModel);

    }

    @Test
    public void execute_nameAndDesc_success() throws CommandException {
        Prefix prefix = PREFIX_CONTACT_NAME;
        boolean isDesc = true;

        SortCommand sortCommand = new SortCommand(prefix, isDesc);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedPersonList((x, y) -> y.getName().fullName
                .compareToIgnoreCase(x.getName().fullName));

        CommandResult commandResult = sortCommand.execute(model);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModel, model);

        Model expectedReverseModel = new ModelManager(new AddressBook(reversedModel.getAddressBook()), new UserPrefs());
        expectedReverseModel.updateSortedPersonList((x, y) -> y.getName().fullName
                .compareToIgnoreCase(x.getName().fullName));

        commandResult = sortCommand.execute(reversedModel);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedReverseModel, reversedModel);

    }

    @Test
    public void execute_emailOnly_success() throws CommandException {
        Prefix prefix = PREFIX_CONTACT_EMAIL;
        boolean isDesc = false;

        SortCommand sortCommand = new SortCommand(prefix, isDesc);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedPersonList((x, y) -> x.getEmail().value
                .compareToIgnoreCase(y.getEmail().value));

        CommandResult commandResult = sortCommand.execute(model);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModel, model);

        Model expectedReverseModel = new ModelManager(new AddressBook(reversedModel.getAddressBook()), new UserPrefs());
        expectedReverseModel.updateSortedPersonList((x, y) -> x.getEmail().value
                .compareToIgnoreCase(y.getEmail().value));

        commandResult = sortCommand.execute(reversedModel);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedReverseModel, reversedModel);

    }

    @Test
    public void execute_emailAndDesc_success() throws CommandException {
        Prefix prefix = PREFIX_CONTACT_EMAIL;
        boolean isDesc = true;

        SortCommand sortCommand = new SortCommand(prefix, isDesc);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedPersonList((x, y) -> y.getEmail().value
                .compareToIgnoreCase(x.getEmail().value));

        CommandResult commandResult = sortCommand.execute(model);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModel, model);

        Model expectedReverseModel = new ModelManager(new AddressBook(reversedModel.getAddressBook()), new UserPrefs());
        expectedReverseModel.updateSortedPersonList((x, y) -> y.getEmail().value
                .compareToIgnoreCase(x.getEmail().value));

        commandResult = sortCommand.execute(reversedModel);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedReverseModel, reversedModel);

    }

    @Test
    public void execute_totalSalesOnly_success() throws CommandException {
        Prefix prefix = PREFIX_TOTAL_SALES;
        boolean isDesc = false;

        SortCommand sortCommand = new SortCommand(prefix, isDesc);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Comparator<Person> comparator = Comparator.comparingDouble(x -> x.getSalesList().getTotalSalesAmount());
        expectedModel.updateSortedPersonList(comparator);

        CommandResult commandResult = sortCommand.execute(model);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModel, model);

        Model expectedReverseModel = new ModelManager(new AddressBook(reversedModel.getAddressBook()), new UserPrefs());
        expectedReverseModel.updateSortedPersonList(comparator);

        commandResult = sortCommand.execute(reversedModel);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedReverseModel, reversedModel);

    }

    @Test
    public void execute_totalSalesAndDesc_success() throws CommandException {
        Prefix prefix = PREFIX_TOTAL_SALES;
        boolean isDesc = true;

        SortCommand sortCommand = new SortCommand(prefix, isDesc);

        String expectedMessage = SortCommand.MESSAGE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Comparator<Person> comparator = Comparator.comparingDouble(x -> x.getSalesList().getTotalSalesAmount());
        expectedModel.updateSortedPersonList(comparator.reversed());

        CommandResult commandResult = sortCommand.execute(model);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModel, model);

        Model expectedReverseModel = new ModelManager(new AddressBook(reversedModel.getAddressBook()), new UserPrefs());
        expectedReverseModel.updateSortedPersonList(comparator.reversed());

        commandResult = sortCommand.execute(reversedModel);

        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedReverseModel, reversedModel);

    }

    @Test
    public void execute_invalidAttribute_failure() {
        Prefix prefix = PREFIX_CONTACT_ADDRESS;
        boolean isDesc = true;
        SortCommand sortCommand = new SortCommand(prefix, isDesc);
        String expectedMessage = MESSAGE_SORTING_ATTRIBUTE_INVALID;
        assertCommandFailure(sortCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final SortCommand standardCommand = new SortCommand(PREFIX_CONTACT_NAME, true);

        // same values -> returns true
        SortCommand commandWithSameValues = new SortCommand(PREFIX_CONTACT_NAME, true);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PurgeCommand()));

        // different prefix -> returns false
        assertFalse(standardCommand.equals(new SortCommand(PREFIX_CONTACT_EMAIL, true)));

        // different isDesc boolean -> returns false
        assertFalse(standardCommand.equals(new SortCommand(PREFIX_CONTACT_NAME, false)));
    }

}
