package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Message;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Adds a meeting associated with a contact to StonksBook.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "meeting add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a scheduled meeting with the given duration "
            + "that is associated with the specified contact. "
            + "Parameters: "
            + PREFIX_CONTACT + "CONTACT_INDEX (must be a positive integer) "
            + PREFIX_MESSAGE + "MESSAGE "
            + PREFIX_DATETIME + "START_DATETIME "
            + PREFIX_DURATION + "DURATION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT + "2 "
            + PREFIX_MESSAGE + "Product Demo "
            + PREFIX_DATETIME + "2020-10-30 15:00 "
            + PREFIX_DURATION + "30";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in StonksBook.";
    public static final String MESSAGE_CONFLICTING_MEETING =
            "This meeting conflicts with the following meeting(s)!\n%s";

    /**
     * Index of the Person to be associated with this meeting.
     */
    private final Index index;
    private final Message message;
    private final LocalDateTime startDate;
    private final Duration duration;

    /**
     * Creates an AddCommand that adds a {@code Meeting}.
     *
     * @param index     The index of the Person to associate this Meeting to.
     * @param message   The message associated with this Meeting.
     * @param startDate The start date of the Meeting.
     * @param duration  The duration of the Meeting.
     */
    public AddCommand(Index index, Message message, LocalDateTime startDate, Duration duration) {
        requireAllNonNull(index, message, startDate, duration);
        this.index = index;
        this.message = message;
        this.startDate = startDate;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAssociateWithMeeting = lastShownList.get(index.getZeroBased());
        Meeting toAdd = new Meeting(personToAssociateWithMeeting, message, startDate, duration);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        List<Meeting> conflictingMeetings = model.getConflictingMeetings(toAdd);
        if (conflictingMeetings.size() != 0) {
            String formattedConflictingMeetings =
                    conflictingMeetings.stream()
                            .map(meeting -> "- " + meeting.toString())
                            .collect(Collectors.joining("\n"));

            throw new CommandException(String.format(MESSAGE_CONFLICTING_MEETING, formattedConflictingMeetings));

        }

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        // state check
        AddCommand otherAddCommand = (AddCommand) other;
        return index.equals(otherAddCommand.index)
                && message.equals(otherAddCommand.message)
                && startDate.equals(otherAddCommand.startDate)
                && duration.equals(otherAddCommand.duration);
    }
}
