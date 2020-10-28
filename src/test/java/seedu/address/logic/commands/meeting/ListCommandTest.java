package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PurgeCommand;
import seedu.address.logic.commands.meeting.ListCommand.ListMeetingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.meeting.ListMeetingDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_noExtraArguments_displayUpcomingMeetingsSuccessMessage() {
        ListMeetingDescriptor descriptor = new ListMeetingDescriptorBuilder().build();
        expectedModel.updateFilteredMeetingList(Model.PREDICATE_SHOW_UPCOMING_MEETINGS);
        assertCommandSuccess(new ListCommand(descriptor), model, ListCommand.MESSAGE_SUCCESS_UPCOMING, expectedModel);
    }

    @Test
    public void execute_showAllMeetings_displayAllMeetingsSuccessMessage() {
        ListMeetingDescriptor descriptor = new ListMeetingDescriptorBuilder().withShouldShowAll(true).build();
        expectedModel.updateFilteredMeetingList(Model.PREDICATE_SHOW_ALL_MEETINGS);
        assertCommandSuccess(new ListCommand(descriptor), model, ListCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_showAllMeetingsWithContact_displayAllMeetingsSuccessMessage() {
        Index secondPersonIndex = Index.fromOneBased(2);
        Person secondPerson = model.getSortedPersonList().get(secondPersonIndex.getZeroBased());

        ListMeetingDescriptor descriptor =
                new ListMeetingDescriptorBuilder()
                        .withShouldShowAll(true)
                        .withContactIndex(secondPersonIndex)
                        .build();

        expectedModel.updateFilteredMeetingList(meeting -> meeting.getPersonId() == secondPerson.getId());

        assertCommandSuccess(new ListCommand(descriptor), model, ListCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    @Test
    public void execute_showUpcomingMeetingsWithContact_displayUpcomingMeetingsSuccessMessage() {
        Index secondPersonIndex = Index.fromOneBased(2);
        Person secondPerson = model.getSortedPersonList().get(secondPersonIndex.getZeroBased());

        ListMeetingDescriptor descriptor =
                new ListMeetingDescriptorBuilder()
                        .withContactIndex(secondPersonIndex)
                        .build();

        Predicate<Meeting> showUpcomingMeetingsWithContact =
                Model.PREDICATE_SHOW_UPCOMING_MEETINGS.and(meeting -> meeting.getPersonId() == secondPerson.getId());
        expectedModel.updateFilteredMeetingList(showUpcomingMeetingsWithContact);

        assertCommandSuccess(new ListCommand(descriptor), model, ListCommand.MESSAGE_SUCCESS_UPCOMING, expectedModel);
    }

    @Test
    public void execute_invalidContactIndex_exceptionThrown() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedPersonList().size() + 1);
        ListMeetingDescriptor descriptor = new ListMeetingDescriptorBuilder().withContactIndex(outOfBoundIndex).build();
        assertCommandFailure(new ListCommand(descriptor), model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ListMeetingDescriptor descriptor =
                new ListMeetingDescriptorBuilder()
                        .withShouldShowAll(true)
                        .withContactIndex(Index.fromOneBased(2))
                        .build();

        final ListCommand standardCommand = new ListCommand(descriptor);

        // same values -> returns true
        ListMeetingDescriptor copyDescriptor =
                new ListMeetingDescriptorBuilder()
                        .withShouldShowAll(true)
                        .withContactIndex(Index.fromOneBased(2))
                        .build();
        ListCommand commandWithSameValues = new ListCommand(copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PurgeCommand()));

        // different descriptor -> returns false
        ListMeetingDescriptor diffDescriptor =
                new ListMeetingDescriptorBuilder()
                        .withShouldShowAll(false)
                        .withContactIndex(Index.fromOneBased(3))
                        .build();
        assertFalse(standardCommand.equals(new ListCommand(diffDescriptor)));
    }
}
