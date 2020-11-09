package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_STATUS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Message;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Edits the details of an existing reminder in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "reminder edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the reminder identified "
            + "by the index number used in the displayed reminder list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CONTACT + "CONTACT_INDEX (must be a positive integer)] "
            + "[" + PREFIX_MESSAGE + "MESSAGE] "
            + "[" + PREFIX_DATETIME + "DATETIME] "
            + "[" + PREFIX_REMINDER_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MESSAGE + "Follow up with Bob "
            + PREFIX_DATETIME + "2020-11-30 12:30";

    public static final String MESSAGE_EDIT_REMINDER_SUCCESS = "Edited Reminder: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_REMINDER = "This reminder already exists in the address book.";

    private final Index index;
    private final EditReminderDescriptor editReminderDescriptor;

    /**
     * Constructs a new EditCommand for Reminder.
     *
     * @param index                  of the reminder in the filtered reminder list to edit.
     * @param editReminderDescriptor details to edit the reminder with.
     */
    public EditCommand(Index index, EditReminderDescriptor editReminderDescriptor) {
        requireNonNull(index);
        requireNonNull(editReminderDescriptor);

        this.index = index;
        this.editReminderDescriptor = new EditReminderDescriptor(editReminderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reminder> lastShownList = model.getSortedReminderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder reminderToEdit = lastShownList.get(index.getZeroBased());
        Reminder editedReminder = createEditedReminder(model, reminderToEdit, editReminderDescriptor);

        if (!reminderToEdit.equals(editedReminder) && model.hasReminder(editedReminder)) {
            throw new CommandException(MESSAGE_DUPLICATE_REMINDER);
        }

        model.setReminder(reminderToEdit, editedReminder);
        return new CommandResult(String.format(MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder));
    }

    /**
     * Creates and returns a {@code Reminder} with the details of {@code reminderToEdit}
     * edited with {@code editReminderDescriptor}.
     */
    private static Reminder createEditedReminder(Model model, Reminder reminderToEdit,
                                                 EditReminderDescriptor editReminderDescriptor)
            throws CommandException {
        assert model != null;
        assert reminderToEdit != null;
        assert editReminderDescriptor != null;


        Person updatedPerson = reminderToEdit.getPerson();
        if (editReminderDescriptor.getContactIndex().isPresent()) {
            Index contactIndex = editReminderDescriptor.getContactIndex().get();
            List<Person> lastShownPeople = model.getSortedPersonList();

            if (editReminderDescriptor.getContactIndex().get().getZeroBased() >= lastShownPeople.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            updatedPerson = lastShownPeople.get(contactIndex.getZeroBased());
        }

        Message updatedMessage = editReminderDescriptor.getMessage().orElse(reminderToEdit.getMessage());
        LocalDateTime updatedScheduledDate =
                editReminderDescriptor.getScheduledDate().orElse(reminderToEdit.getScheduledDate());
        boolean updatedCompletionStatus =
                editReminderDescriptor.isCompleted() == null
                        ? reminderToEdit.isCompleted()
                        : editReminderDescriptor.isCompleted();

        return new Reminder(updatedPerson, updatedMessage, updatedScheduledDate, updatedCompletionStatus);
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
                && editReminderDescriptor.equals(e.editReminderDescriptor);
    }

    /**
     * Stores the details to edit the reminder with. Each non-empty field value will replace the
     * corresponding field value of the reminder.
     */
    public static class EditReminderDescriptor {
        private Index contactIndex;
        private Message message;
        private LocalDateTime scheduledDate;
        private Boolean completed;

        public EditReminderDescriptor() {
        }

        /**
         * A constructor that is used to create a Copy of the provided @{code EditReminderDescriptor}.
         */
        public EditReminderDescriptor(EditReminderDescriptor toCopy) {
            assert toCopy != null;
            setContactIndex(toCopy.contactIndex);
            setMessage(toCopy.message);
            setScheduledDate(toCopy.scheduledDate);
            setCompleted(toCopy.completed);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(contactIndex, message, scheduledDate, completed);
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

        public void setCompleted(Boolean completed) {
            this.completed = completed;
        }

        public Optional<Message> getMessage() {
            return Optional.ofNullable(message);
        }

        public void setScheduledDate(LocalDateTime scheduledDate) {
            this.scheduledDate = scheduledDate;
        }

        public Optional<LocalDateTime> getScheduledDate() {
            return Optional.ofNullable(scheduledDate);
        }

        public Boolean isCompleted() {
            return completed;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditReminderDescriptor)) {
                return false;
            }

            // state check
            EditReminderDescriptor e = (EditReminderDescriptor) other;

            return getContactIndex().equals(e.getContactIndex())
                    && getMessage().equals(e.getMessage())
                    && getScheduledDate().equals(e.getScheduledDate())
                    && isCompleted() == e.isCompleted();
        }
    }
}
