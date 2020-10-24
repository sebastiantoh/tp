package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_STATUS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
    public static final String MESSAGE_DUPLICATE_REMINDER = "This reminder already exists in the address book.";

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
}
