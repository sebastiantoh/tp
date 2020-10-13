package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;

/**
 * Lists all sales belonging to a specified contact.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "sale list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of all sales to a specified contact.\n"
            + "Parameters: "
            + PREFIX_SALE_CONTACT_INDEX + "CONTACT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SALE_CONTACT_INDEX + "3 ";

    private final Index targetIndex;

    public ListCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getSortedPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShow = lastShownList.get(targetIndex.getZeroBased());

        List<Sale> sales = personToShow.getSalesList().asUnmodifiableObservableList();

        StringBuilder output = new StringBuilder("Sales made to " + personToShow.getName() + ":\n");

        if (sales.size() == 0) {
            return new CommandResult("No sales made to " + personToShow.getName() + "!");
        }

        int index = 1;
        for (Sale sale : personToShow.getSalesList().asUnmodifiableObservableList()) {
            output.append(index).append(". ").append(sale.toString()).append("\n");
            index++;
        }

        return new CommandResult(output.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && targetIndex.equals(((ListCommand) other).targetIndex)); // state check
    }
}
