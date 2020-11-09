package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Message;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Edits the details of an existing meeting in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "meeting edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the meeting identified "
            + "by the index number used in the displayed meeting list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX (must be a positive integer)] "
            + "[" + PREFIX_MESSAGE + "MESSAGE] "
            + "[" + PREFIX_DATETIME + "START_DATETIME] "
            + "[" + PREFIX_DURATION + "DURATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATETIME + "2020-11-30 12:30 "
            + PREFIX_DURATION + "90";

    public static final String MESSAGE_EDIT_MEETING_SUCCESS = "Edited Meeting: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book.";
    public static final String MESSAGE_CONFLICTING_MEETING =
            "Editing this meeting would create a conflicts with the following other meeting(s)!\n%s";

    private final Index index;
    private final EditMeetingDescriptor editMeetingDescriptor;

    /**
     * Constructs a new EditCommand for Meeting.
     *
     * @param index                 of the meeting in the filtered meeting list to edit.
     * @param editMeetingDescriptor details to edit the meeting with.
     */
    public EditCommand(Index index, EditMeetingDescriptor editMeetingDescriptor) {
        requireNonNull(index);
        requireNonNull(editMeetingDescriptor);

        this.index = index;
        this.editMeetingDescriptor = new EditMeetingDescriptor(editMeetingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Meeting> lastShownList = model.getSortedMeetingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToEdit = lastShownList.get(index.getZeroBased());
        Meeting editedMeeting = createEditedMeeting(model, meetingToEdit, editMeetingDescriptor);

        if (!meetingToEdit.equals(editedMeeting) && model.hasMeeting(editedMeeting)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        List<Meeting> conflictingMeetings = model.getConflictingMeetings(editedMeeting, meetingToEdit);
        if (conflictingMeetings.size() != 0) {
            String formattedConflictingMeetings =
                    conflictingMeetings.stream()
                            .map(meeting -> "- " + meeting.toString())
                            .collect(Collectors.joining("\n"));

            throw new CommandException(String.format(MESSAGE_CONFLICTING_MEETING, formattedConflictingMeetings));
        }

        model.setMeeting(meetingToEdit, editedMeeting);
        return new CommandResult(String.format(MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting));
    }

    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Model model, Meeting meetingToEdit,
                                               EditMeetingDescriptor editMeetingDescriptor) throws CommandException {
        assert model != null;
        assert meetingToEdit != null;
        assert editMeetingDescriptor != null;

        Person updatedPerson = meetingToEdit.getPerson();
        if (editMeetingDescriptor.getContactIndex().isPresent()) {
            Index contactIndex = editMeetingDescriptor.getContactIndex().get();
            List<Person> lastShownPeople = model.getSortedPersonList();

            if (editMeetingDescriptor.getContactIndex().get().getZeroBased() >= lastShownPeople.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            updatedPerson = lastShownPeople.get(contactIndex.getZeroBased());
        }

        Message updatedMessage = editMeetingDescriptor.getMessage().orElse(meetingToEdit.getMessage());
        LocalDateTime updatedStartDate =
                editMeetingDescriptor.getStartDate().orElse(meetingToEdit.getStartDate());
        Duration updatedDuration = editMeetingDescriptor.getDuration().orElse(meetingToEdit.getDuration());

        return new Meeting(updatedPerson, updatedMessage, updatedStartDate, updatedDuration);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editMeetingDescriptor.equals(e.editMeetingDescriptor);
    }

    /**
     * Stores the details to edit the meeting with. Each non-empty field value will replace the
     * corresponding field value of the meeting.
     */
    public static class EditMeetingDescriptor {
        private Index contactIndex;
        private Message message;
        private LocalDateTime startDate;
        private Duration duration;

        public EditMeetingDescriptor() {
        }

        /**
         * A constructor that is used to create a Copy of the provided @{code EditMeetingDescriptor}.
         */
        public EditMeetingDescriptor(EditMeetingDescriptor toCopy) {
            assert toCopy != null;
            setContactIndex(toCopy.contactIndex);
            setMessage(toCopy.message);
            setStartDate(toCopy.startDate);
            setDuration(toCopy.duration);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(contactIndex, message, startDate, duration);
        }

        public void setContactIndex(Index contactIndex) {
            this.contactIndex = contactIndex;
        }

        public Optional<Index> getContactIndex() {
            return Optional.ofNullable(contactIndex);
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public Optional<Message> getMessage() {
            return Optional.ofNullable(message);
        }

        public void setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
        }

        public Optional<LocalDateTime> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMeetingDescriptor)) {
                return false;
            }

            // state check
            EditMeetingDescriptor e = (EditMeetingDescriptor) other;

            return getContactIndex().equals(e.getContactIndex())
                    && getMessage().equals(e.getMessage())
                    && getStartDate().equals(e.getStartDate())
                    && getDuration().equals(e.getDuration());
        }
    }
}
