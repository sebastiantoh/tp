package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        + ": Deletes the sale(s) identified by the index number used in the displayed sale list.\n"
        + "Parameters: " + PREFIX_SALE_INDEX + " SALE_INDEX... (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_SALE_INDEX + " 3";

    public static final String MESSAGE_DELETE_SALE_SUCCESS = "Deleted Sale(s):";

    public static final String MESSAGE_NO_SALES_DISPLAYED = "No sales displayed, use `sale list` "
            + "to display sales before executing the `sale delete` command";

    private final List<Index> saleIndexes;

    /**
     * Creates an DeleteCommand that removes a sale from a specified contact.
     * @param saleIndexes Indexes of the sales to be removed.
     */
    public DeleteCommand(List<Index> saleIndexes) {
        this.saleIndexes = saleIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Sale> sales = model.getSortedSaleList();
        List<Person> people = model.getSortedPersonList();

        if (model.getSortedSaleList().size() == 0) {
            throw new CommandException(MESSAGE_NO_SALES_DISPLAYED);
        }

        List<Index> invalidIndexes = saleIndexes
                .parallelStream().filter(personIndex -> personIndex.getZeroBased() >= sales.size())
                .collect(Collectors.toList());

        if (!invalidIndexes.isEmpty()) {
            throw new CommandException(MassSaleCommandUtil.generateInvalidIndexMessage(
                    Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX, invalidIndexes));
        }

        List<Sale> deletedSales = new ArrayList<>();
        List<Person> previousPersons = new ArrayList<>();
        List<Person> editedPersons = new ArrayList<>();
        for (Index saleIndex : saleIndexes) {
            Sale saleToDelete = sales.get(saleIndex.getZeroBased());
            Person personToEdit = people.stream()
                    .filter(person -> person.equals(saleToDelete.getBuyer()))
                    .findAny()
                    .orElse(null);
            assert personToEdit != null;

            BigDecimal newTotalSalesAmount = personToEdit.getTotalSalesAmount().subtract(saleToDelete.getTotalCost());

            Person editedPerson = new Person(personToEdit.getId(), personToEdit.getName(), personToEdit.getPhone(),
                    personToEdit.getEmail(), personToEdit.getAddress(), personToEdit.getTags(),
                    personToEdit.getRemark(), personToEdit.isArchived(), newTotalSalesAmount);

            deletedSales.add(saleToDelete);
            previousPersons.add(personToEdit);
            editedPersons.add(editedPerson);
        }

        for (int i = 0; i < deletedSales.size(); i++) {
            model.removeSale(deletedSales.get(i));
            model.setPerson(previousPersons.get(i), editedPersons.get(i));
        }

        return new CommandResult(String.format(generateSuccessMessage(deletedSales)), false, true);
    }

    private String generateSuccessMessage(List<Sale> deletedSales) {
        return MESSAGE_DELETE_SALE_SUCCESS + MassSaleCommandUtil.listAllSales(deletedSales);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && saleIndexes.equals(((DeleteCommand) other).saleIndexes)); // state check
    }
}
