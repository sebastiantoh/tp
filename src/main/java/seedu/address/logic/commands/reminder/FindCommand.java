package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Finds reminders based on completion status.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "reminder find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find reminders based on completion status\n"
            + "Parameters: STATUS (must be either completed or pending) "
            + "Example: " + COMMAND_WORD + " st/completed ";

    public static final String MESSAGE_FIND_REMINDER_SUCCESS = "Listed all %1$s reminders.";
    public static final String MESSAGE_NOT_FOUND = "No %1$s reminders found.";

    private final Boolean isCompleted;

    /**
     * Instantiates a new FindCommand for reminders.
     *
     * @param isCompleted indicates if the reminder is completed.
     */
    public FindCommand(Boolean isCompleted) {
        requireNonNull(isCompleted);

        this.isCompleted = isCompleted;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isCompleted) {
            model.updateFilteredRemindersList(Reminder::isCompleted);
            return new CommandResult(String.format(MESSAGE_FIND_REMINDER_SUCCESS, "completed"));
        } else {
            model.updateFilteredRemindersList(reminder -> !reminder.isCompleted());
            return new CommandResult(String.format(MESSAGE_FIND_REMINDER_SUCCESS, "pending"));
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        // state check
        FindCommand f = (FindCommand) other;
        return isCompleted.equals(f.isCompleted);
    }
}
