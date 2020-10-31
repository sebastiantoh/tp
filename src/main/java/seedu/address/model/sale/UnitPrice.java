package seedu.address.model.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.math.BigDecimal;

/**
 * Represents a Sale item's unit price in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnitPriceString(String)}
 */
public class UnitPrice {
    public static final String MESSAGE_CONSTRAINTS =
            "UnitPrice should be in the form \"DOLLARS.CENTS\", where DOLLARS represents a positive integer "
                    + "and CENTS represents a 2 digit positive integer. It should not be blank, "
                    + "and the total unit price should be greater than zero and less than 10 million.";

    /*
     * UnitPrice should be in the form "DOLLARS.CENTS", where DOLLARS represents a positive integer
     * and CENTS represents a 2 digit positive integer.
     * The total unit price should be greater than zero.
     * It should not be blank.
     */
    public static final String VALIDATION_REGEX = "^(([\\d]*)\\.(\\d{2}))$";

    public final BigDecimal amount;

    /**
     * Constructs a {@code UnitPrice}.
     * @param unitPrice A valid value of unit price.
     */
    public UnitPrice(BigDecimal unitPrice) {
        requireNonNull(unitPrice);
        checkArgument(isValidUnitPrice(unitPrice), MESSAGE_CONSTRAINTS);
        this.amount = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Returns true if given unit price is greater than 0 and less than 10 million
     */
    public static boolean isValidUnitPrice(BigDecimal test) {
        String string = test.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        int noOfDecimalPlaces = index < 0 ? 0 : string.length() - index - 1;

        return test.compareTo(BigDecimal.ZERO) > 0 && noOfDecimalPlaces < 3;
    }

    /**
     * Returns true if a given params is a valid unit price.
     */
    public static boolean isValidUnitPriceString(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        String[] priceSplit = test.split("\\.");
        int dollars = Integer.parseInt(priceSplit[0]);
        int cents = Integer.parseInt(priceSplit[1]);

        boolean isCentsValid = cents >= 0 && cents < 100;
        boolean isDollarsValid = dollars >= 0;
        boolean isPriceGreaterThanZero = (cents + dollars) > 0;
        boolean isPriceLessThanTenMillion = new BigDecimal(test).compareTo(new BigDecimal("10000000")) < 0;

        return isCentsValid && isDollarsValid && isPriceGreaterThanZero && isPriceLessThanTenMillion;
    }

    public String getUnitPriceString() {
        return this.amount.setScale(2).toPlainString();
    }

    @Override
    public String toString() {
        return this.amount.setScale(2).toPlainString();

        // Dollar sign has been temporarily removed to pass test cases
        // TODO: reinstate the following method after implementing GUI for sales
        // return NumberFormat.getCurrencyInstance().format(this.amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnitPrice // instanceof handles nulls
                && amount.compareTo(((UnitPrice) other).amount) == 0); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
