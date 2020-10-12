package seedu.address.model.sale;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Sale item's unit price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnitPriceString(String)}
 */
public class UnitPrice {
    public static final String MESSAGE_CONSTRAINTS =
            "UnitPrice should be in the form \"DOLLARS.CENTS\", where DOLLARS represents a positive integer "
                    + "and CENTS represents a 2 digit positive integer. It should not be blank, "
                    + "and the total unit price should be greater than zero";

    /*
     * UnitPrice should be in the form "DOLLARS.CENTS", where DOLLARS represents a positive integer
     * and CENTS represents a 2 digit positive integer.
     * The total unit price should be greater than zero.
     * It should not be blank.
     */
    public static final String VALIDATION_REGEX = "^(([\\d]*)\\.(\\d{2}))$";

    public final int dollars;
    public final int cents;

    /**
     * Constructs a {@code UnitPrice}.
     *
     * @param dollars A valid dollar value for unit price.
     * @param cents A valid cent value for unit price.
     */
    public UnitPrice(Integer dollars, Integer cents) {
        requireAllNonNull(dollars, cents);
        checkArgument(isValidUnitPrice(dollars, cents), MESSAGE_CONSTRAINTS);
        this.dollars = dollars;
        this.cents = cents;
    }

    /**
     * Returns true if a given params is a valid unit price.
     */
    public static boolean isValidUnitPrice(Integer dollars, Integer cents) {
        boolean isCentsValid = cents >= 0 && cents < 100;
        boolean isDollarsValid = dollars >= 0;
        boolean isPriceGreaterThanZero = (cents + dollars) > 0;
        return isCentsValid && isDollarsValid && isPriceGreaterThanZero;
    }

    /**
     * Returns true if a given string can be parsed into a valid unit price.
     */
    public static boolean isValidUnitPriceString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "$" + dollars + "." + String.format("%02d", cents);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnitPrice // instanceof handles nulls
                && dollars == ((UnitPrice) other).dollars // state check
                && cents == ((UnitPrice) other).cents); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
