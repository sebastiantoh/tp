package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.date.MonthlyCountData;

/**
 * Statistics on Sales.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "sale stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Outputs statistics on sales.\n"
            + "Parameters: NUMBER_OF_MONTHS "
            + "Example: " + COMMAND_WORD + " 5";

    public static final String MESSAGE_SUCCESS = "Opened a new window!";

    public static final String DATASET_TITLE = "Sale Count";

    private final int numberOfMonths;

    /**
     * Creates a StatsCommand with the given {@code numberOfMonths}.
     */
    public StatsCommand(int numberOfMonths) {
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

        DataSet<MonthlyCountData> result = model
                .getMultipleMonthSaleCount(currentMonth, currentYear, numberOfMonths);

        result.setTitle(DATASET_TITLE);
        return new CommandResult(MESSAGE_SUCCESS, result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StatsCommand
                && numberOfMonths == (((StatsCommand) other).numberOfMonths));
    }
}
