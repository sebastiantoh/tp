package seedu.address.model.sale;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Sale in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Sale {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("E, dd MMM yyyy, HH:mm");

    /** Identity fields */
    private final ItemName itemName;

    /** Data fields */
    private final LocalDateTime datetimeOfPurchase;
    private final Quantity quantity;
    private final UnitPrice unitPrice;
    private final double totalCost;

    // Sale tags
    private final Set<Tag> tags;

    /**
     * Every field must be present and not null.
     */
    public Sale(ItemName itemName, LocalDateTime datetimeOfPurchase, Quantity quantity,
                UnitPrice unitPrice, Set<Tag> tags) {
        requireAllNonNull(itemName, datetimeOfPurchase, quantity, unitPrice);
        this.itemName = itemName;
        this.quantity = quantity;
        this.datetimeOfPurchase = datetimeOfPurchase;
        this.unitPrice = unitPrice;
        this.totalCost = this.quantity.quantity * this.unitPrice.getAmount();
        this.tags = tags;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public LocalDateTime getDatetimeOfPurchase() {
        return datetimeOfPurchase;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both sales have the identity and data fields.
     * This defines a weaker notion of equality between two sales.
     */
    public boolean isSameSale(Sale otherSale) {
        if (otherSale == this) {
            return true;
        }

        return otherSale != null
                && otherSale.getItemName().equals(getItemName())
                && otherSale.getDatetimeOfPurchase().equals(getDatetimeOfPurchase())
                && otherSale.getUnitPrice().equals(getUnitPrice())
                && otherSale.getQuantity().equals(getItemName());
    }

    /**
     * Returns true if both sale have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Sale)) {
            return false;
        }

        Sale otherSale = (Sale) other;
        return otherSale.getItemName().equals(getItemName())
                && otherSale.getDatetimeOfPurchase().equals(getDatetimeOfPurchase())
                && otherSale.getUnitPrice().equals(getUnitPrice())
                && otherSale.getQuantity().equals(getQuantity())
                && otherSale.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName, datetimeOfPurchase, quantity, unitPrice);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getItemName())
                .append(" (Date of Purchase: ")
                .append(getDatetimeOfPurchase().format(DATE_TIME_FORMATTER))
                .append(", Quantity: ")
                .append(getQuantity())
                .append(", Unit Price: ")
                .append(getUnitPrice())
                .append(", Tags: ")
                .append(getTags())
                .append(")");
        return builder.toString();
    }
}
