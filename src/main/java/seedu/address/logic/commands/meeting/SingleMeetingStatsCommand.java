package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Gets single month meeting count
 */
public class SingleMeetingStatsCommand extends StatsCommand {
    private final Month month;

    private final Year year;

    public static final String MESSAGE_SUCCESS = "The number of meeting "
            + "started in %1$s %2$s is %3$s.";

    /**
     * Creates a SingleMeetingStatsCommand with the current month and year.
     */
    public SingleMeetingStatsCommand() {
        this.month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        this.year = Year.now();
    }

    /**
     * Creates a SingleMeetingStatsCommand with the given {@code month} and {@code year}.
     */
    public SingleMeetingStatsCommand(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    /**
     * Gets the number of meetings started in a particular month
     * stated by the attributes month and year.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult object storing the result string
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        int result = model.getMonthMeetingsCount(month, year);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                month.name(), year.getValue(), result));
    }
}
