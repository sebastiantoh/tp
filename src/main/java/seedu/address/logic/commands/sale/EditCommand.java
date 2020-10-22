package seedu.address.logic.commands.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_UNIT_PRICE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SALES;

import java.time.LocalDateTime;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the sale identified "
            + "by the index number used in the displayed sale list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SALE_CONTACT_INDEX + "CONTACT_INDEX] "
            + "[" + PREFIX_SALE_NAME + "ITEM_NAME] "
            + "[" + PREFIX_SALE_DATE + "DATETIME_OF_PURCHASE] "
            + "[" + PREFIX_SALE_UNIT_PRICE + "UNIT_PRICE] "
            + "[" + PREFIX_SALE_QUANTITY + "QUANTITY]\n"
            + "Example: " + COMMAND_WORD + " 3 "
            + PREFIX_SALE_NAME + "File "
            + PREFIX_SALE_QUANTITY + "25 ";

    public static final String MESSAGE_EDIT_SALE_SUCCESS = "Edited Sale: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SALE = "This sale already exists in the address book.";

    private final Index saleIndex;
    private final Index personIndex;
    private final EditSaleDescriptor editSaleDescriptor;

    /**
     * Constructs a new EditCommand.
     * @param saleIndex of the sale in the filtered sale list to edit
     * @param editSaleDescriptor details to edit the sale with.
     * @param personIndex of the person in the contact to assign as buyer.
     */
    public EditCommand(Index saleIndex, EditSaleDescriptor editSaleDescriptor, Index personIndex) {
        requireAllNonNull(saleIndex, editSaleDescriptor);
        this.saleIndex = saleIndex;
        this.editSaleDescriptor = new EditSaleDescriptor(editSaleDescriptor);
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Sale> lastShownList = model.getFilteredSaleList();

        if (saleIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX);
        }

        Sale saleToEdit = lastShownList.get(saleIndex.getZeroBased());

        if (personIndex != null) {
            List<Person> lastShownPeople = model.getSortedPersonList();
            if (personIndex.getZeroBased() >= lastShownPeople.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person newBuyer = lastShownPeople.get(personIndex.getZeroBased());
            editSaleDescriptor.setBuyerId(newBuyer.getId());
        } else {
            editSaleDescriptor.setBuyerId(saleToEdit.getBuyerId());
        }

        Sale editedSale = createEditedSale(saleToEdit, editSaleDescriptor);

        if (!model.saleTagsExist(editedSale)) {
            throw new CommandException(Messages.MESSAGE_SALE_TAGS_NOT_FOUND);
        }

        if (!saleToEdit.isSameSale(editedSale) && model.hasSale(editedSale)) {
            throw new CommandException(MESSAGE_DUPLICATE_SALE);
        }

        model.setSale(saleToEdit, editedSale);
        model.updateFilteredSaleList(PREDICATE_SHOW_ALL_SALES);
        return new CommandResult(String.format(MESSAGE_EDIT_SALE_SUCCESS, editedSale));
    }

    /**
     * Creates and returns a {@code Sale} with the details of {@code saleToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Sale createEditedSale(Sale saleToEdit, EditSaleDescriptor editSaleDescriptor) {
        assert saleToEdit != null;

        ItemName updatedItemName = editSaleDescriptor.getItemName().orElse(saleToEdit.getItemName());
        int updatedBuyerId = editSaleDescriptor.getBuyerId().orElse(saleToEdit.getBuyerId());
        LocalDateTime updatedDatetimeOfPurchase = editSaleDescriptor.getDatetimeOfPurchase()
                .orElse(saleToEdit.getDatetimeOfPurchase());
        UnitPrice updatedUnitPrice = editSaleDescriptor.getUnitPrice().orElse(saleToEdit.getUnitPrice());
        Quantity updatedQuantity = editSaleDescriptor.getQuantity().orElse(saleToEdit.getQuantity());
        Set<Tag> updatedTags = editSaleDescriptor.getTags().orElse(saleToEdit.getTags());

        return new Sale(updatedItemName, updatedBuyerId, updatedDatetimeOfPurchase, updatedQuantity, updatedUnitPrice,
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
        return saleIndex.equals(e.saleIndex)
                && editSaleDescriptor.equals(e.editSaleDescriptor)
                && (Objects.equals(personIndex, e.personIndex));
    }

    /**
     * Stores the details to edit the sale with. Each non-empty field value will replace the
     * corresponding field value of the sale.
     */
    public static class EditSaleDescriptor {
        private int buyerId;
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
            setBuyerId(toCopy.buyerId);
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
            return CollectionUtil.isAnyNonNull(buyerId, itemName, datetimeOfPurchase, unitPrice, quantity, tagList);
        }

        public void setBuyerId(int id) {
            this.buyerId = id;
        }

        public Optional<Integer> getBuyerId() {
            return Optional.ofNullable(buyerId);
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

            return getBuyerId().equals(e.getBuyerId())
                    && getItemName().equals(e.getItemName())
                    && getDatetimeOfPurchase().equals(e.getDatetimeOfPurchase())
                    && getUnitPrice().equals(e.getUnitPrice())
                    && getQuantity().equals(e.getQuantity())
                    && getTags().equals(e.getTags());
        }
    }
}
