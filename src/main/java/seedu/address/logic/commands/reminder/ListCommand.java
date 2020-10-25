package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_STATUS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Lists reminders in the address book to the user based on the completion status provided.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "reminder list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List reminders based on completion status\n"
            + "Parameters: "
            + "[" + PREFIX_REMINDER_STATUS + "STATUS (must be either completed or pending)]\n"
            + "Example: " + COMMAND_WORD + " st/completed ";

    public static final String MESSAGE_LIST_ALL_SUCCESS = "Listed all reminders!";

    public static final String MESSAGE_LIST_REMINDER_SUCCESS = "Listed %d %s reminders!";

    private final Boolean isCompleted;

    /**
     * Instantiates a new ListCommand for reminders.
     * @param isCompleted True if the reminder is completed.
     */
    public ListCommand(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (isCompleted == null) {
            model.updateFilteredRemindersList(reminder -> true);
            return new CommandResult(MESSAGE_LIST_ALL_SUCCESS);
        } else if (isCompleted) {
            model.updateFilteredRemindersList(Reminder::isCompleted);
            return new CommandResult(String.format(MESSAGE_LIST_REMINDER_SUCCESS,
                    model.getFilteredReminderList().size(),
                    "completed"));
        } else {
            model.updateFilteredRemindersList(reminder -> !reminder.isCompleted());
            return new CommandResult(String.format(MESSAGE_LIST_REMINDER_SUCCESS,
                    model.getFilteredReminderList().size(),
                    "pending"));
        }
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
        ListCommand c = (ListCommand) other;

        if (isCompleted == null) {
            return c.isCompleted == null;
        }
        return isCompleted.equals(c.isCompleted);
    }
}
