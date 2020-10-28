package seedu.address.logic.commands.archive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code RemoveCommand}.
 */
public class RemoveCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ARCHIVED_PERSONS);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Person personToRemove = model.getSortedPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_ITEM);
        assertTrue(personToRemove.isArchived());

        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVE_SUCCESS, personToRemove);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person removedPerson = new Person(
                personToRemove.getId(),
                personToRemove.getName(),
                personToRemove.getPhone(),
                personToRemove.getEmail(),
                personToRemove.getAddress(),
                personToRemove.getTags(),
                personToRemove.getRemark(),
                !personToRemove.isArchived()
        );
        expectedModel.setPerson(personToRemove, removedPerson);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ARCHIVED_PERSONS);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidFilteredList_throwsCommandException() {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_UNARCHIVED_PERSONS);
        Person invalidPersonToArchive = model.getSortedPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        RemoveCommand removeCommand = new RemoveCommand(INDEX_FIRST_ITEM);
        assertFalse(invalidPersonToArchive.isArchived());

        assertCommandFailure(removeCommand, model, Messages.MESSAGE_UNARCHIVE_INVALID_LIST);
    }

    @Test
    public void equals() {
        RemoveCommand removeFirstCommand = new RemoveCommand(INDEX_FIRST_ITEM);
        RemoveCommand removeSecondCommand = new RemoveCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveCommand archiveFirstCommandCopy = new RemoveCommand(INDEX_FIRST_ITEM);
        assertTrue(removeFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }

}
