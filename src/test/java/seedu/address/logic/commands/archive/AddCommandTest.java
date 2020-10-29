package seedu.address.logic.commands.archive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ListCommand}.
 */
public class AddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToArchive = model.getSortedPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        AddCommand archiveCommand = new AddCommand(INDEX_FIRST_ITEM);
        assertFalse(personToArchive.isArchived());

        String expectedMessage = String.format(AddCommand.MESSAGE_ARCHIVE_PERSON_SUCCESS, personToArchive);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Person archivedPerson = new Person(
                personToArchive.getId(),
                personToArchive.getName(),
                personToArchive.getPhone(),
                personToArchive.getEmail(),
                personToArchive.getAddress(),
                personToArchive.getTags(),
                personToArchive.getRemark(),
                !personToArchive.isArchived()
        );
        expectedModel.setPerson(personToArchive, archivedPerson);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        AddCommand archiveCommand = new AddCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidFilteredList_throwsCommandException() {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ARCHIVED_PERSONS);
        Person invalidPersonToArchive = model.getSortedPersonList().get(INDEX_FIRST_ITEM.getZeroBased());
        AddCommand archiveCommand = new AddCommand(INDEX_FIRST_ITEM);
        assertTrue(invalidPersonToArchive.isArchived());

        assertCommandFailure(archiveCommand, model, Messages.MESSAGE_ARCHIVE_INVALID_LIST);
    }

    @Test
    public void equals() {
        AddCommand archiveFirstCommand = new AddCommand(INDEX_FIRST_ITEM);
        AddCommand archiveSecondCommand = new AddCommand(INDEX_SECOND_ITEM);

        // same object -> returns true
        assertTrue(archiveFirstCommand.equals(archiveFirstCommand));

        // same values -> returns true
        AddCommand archiveFirstCommandCopy = new AddCommand(INDEX_FIRST_ITEM);
        assertTrue(archiveFirstCommand.equals(archiveFirstCommandCopy));

        // different types -> returns false
        assertFalse(archiveFirstCommand.equals(1));

        // null -> returns false
        assertFalse(archiveFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(archiveFirstCommand.equals(archiveSecondCommand));
    }

}
