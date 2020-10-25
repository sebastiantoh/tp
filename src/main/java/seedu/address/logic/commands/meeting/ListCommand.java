package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_MEETINGS;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Lists all meetings in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "meeting list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of meetings.\n"
            + "Parameters: "
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX (must be a positive integer)] "
            + "[" + PREFIX_SHOW_ALL + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT + "3";

    public static final String MESSAGE_SUCCESS_UPCOMING = "Listed all upcoming meetings.";

    public static final String MESSAGE_SUCCESS_ALL = "Listed all meetings.";

    private final ListMeetingDescriptor listMeetingDescriptor;

    /**
     * Creates a ListCommand that displays the list of meetings.
     *
     * @param listMeetingDescriptor Details of how the ListCommand should filter the meetings list.
     */
    public ListCommand(ListMeetingDescriptor listMeetingDescriptor) {
        this.listMeetingDescriptor = listMeetingDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!listMeetingDescriptor.isAnyFieldPresent()) {
            model.updateFilteredMeetingList(PREDICATE_SHOW_UPCOMING_MEETINGS);
            return new CommandResult(MESSAGE_SUCCESS_UPCOMING);
        }

        Predicate<Meeting> predicate;

        boolean shouldShowAll = listMeetingDescriptor.getShouldShowAll().orElse(false);
        if (shouldShowAll) {
            predicate = meeting -> true;
        } else {
            predicate = PREDICATE_SHOW_UPCOMING_MEETINGS;
        }

        Optional<Index> contactIndex = listMeetingDescriptor.getContactIndex();
        if (contactIndex.isPresent()) {
            List<Person> lastShownList = model.getSortedPersonList();

            Index index = contactIndex.get();
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToShow = lastShownList.get(index.getZeroBased());

            Predicate<Meeting> filterByContact = meeting -> meeting.getPersonId() == personToShow.getId();
            predicate = predicate.and(filterByContact);
        }

        model.updateFilteredMeetingList(predicate);

        return new CommandResult(shouldShowAll ? MESSAGE_SUCCESS_ALL : MESSAGE_SUCCESS_UPCOMING);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        // state check
        ListCommand otherListCommand = (ListCommand) other;
        return listMeetingDescriptor.equals(otherListCommand.listMeetingDescriptor);
    }

    /**
     * Stores the details on how the ListCommand should filter the meeting list.
     */
    public static class ListMeetingDescriptor {
        private Index contactIndex;
        private Boolean shouldShowAll;

        public ListMeetingDescriptor() {
        }

        /**
         * A constructor that is used to create a Copy of the provided @{code ListMeetingDescriptor}.
         */
        public ListMeetingDescriptor(ListMeetingDescriptor toCopy) {
            assert toCopy != null;
            setContactIndex(toCopy.contactIndex);
            setShouldShowAll(toCopy.shouldShowAll);
        }

        /**
         * Returns true if at least one field is present.
         */
        public boolean isAnyFieldPresent() {
            return CollectionUtil.isAnyNonNull(contactIndex, shouldShowAll);
        }

        public void setContactIndex(Index contactIndex) {
            this.contactIndex = contactIndex;
        }

        public Optional<Index> getContactIndex() {
            return Optional.ofNullable(contactIndex);
        }

        public void setShouldShowAll(boolean shouldShowAll) {
            this.shouldShowAll = shouldShowAll;
        }

        public Optional<Boolean> getShouldShowAll() {
            return Optional.ofNullable(shouldShowAll);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof ListMeetingDescriptor)) {
                return false;
            }

            // state check
            ListMeetingDescriptor e = (ListMeetingDescriptor) other;

            return getContactIndex().equals(e.getContactIndex())
                    && getShouldShowAll().equals(e.getShouldShowAll());
        }
    }
}
