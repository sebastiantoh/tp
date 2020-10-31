package seedu.address.model.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Sale item's quantity in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class Quantity {

    public static final String MESSAGE_CONSTRAINTS =
            "Quantity should be a positive integer less than 10 million, and should not be blank";

    /*
     * Quantity should be a non-blank, positive integer.
     */
    public static final String VALIDATION_REGEX = "^[\\d]+$";

    public final int quantity;

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity.
     */
    public Quantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_CONSTRAINTS);
        this.quantity = Integer.parseInt(quantity);
    }

    /**
     * Returns true if a given string is a valid quantity.
     */
    public static boolean isValidQuantity(String test) {
        requireNonNull(test);
        try {
            int quantity = Integer.parseInt(test);
            boolean isWithinRange = quantity > 0 && quantity < 10000000;
            return test.matches(VALIDATION_REGEX) && isWithinRange;
        } catch (NumberFormatException e) {
            return false;
        }
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
