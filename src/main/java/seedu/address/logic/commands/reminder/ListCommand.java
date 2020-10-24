package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all reminders in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "reminder list";

    public static final String MESSAGE_SUCCESS = "Listed all reminders";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredRemindersList(reminder -> true);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand; // instanceof handles nulls
    }
}
