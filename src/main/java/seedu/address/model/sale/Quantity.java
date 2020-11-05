package seedu.address.model.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Sale item's quantity in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should be a positive integer less than 10 million, and should not be blank.";

    /*
     * Quantity should be a non-blank, positive integer.
     */
    public final int quantity;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(Integer quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = quantity;
    }

    /**
     * Returns true if a given string is a valid quantity.
     */
    public static boolean isValidQuantity(Integer test) {
        requireNonNull(test);
        return test > 0 && test < 10000000;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Quantity // instanceof handles nulls
                && quantity == ((Quantity) other).quantity); // state check
    }

    @Override
    public int hashCode() {
        return ((Integer) quantity).hashCode();
    }
}
