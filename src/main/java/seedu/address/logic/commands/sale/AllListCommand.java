package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;

/**
 * Lists all sales belonging to a specified contact.
 */
public class AllListCommand extends ListCommand {

    private static final String MESSAGE_SUCCESS_ALL_SALES_PRESENT = "Listing all sales:\n%s";

    private static final String MESSAGE_SUCCESS_CONTACT_SALES_PRESENT = "Sales made to %s:\n%s";

    private static final String MESSAGE_SUCCESS_ALL_SALES_EMPTY = "No sales made!";

    private static final String MESSAGE_SUCCESS_CONTACT_SALES_EMPTY = "No sales made to %s!";

    private final boolean showAll;
    private final Index targetIndex;

    /**
     * Creates an AllListCommand with the following parameters.
     * @param showAll A boolean dictating if all sales should be displayed.
     * @param targetIndex The index of the contact whose sales are to be displayed.
     */
    public AllListCommand(boolean showAll, Index targetIndex) {
        this.showAll = showAll;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> sortedPersonList = model.getSortedPersonList();
        List<Sale> sales = model.getFilteredSaleList();

        if (showAll) {
            model.updateFilteredSaleList(x -> true);

            if (sales.size() == 0) {
                return new CommandResult(MESSAGE_SUCCESS_ALL_SALES_EMPTY);
            }

            String formattedListAsStr = this.formatSaleListOutput(sales);
            return new CommandResult(String.format(MESSAGE_SUCCESS_ALL_SALES_PRESENT, formattedListAsStr));
        } else {
            if (targetIndex.getZeroBased() >= sortedPersonList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToShow = sortedPersonList.get(targetIndex.getZeroBased());
            Predicate<Sale> filterByContact = x -> x.getBuyer().equals(personToShow);

            model.updateFilteredSaleList(filterByContact);

            if (sales.size() == 0) {
                return new CommandResult(String.format(
                        MESSAGE_SUCCESS_CONTACT_SALES_EMPTY, personToShow.getName()));
            }

            String formattedListAsStr = this.formatSaleListOutput(sales);
            return new CommandResult(String.format(
                    MESSAGE_SUCCESS_CONTACT_SALES_PRESENT, personToShow.getName(), formattedListAsStr));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof AllListCommand) {
            if (!showAll) {
                return targetIndex.equals(((AllListCommand) other).targetIndex);
            } else {
                return ((AllListCommand) other).showAll;
            }
        }

        return false;
    }
}
