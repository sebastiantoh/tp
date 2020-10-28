package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * Adds a sale associated with a contact to StonksBook.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "sale add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a sale of specified item name, unit price, "
        + "quantity and tags (optional), to the specified contact(s).\n"
        + "Parameters: "
        + PREFIX_SALE_CONTACT_INDEX + "CONTACT_INDEX... (must be a positive integer) "
        + PREFIX_SALE_NAME + "ITEM_NAME "
        + PREFIX_SALE_DATE + "DATETIME_OF_PURCHASE "
        + PREFIX_SALE_UNIT_PRICE + "UNIT_PRICE "
        + PREFIX_SALE_QUANTITY + "QUANTITY "
        + PREFIX_TAG + "TAG...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_SALE_CONTACT_INDEX + "2 "
        + PREFIX_SALE_NAME + "Apple "
        + PREFIX_SALE_DATE + "2020-10-30 15:00 "
        + PREFIX_SALE_UNIT_PRICE + "2.50 "
        + PREFIX_SALE_QUANTITY + "50 "
        + PREFIX_TAG + "fruits";

    public static final String MESSAGE_SUCCESS = "New sale(s) added: ";
    public static final String MESSAGE_FAILED = "No sales added.";
    public static final String MESSAGE_DUPLICATE_SALE = "The following sale(s) already exists in StonksBook.";

    private final List<Index> indexList;
    private final ItemName itemName;
    private final LocalDateTime dateOfPurchase;
    private final Quantity quantity;
    private final UnitPrice unitPrice;
    private final Set<Tag> tagList;

    /**
     * Creates an AddCommand that adds a Sale of specified parameters.
     * @param indexList        The indexes of the Persons to associate this sale to.
     * @param itemName         The item name of the Sale.
     * @param dateOfPurchase   The date of purchase of the Sale.
     * @param quantity         The quantity of the Sale.
     * @param unitPrice        The unit price of the Sale.
     * @param tagList          The tagList belonging to the Sale.
     */
    public AddCommand(List<Index> indexList, ItemName itemName, LocalDateTime dateOfPurchase,
                      Quantity quantity, UnitPrice unitPrice, Set<Tag> tagList) {
        requireAllNonNull(indexList, itemName, dateOfPurchase, quantity, unitPrice, tagList);
        this.indexList = indexList;
        this.itemName = itemName;
        this.dateOfPurchase = dateOfPurchase;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getSortedPersonList();
        MassSaleCommandUtil.arePersonIndexesValid(lastShownList, indexList);

        if (!model.saleTagsExist(tagList)) {
            throw new CommandException(Messages.MESSAGE_SALE_TAGS_NOT_FOUND);
        }

        List<Sale> duplicatedSales = new ArrayList<>();
        List<Sale> salesAdded = new ArrayList<>();

        for (Index index : indexList) {
            Person buyer = lastShownList.get(index.getZeroBased());
            Sale toAdd = new Sale(itemName, buyer, dateOfPurchase, quantity, unitPrice, tagList);

            if (model.hasSale(toAdd)) {
                duplicatedSales.add(toAdd);
            } else {
                model.addSale(toAdd);
                salesAdded.add(toAdd);
            }
        }

        if (duplicatedSales.isEmpty()) {
            return new CommandResult(generateSuccessMessage(salesAdded), false, true);
        }

        return new CommandResult(generateSuccessMessage(salesAdded)
                + "\n" + generateDuplicateSaleMessage(duplicatedSales), false, true);
    }

    private String generateSuccessMessage(List<Sale> sales) {
        if (sales.isEmpty()) {
            return MESSAGE_FAILED;
        }
        return MESSAGE_SUCCESS + MassSaleCommandUtil.listAllSales(sales);
    }

    private String generateDuplicateSaleMessage(List<Sale> sales) {
        assert !sales.isEmpty();
        return MESSAGE_DUPLICATE_SALE + MassSaleCommandUtil.listAllSales(sales);
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
        return indexList.equals(otherAddCommand.indexList)
            && itemName.equals(otherAddCommand.itemName)
            && dateOfPurchase.equals(otherAddCommand.dateOfPurchase)
            && quantity.equals(otherAddCommand.quantity)
            && unitPrice.equals(otherAddCommand.unitPrice)
            && tagList.equals(otherAddCommand.tagList);
    }
}
