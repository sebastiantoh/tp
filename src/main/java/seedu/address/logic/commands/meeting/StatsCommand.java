package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.Month;
import java.time.Year;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;


/**
 * Gets statistics on meetings
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "meeting stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Outputs statistics on meetings.\n"
            + "Parameters: "
            + "[" + PREFIX_MONTH + "MONTH "
            + PREFIX_YEAR + "YEAR] "
            + "Example: " + COMMAND_WORD + " m/10 y/2020";

    public static final String MESSAGE_SUCCESS = "The number of meeting started in %1$s %2$s is %3$s.";

    public final Month month;

    public final Year year;

    public StatsCommand(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        int result = model.getMonthMeetingsCount(month, year);
        return new CommandResult(String.format(MESSAGE_SUCCESS, month.name(), year.getValue(), result));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsCommand // instanceof handles nulls
                && month.equals(((StatsCommand) other).month)
                && year.equals(((StatsCommand) other).year));
    }
}
