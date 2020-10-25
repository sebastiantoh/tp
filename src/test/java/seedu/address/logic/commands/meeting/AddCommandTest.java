package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.TYPICAL_DATE_1;
import static seedu.address.testutil.TypicalDates.TYPICAL_DATE_2;
import static seedu.address.testutil.TypicalDurations.TYPICAL_DURATION_HALF_HOUR;
import static seedu.address.testutil.TypicalDurations.TYPICAL_DURATION_ONE_HOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Message;
import seedu.address.model.ModelStub;
import seedu.address.model.ModelStubWithSortedPersonList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.person.PersonBuilder;

public class AddCommandTest {
    private static final Message MESSAGE = new Message("message");

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddCommand(null, MESSAGE, TYPICAL_DATE_1, TYPICAL_DURATION_ONE_HOUR));
    }

    @Test
    public void constructor_nullMessage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_FIRST_ITEM, null, TYPICAL_DATE_1, TYPICAL_DURATION_ONE_HOUR));
    }

    @Test
    public void constructor_nullStartDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_FIRST_ITEM, MESSAGE, null, TYPICAL_DURATION_ONE_HOUR));
    }

    @Test
    public void constructor_nullDuration_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddCommand(INDEX_FIRST_ITEM, MESSAGE, TYPICAL_DATE_1, null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMeetingAdded modelStub = new ModelStubAcceptingMeetingAdded();
        Person validPerson = new PersonBuilder(BOB).build();

        Meeting meeting = new Meeting(validPerson, MESSAGE, TYPICAL_DATE_1, TYPICAL_DURATION_ONE_HOUR);
        CommandResult commandResult =
                new AddCommand(INDEX_FIRST_ITEM, MESSAGE, TYPICAL_DATE_1, TYPICAL_DURATION_ONE_HOUR)
                        .execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, meeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(meeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Person validPerson = new PersonBuilder(BOB).build();
        Meeting meeting = new Meeting(validPerson, MESSAGE, TYPICAL_DATE_1, TYPICAL_DURATION_ONE_HOUR);
        ModelStub modelStub = new ModelStubWithMeeting(meeting);

        AddCommand addCommand = new AddCommand(INDEX_FIRST_ITEM, MESSAGE, TYPICAL_DATE_1, TYPICAL_DURATION_ONE_HOUR);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_MEETING, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Message lunchWithAlice = new Message("Lunch with Alice");
        AddCommand addLunchAliceMeetingCommand =
                new AddCommand(INDEX_FIRST_ITEM, lunchWithAlice, TYPICAL_DATE_1,
                        TYPICAL_DURATION_ONE_HOUR);

        // same object -> returns true
        assertEquals(addLunchAliceMeetingCommand, addLunchAliceMeetingCommand);

        // same values -> returns true
        AddCommand addLunchAliceMeetingCommandCopy =
                new AddCommand(INDEX_FIRST_ITEM, lunchWithAlice, TYPICAL_DATE_1,
                        TYPICAL_DURATION_ONE_HOUR);
        assertEquals(addLunchAliceMeetingCommand, addLunchAliceMeetingCommandCopy);

        // different types -> returns false
        assertNotEquals(addLunchAliceMeetingCommand, 1);

        // null -> returns false
        assertNotEquals(addLunchAliceMeetingCommand, null);

        // different index -> returns false
        assertNotEquals(addLunchAliceMeetingCommand,
                new AddCommand(INDEX_SECOND_ITEM, lunchWithAlice, TYPICAL_DATE_1,
                        TYPICAL_DURATION_ONE_HOUR));

        // different message -> returns false
        assertNotEquals(addLunchAliceMeetingCommand,
                new AddCommand(INDEX_FIRST_ITEM, new Message("Dinner with Alice"), TYPICAL_DATE_1,
                        TYPICAL_DURATION_ONE_HOUR));

        // different date -> returns false
        assertNotEquals(addLunchAliceMeetingCommand,
                new AddCommand(INDEX_FIRST_ITEM, lunchWithAlice, TYPICAL_DATE_2,
                        TYPICAL_DURATION_ONE_HOUR));

        // different duration -> returns false
        assertNotEquals(addLunchAliceMeetingCommand,
                new AddCommand(INDEX_FIRST_ITEM, lunchWithAlice, TYPICAL_DATE_1,
                        TYPICAL_DURATION_HALF_HOUR));
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends ModelStubWithSortedPersonList {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::equals);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public List<Meeting> getConflictingMeetings(Meeting meeting, Meeting... meetingsToExclude) {
            return new ArrayList<>();
        }
    }

    /**
     * A Model stub that contains a single meeting.
     */
    private class ModelStubWithMeeting extends ModelStubWithSortedPersonList {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.equals(meeting);
        }
    }
}
