package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Message;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Adds a reminder associated with a contact to StonksBook.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "reminder add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reminder scheduled on a particular date that "
            + "is associated with the specified contact. "
            + "Parameters: "
            + PREFIX_CONTACT + "CONTACT_INDEX (must be a positive integer) "
            + PREFIX_MESSAGE + "MESSAGE "
            + PREFIX_DATETIME + "DATETIME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT + "2 "
            + PREFIX_MESSAGE + "Send email to follow up "
            + PREFIX_DATETIME + "2020-10-30 15:00";

    public static final String MESSAGE_SUCCESS = "New reminder added: %1$s";
    public static final String MESSAGE_DUPLICATE_REMINDER = "This reminder already exists in StonksBook.";

    /**
     * Index of the Person to be associated with this reminder.
     */
    private final Index index;
    private final Message message;
    private final LocalDateTime scheduledDate;

    /**
     * Creates an AddCommand that adds a {@code Reminder}.
     *
     * @param index         The index of the Person to associate this reminder to.
     * @param message       The message associated with this reminder.
     * @param scheduledDate The scheduled date of the reminder.
     */
    public AddCommand(Index index, Message message, LocalDateTime scheduledDate) {
        requireAllNonNull(index, message, scheduledDate);
        this.index = index;
        this.message = message;
        this.scheduledDate = scheduledDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAssociateWithReminder = lastShownList.get(index.getZeroBased());
        Reminder toAdd = new Reminder(personToAssociateWithReminder, message, scheduledDate);

        if (model.hasReminder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_REMINDER);
        }

        model.addReminder(toAdd);
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
            && scheduledDate.equals(otherAddCommand.scheduledDate);
    }
}
