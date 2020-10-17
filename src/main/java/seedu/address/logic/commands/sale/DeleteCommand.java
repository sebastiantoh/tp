package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;

import java.math.BigDecimal;
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
 * Deletes a sale based on its displayed index in the sale list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "sale delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the sale identified by the index number used in the displayed sale list.\n"
        + "Parameters: SALE_INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_DELETE_SALE_SUCCESS = "Deleted Sale: %1$s";

    public static final String MESSAGE_NO_SALES_DISPLAYED = "No sales displayed, use `sale list` "
            + "to display sales before executing the `sale delete` command";

    private final Index saleIndex;

    /**
     * Creates an DeleteCommand that removes a sale from a specified contact.
     * @param saleIndex Index of the sale to be removed.
     */
    public DeleteCommand(Index saleIndex) {
        this.saleIndex = saleIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Sale> sales = model.getFilteredSaleList();
        List<Person> people = model.getSortedPersonList();
        if (model.getSortedSaleList().size() > sales.size() && model.getFilteredSaleList().size() == 0) {
            throw new CommandException(MESSAGE_NO_SALES_DISPLAYED);
        }
        if (saleIndex.getZeroBased() >= sales.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX);
        }

        Sale saleToDelete = sales.get(saleIndex.getZeroBased());
        Person personToEdit = people.stream()
                .filter(person -> person.getId().equals(saleToDelete.getBuyerId()))
                .findAny()
                .orElse(null);
        assert personToEdit != null;

        BigDecimal newTotalSalesAmount = personToEdit.getTotalSalesAmount().subtract(saleToDelete.getTotalCost());

        Person editedPerson = new Person(personToEdit.getId(), personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getAddress(), personToEdit.getTags(),
                personToEdit.getRemark(), newTotalSalesAmount);

        model.removeSale(saleToDelete);
        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_DELETE_SALE_SUCCESS, saleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && saleIndex.equals(((DeleteCommand) other).saleIndex)); // state check
    }
}
