package seedu.address.logic.commands.sale;

import java.time.Month;
import java.time.Year;
import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.sale.Sale;

public class MonthlyListCommand extends ListCommand {

    private static final String MESSAGE_SUCCESS = "You have sold %d items in %s %s!\n%s";
    private final Month month;

    private final Year year;

    public MonthlyListCommand(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public CommandResult execute(Model model) {
        List<Sale> monthlySaleList = model.getMonthlySaleList(month, year);
        StringBuilder output = new StringBuilder();

        int index = 1;
        for (Sale sale : monthlySaleList) {
            output.append(index).append(". ").append(sale).append("\n");
            index++;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, monthlySaleList.size(),month, year, output));
    }
}
