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
 * Deletes a sale based on its displayed index in the sale list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "sale delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the sale belonging to specified contact, "
        + "identified by the index number used in the displayed sale list.\n"
        + "Parameters: CONTACT_INDEX (must be a positive integer) "
        + "SALE_INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_SALE_CONTACT_INDEX + "2 "
        + PREFIX_SALE_INDEX + "1";

    public static final String MESSAGE_DELETE_SALE_SUCCESS = "Deleted Sale: %1$s";

    private final Index contactIndex;
    private final Index saleIndex;

    /**
     * Creates an AddCommand that removes a sale from a specified contact.
     * @param contactIndex Index of the contact whose sale is to be removed.
     * @param saleIndex Index of the sale to be removed.
     */
    public DeleteCommand(Index contactIndex, Index saleIndex) {
        this.contactIndex = contactIndex;
        this.saleIndex = saleIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> people = model.getSortedPersonList();

        if (contactIndex.getZeroBased() >= people.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = people.get(contactIndex.getZeroBased());
        List<Sale> sales = personToEdit.getSalesList().asUnmodifiableObservableList();

        if (saleIndex.getZeroBased() >= sales.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX);
        }

        Sale saleToDelete = sales.get(saleIndex.getZeroBased());

        model.removeSaleFromPerson(personToEdit, saleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SALE_SUCCESS, saleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && contactIndex.equals(((DeleteCommand) other).contactIndex)
            && saleIndex.equals(((DeleteCommand) other).saleIndex)); // state check
    }
}
