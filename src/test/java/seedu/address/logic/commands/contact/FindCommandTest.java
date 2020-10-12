package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.CARL;
import static seedu.address.testutil.person.TypicalPersons.DANIEL;
import static seedu.address.testutil.person.TypicalPersons.ELLE;
import static seedu.address.testutil.person.TypicalPersons.FIONA;
import static seedu.address.testutil.person.TypicalPersons.HOON;
import static seedu.address.testutil.person.TypicalPersons.IDA;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private Model model;

    private Model expectedModel;

    @BeforeEach
    void beforeEach() {
        model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());
        this.model.addPerson(HOON);
        this.model.addPerson(IDA);

        this.expectedModel.addPerson(HOON);
        this.expectedModel.addPerson(IDA);
    }


    @Test
    public void equals() {
        String firstSearchKeyword = "first";
        String secondSearchKeyword = "second";

        FindCommand findFirstCommand = new FindCommand(firstSearchKeyword);
        FindCommand findSecondCommand = new FindCommand(secondSearchKeyword);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstSearchKeyword);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String zeroKeyword = " ";
        FindCommand command = new FindCommand(zeroKeyword);
        expectedModel.updateFilteredPersonList(x -> false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 4);
        Predicate<Person> predicate = Arrays.asList(CARL, FIONA, ELLE, IDA)::contains;
        String multipleKeywords = "Kurz Elle Kunz";
        FindCommand command = new FindCommand(multipleKeywords);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, IDA), model.getSortedPersonList());
    }

    @Test
    public void execute_similarMatch_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        Predicate<Person> predicate = Arrays.asList(BENSON, DANIEL, ELLE, HOON, IDA)::contains;
        String keyword = "meer";
        FindCommand command = new FindCommand(keyword);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL, ELLE, HOON, IDA), model.getSortedPersonList());
    }

    @Test
    public void execute_similarMatch_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String unmatchedKeyword = "kdapsokdasopidjasoidjsaiodsjdoasijdsaodi";
        FindCommand command = new FindCommand(unmatchedKeyword);
        expectedModel.updateFilteredPersonList(x -> false);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedPersonList());
    }

    @Test
    public void execute_exactAndSimilarMatch_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 5);
        Predicate<Person> predicate = Arrays.asList(BENSON, DANIEL, ELLE, HOON, IDA)::contains;
        String keyword = IDA.getName().fullName;
        FindCommand command = new FindCommand(keyword);
        expectedModel.updateFilteredPersonList(predicate);
        assertEquals(new CommandResult(expectedMessage), command.execute(model));
        assertEquals(Arrays.asList(ELLE, DANIEL, BENSON, HOON, IDA), model.getFilteredPersonList());
        assertEquals(Arrays.asList(IDA, BENSON, DANIEL, ELLE, HOON), model.getSortedPersonList());
    }
}
