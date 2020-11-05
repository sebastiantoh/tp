package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.contact.SortCommand.MESSAGE_SORTING_ATTRIBUTE_INVALID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.person.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    private static final Comparator<Person> ORDER_BY_NAME = (x, y) -> x.getName().fullName
            .compareToIgnoreCase(y.getName().fullName);

    private static final Comparator<Person> ORDER_BY_NAME_DESC = (x, y) -> y.getName().fullName
            .compareToIgnoreCase(x.getName().fullName);

    private static final Comparator<Person> ORDER_BY_EMAIL = (x, y) -> x.getEmail().value
            .compareToIgnoreCase(y.getEmail().value);

    private static final Comparator<Person> ORDER_BY_EMAIL_DESC = (x, y) -> y.getEmail().value
            .compareToIgnoreCase(x.getEmail().value);

    private Model model;

    private Model reversedModel;

    @BeforeEach
    public void init() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        reversedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
    }

    private static void assertSortCommandSuccess(SortCommand sortCommand,
        Comparator<Person> expectedComparator, Model actualModel) {
        Model expectedModel = new ModelManager(new AddressBook(actualModel.getAddressBook()), new UserPrefs());
        expectedModel.updateSortedPersonList(expectedComparator);
        assertCommandSuccess(sortCommand, actualModel, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nameOnly_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_CONTACT_NAME, false);
        assertSortCommandSuccess(sortCommand, ORDER_BY_NAME, model);
        assertSortCommandSuccess(sortCommand, ORDER_BY_NAME, reversedModel);
    }

    @Test
    public void execute_nameAndDesc_success() {
        SortCommand sortCommand = new SortCommand(PREFIX_CONTACT_NAME, true);
        assertSortCommandSuccess(sortCommand, ORDER_BY_NAME_DESC, model);
        assertSortCommandSuccess(sortCommand, ORDER_BY_NAME_DESC, reversedModel);
    }

    @Test
    public void execute_emailOnly_success() throws CommandException {
        SortCommand sortCommand = new SortCommand(PREFIX_CONTACT_EMAIL, false);
        assertSortCommandSuccess(sortCommand, ORDER_BY_EMAIL, model);
        assertSortCommandSuccess(sortCommand, ORDER_BY_EMAIL, reversedModel);
    }

    @Test
    public void execute_emailAndDesc_success() throws CommandException {
        SortCommand sortCommand = new SortCommand(PREFIX_CONTACT_EMAIL, true);
        assertSortCommandSuccess(sortCommand, ORDER_BY_EMAIL_DESC, model);
        assertSortCommandSuccess(sortCommand, ORDER_BY_EMAIL_DESC, reversedModel);
    }

    @Test
    public void execute_invalidAttribute_failure() {
        Prefix invalidPrefix = PREFIX_CONTACT_ADDRESS;
        SortCommand sortCommand = new SortCommand(invalidPrefix, true);
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
