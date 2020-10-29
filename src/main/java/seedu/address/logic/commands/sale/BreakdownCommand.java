package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.tag.SaleTagCountData;

/**
 * Statistics on Sales
 */
public class BreakdownCommand extends Command {

    public static final String COMMAND_WORD = "sale breakdown";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Outputs breakdown of sales categories.\n"
            + "Parameters: NUMBER_OF_MONTHS "
            + "Example: " + COMMAND_WORD + " 5";

    public static final String MESSAGE_SUCCESS = "Opened a new window!";

    public static final String DATASET_TITLE = "Breakdown of Sales by Sale Tags (Top 5)";


    /**
     * Creates a BreakdownCommand.
     */
    public BreakdownCommand() {
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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        DataSet<SaleTagCountData> result = model.getSaleTagCount();

        result.getDataList().forEach(x -> System.out.println(x.getKeyAsStr() + " " + x.getCount()));

        if (result.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_DATASET);
        }

        result.setTitle(DATASET_TITLE);
        return new CommandResult(MESSAGE_SUCCESS, result);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof BreakdownCommand);
    }
}
