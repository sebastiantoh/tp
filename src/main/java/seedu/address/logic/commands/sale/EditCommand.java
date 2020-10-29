package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SALES;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
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
 * Edits the details of an existing sale in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "sale edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the sale(s) identified "
            + "by the index number used in the displayed sale list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + PREFIX_SALE_INDEX + "INDEX... "
            + "[" + PREFIX_SALE_CONTACT_INDEX + "CONTACT_INDEX] "
            + "[" + PREFIX_SALE_NAME + "ITEM_NAME] "
            + "[" + PREFIX_SALE_DATE + "DATETIME_OF_PURCHASE] "
            + "[" + PREFIX_SALE_UNIT_PRICE + "UNIT_PRICE] "
            + "[" + PREFIX_SALE_QUANTITY + "QUANTITY]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SALE_INDEX + "3 "
            + PREFIX_SALE_NAME + "File " + PREFIX_SALE_QUANTITY + "25 ";

    public static final String MESSAGE_EDIT_SALE_SUCCESS = "Edited Sale(s): ";
    public static final String MESSAGE_EDIT_SALE_FAILED = "No sales edited.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SALE = "This sale already exists in the address book.";
    public static final String MESSAGES_SALES_MISSING_TAGS = "All sales must contain at least one tag.";

    private final List<Index> saleIndexes;
    private final Index personIndex;
    private final EditSaleDescriptor editSaleDescriptor;

    /**
     * Constructs a new EditCommand.
     * @param saleIndexes of the sales in the filtered sale list to edit
     * @param editSaleDescriptor details to edit the sale with.
     * @param personIndex of the person in the contact to assign as buyer.
     */
    public EditCommand(List<Index> saleIndexes, EditSaleDescriptor editSaleDescriptor, Index personIndex) {
        requireAllNonNull(saleIndexes, editSaleDescriptor);
        this.saleIndexes = saleIndexes;
        this.editSaleDescriptor = new EditSaleDescriptor(editSaleDescriptor);
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Sale> lastShownList = model.getSortedSaleList();
        MassSaleCommandUtil.areSaleIndexesValid(lastShownList, saleIndexes);

        // Check if new tags are valid.
        if (editSaleDescriptor.getTags().isPresent()) {
            if (!model.saleTagsExist(editSaleDescriptor.getTags().get())) {
                throw new CommandException(Messages.MESSAGE_SALE_TAGS_NOT_FOUND);
            }
        }

        // Check if new buyer is valid, and add to editSaleDescriptor if it is.
        if (personIndex != null) {
            List<Person> lastShownPeople = model.getSortedPersonList();
            MassSaleCommandUtil.arePersonIndexesValid(lastShownPeople, new ArrayList<>(Arrays.asList(personIndex)));
            Person newBuyer = lastShownPeople.get(personIndex.getZeroBased());
            editSaleDescriptor.setBuyer(newBuyer);
        }

        List<Sale> editedSales = new ArrayList<>();
        List<Sale> invalidSales = new ArrayList<>();

        for (Index saleIndex : saleIndexes) {
            Sale saleToEdit = lastShownList.get(saleIndex.getZeroBased());
            Sale editedSale = createEditedSale(saleToEdit, editSaleDescriptor);

            if (!saleToEdit.isSameSale(editedSale) && model.hasSale(editedSale)) {
                invalidSales.add(editedSale);
            } else {
                model.setSale(saleToEdit, editedSale);
                editedSales.add(editedSale);
            }
        }

        model.updateFilteredSaleList(PREDICATE_SHOW_ALL_SALES);
        String result = generateResultString(editedSales, invalidSales);

        return new CommandResult(result, false, true);
    }

    private String generateResultString(List<Sale> editedSales, List<Sale> invalidSales) {
        String result;

        if (editedSales.size() > 0) {
            result = MESSAGE_EDIT_SALE_SUCCESS + MassSaleCommandUtil.listAllSales(editedSales);
        } else {
            result = MESSAGE_EDIT_SALE_FAILED;
        }

        if (invalidSales.size() > 0) {
            result += "\n" + MESSAGE_DUPLICATE_SALE + MassSaleCommandUtil.listAllSales(invalidSales);
        }
        return result;
    }

    /**
     * Creates and returns a {@code Sale} with the details of {@code saleToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Sale createEditedSale(Sale saleToEdit, EditSaleDescriptor editSaleDescriptor) {
        assert saleToEdit != null;

        ItemName updatedItemName = editSaleDescriptor.getItemName().orElse(saleToEdit.getItemName());
        Person updatedBuyer = editSaleDescriptor.getBuyer().orElse(saleToEdit.getBuyer());
        LocalDateTime updatedDatetimeOfPurchase = editSaleDescriptor.getDatetimeOfPurchase()
                .orElse(saleToEdit.getDatetimeOfPurchase());
        UnitPrice updatedUnitPrice = editSaleDescriptor.getUnitPrice().orElse(saleToEdit.getUnitPrice());
        Quantity updatedQuantity = editSaleDescriptor.getQuantity().orElse(saleToEdit.getQuantity());
        Set<Tag> updatedTags = editSaleDescriptor.getTags().orElse(saleToEdit.getTags());

        return new Sale(updatedItemName, updatedBuyer, updatedDatetimeOfPurchase, updatedQuantity, updatedUnitPrice,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return saleIndexes.equals(e.saleIndexes)
                && editSaleDescriptor.equals(e.editSaleDescriptor)
                && (Objects.equals(personIndex, e.personIndex));
    }

    /**
     * Stores the details to edit the sale with. Each non-empty field value will replace the
     * corresponding field value of the sale.
     */
    public static class EditSaleDescriptor {
        private Person buyer;
        private ItemName itemName;
        private LocalDateTime datetimeOfPurchase;
        private Quantity quantity;
        private UnitPrice unitPrice;
        private Set<Tag> tagList;


        public EditSaleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditSaleDescriptor(EditSaleDescriptor toCopy) {
            setBuyer(toCopy.buyer);
            setItemName(toCopy.itemName);
            setDatetimeOfPurchase(toCopy.datetimeOfPurchase);
            setUnitPrice(toCopy.unitPrice);
            setQuantity(toCopy.quantity);
            setTags(toCopy.tagList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(buyer, itemName, datetimeOfPurchase, unitPrice, quantity, tagList);
        }

        public void setBuyer(Person buyer) {
            this.buyer = buyer;
        }

        public Optional<Person> getBuyer() {
            return Optional.ofNullable(buyer);
        }

        public void setItemName(ItemName itemName) {
            this.itemName = itemName;
        }

        public Optional<ItemName> getItemName() {
            return Optional.ofNullable(itemName);
        }

        public void setDatetimeOfPurchase(LocalDateTime datetimeOfPurchase) {
            this.datetimeOfPurchase = datetimeOfPurchase;
        }

        public Optional<LocalDateTime> getDatetimeOfPurchase() {
            return Optional.ofNullable(datetimeOfPurchase);
        }

        public void setUnitPrice(UnitPrice unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Optional<UnitPrice> getUnitPrice() {
            return Optional.ofNullable(unitPrice);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Optional<Quantity> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tagList = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tagList != null) ? Optional.of(Collections.unmodifiableSet(tagList)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSaleDescriptor)) {
                return false;
            }

            // state check
            EditSaleDescriptor e = (EditSaleDescriptor) other;

            return getBuyer().equals(e.getBuyer())
                    && getItemName().equals(e.getItemName())
                    && getDatetimeOfPurchase().equals(e.getDatetimeOfPurchase())
                    && getUnitPrice().equals(e.getUnitPrice())
                    && getQuantity().equals(e.getQuantity())
                    && getTags().equals(e.getTags());
        }
    }
}
