package seedu.address.model.sale;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Sale in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Sale implements Comparable<Sale> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("E, dd MMM yyyy, HH:mm");

    /** Identity fields */
    private final ItemName itemName;
    private final Person buyer;

    /** Data fields */
    private final LocalDateTime datetimeOfPurchase;
    private final Quantity quantity;
    private final UnitPrice unitPrice;
    private final BigDecimal totalCost;

    // Sale tags
    private final Set<Tag> tags;

    /**
     * Every field must be present and not null.
     */
    public Sale(ItemName itemName, Person buyer, LocalDateTime datetimeOfPurchase, Quantity quantity,
                UnitPrice unitPrice, Set<Tag> tags) {
        requireAllNonNull(itemName, datetimeOfPurchase, quantity, unitPrice);
        this.itemName = itemName;
        this.buyer = buyer;
        this.quantity = quantity;
        this.datetimeOfPurchase = datetimeOfPurchase;
        this.unitPrice = unitPrice;
        this.totalCost = this.unitPrice.getAmount().multiply(new BigDecimal(this.quantity.quantity));
        this.tags = tags;
    }

    public ItemName getItemName() {
        return itemName;
    }

    public Person getBuyer() {
        return buyer;
    }

    public LocalDateTime getDatetimeOfPurchase() {
        return datetimeOfPurchase;
    }

    public String getFormattedDatetimeOfPurchase() {
        return getDatetimeOfPurchase().format(DATE_TIME_FORMATTER);
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    public String getUnitPriceString() {
        return unitPrice.toString();
    }

    public BigDecimal getTotalCost() {
        return this.totalCost;
    }

    public String getTotalCostString() {
        return NumberFormat.getCurrencyInstance().format(totalCost);
    }

    public Month getMonth() {
        return datetimeOfPurchase.getMonth();
    }

    public Year getYear() {
        return Year.of(datetimeOfPurchase.getYear());
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
                && otherSale.getBuyer().equals(getBuyer())
                && otherSale.getDatetimeOfPurchase().equals(getDatetimeOfPurchase())
                && otherSale.getUnitPrice().equals(getUnitPrice())
                && otherSale.getQuantity().equals(getQuantity());
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
                && otherSale.getBuyer().equals(getBuyer())
                && otherSale.getDatetimeOfPurchase().equals(getDatetimeOfPurchase())
                && otherSale.getUnitPrice().equals(getUnitPrice())
                && otherSale.getQuantity().equals(getQuantity())
                && otherSale.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName, buyer, datetimeOfPurchase, quantity, unitPrice);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getItemName())
                .append(" (Date of Purchase: ")
                .append(getFormattedDatetimeOfPurchase())
                .append(", Quantity: ")
                .append(getQuantity())
                .append(", Unit Price: ")
                .append(getUnitPrice())
                .append(", Tags: ")
                .append(getTags())
                .append(") (Client: ")
                .append(getBuyer().getName().fullName)
                .append(")");
        return builder.toString();
    }

    /**
     * Compares this sale to the specified Sale. A Sale is "less" than another Sale if and only if, from highest to
     * lowest priority: has an earlier datetime of purchase, has a lower lexicographical order of buyer name,
     * has a lower lexicographical order of item name.
     *
     * @param otherSale The other Sale to compare to
     * @return The comparator value, negative if less, positive if greater.
     */
    @Override
    public int compareTo(Sale otherSale) {
        return Comparator.comparing(Sale::getDatetimeOfPurchase)
                .thenComparing(s -> s.getBuyer().getName().fullName)
                .thenComparing(s -> s.getItemName().name)
                .compare(this, otherSale);
    }
}
