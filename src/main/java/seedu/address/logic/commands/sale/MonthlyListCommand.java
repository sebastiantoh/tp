package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;

import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.sale.Sale;

/**
 * Lists sale items for a particular month and year.
 */
public class MonthlyListCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = "You have sold %d items in %s %s!";

    private static final Comparator<Sale> SORT_BY_DATE = Comparator.comparing(Sale::getDatetimeOfPurchase);

    private final Month month;

    private final Year year;

    /**
     * Creates a MonthlyListCommand object with the given {@code month} and {@code year}.
     */
    public MonthlyListCommand(Month month, Year year) {
        requireNonNull(month);
        requireNonNull(year);

        this.month = month;
        this.year = year;
    }

    /**
     * Returns the String representation of the monthly sale list
     * based on the {@code month} and {@code year},
     * and sorts the list by the sale date.
     *
     * @param model {@code Model} which the command should operate on.
     * @return formatted String representation of the monthly sale list.
     */
    @Override
    public CommandResult execute(Model model) {
        List<Sale> monthlySaleList = model.getMonthlySaleList(month, year);

        model.updateFilteredSaleList(monthlySaleList::contains);
        model.updateSortedSaleList(SORT_BY_DATE);

        return new CommandResult(String.format(MESSAGE_SUCCESS, monthlySaleList.size(), month, year), false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MonthlyListCommand // instanceof handles nulls
                && month.equals(((MonthlyListCommand) other).month)
                && year.equals(((MonthlyListCommand) other).year));
    }
}
