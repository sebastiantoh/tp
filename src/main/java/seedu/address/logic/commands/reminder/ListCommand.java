package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Lists all reminders in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "reminder list";

    public static final String MESSAGE_SUCCESS = "Listed all reminders";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Reminder> reminders = model.getRemindersList();

        String output;
        if (reminders.size() == 0) {
            output = "No reminders found!";
        } else {
            output = IntStream
                    .range(0, reminders.size())
                    .mapToObj(i -> String.format("%d. %s", Index.fromZeroBased(i).getOneBased(), reminders.get(i)))
                    .collect(Collectors.joining("\n"));
        }


        return new CommandResult(output);
    }
}
