package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contact.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UniqueSaleList;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.sale.exceptions.DuplicateSaleException;
import seedu.address.model.tag.Tag;

/**
 * Adds a sale associated with a contact to StonksBook.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "sale add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a sale of specified item name, unit price and "
        + "quantity, to the specified contact.\n"
        + "Parameters: "
        + PREFIX_SALE_CONTACT_INDEX + "CONTACT_INDEX (must be a positive integer) "
        + PREFIX_SALE_NAME + "ITEM_NAME "
        + PREFIX_SALE_UNIT_PRICE + "UNIT_PRICE "
        + PREFIX_SALE_QUANTITY + "QUANTITY\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_SALE_CONTACT_INDEX + "2 "
        + PREFIX_SALE_NAME + "Apple "
        + PREFIX_SALE_UNIT_PRICE + "2.50 "
        + PREFIX_SALE_QUANTITY + "50";

    public static final String MESSAGE_SUCCESS = "New sale added: %1$s";
    public static final String MESSAGE_DUPLICATE_SALE = "This sale already exists in StonksBook.";

    private final Index index; /** Index of the person to be associated with this sale. */
    private final Sale toAdd; /** Sale to be added. */

    /**
     * Creates an AddCommand that adds a {@code Sale}.
     *
     * @param index          The index of the Person to associate this sale to.
     * @param toAdd          The sale to be added to the specified person.
     */
    public AddCommand(Index index, Sale toAdd) {
        requireAllNonNull(index, toAdd);
        this.index = index;
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getSortedPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        try {
            personToEdit.getSalesList().add(toAdd);
        } catch (DuplicateSaleException e) {
            throw new CommandException(MESSAGE_DUPLICATE_SALE);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        // state check
        AddCommand otherAddCommand = (AddCommand) other;
        return index.equals(otherAddCommand.index)
            && toAdd.equals(otherAddCommand.toAdd);
    }
}
