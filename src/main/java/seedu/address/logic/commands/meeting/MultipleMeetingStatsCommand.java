package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.util.List;

import seedu.address.commons.MonthlyCountData;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Gets multiple monthly meeting counts
 */
public class MultipleMeetingStatsCommand extends StatsCommand {

    public static final String MESSAGE_SUCCESS = "Opened a new window!";

    private final int numberOfMonths;

    /**
     * Creates a MultipleMeetingStatsCommand with the given {@code numberOfMonths}.
     */
    public MultipleMeetingStatsCommand(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    /**
     * Gets multiple monthly meeting counts for months between
     * the current month and the previous numberOfMonths - 1 months inclusive.
     *
     * @param model {@code Model} which the command should operate on
     * @return CommandResult object storing the success message
     * and the multiple monthly count result
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Month currentMonth = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year currentYear = Year.now();

        List<MonthlyCountData> result = model
                .getMultipleMonthMeetingsCount(currentMonth, currentYear, numberOfMonths);

        return new CommandResult(MESSAGE_SUCCESS, result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MultipleMeetingStatsCommand
                && numberOfMonths == (((MultipleMeetingStatsCommand) other).numberOfMonths));
    }
}
