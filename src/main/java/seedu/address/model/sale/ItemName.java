package seedu.address.model.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Sale item's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidItemName(String)}
 */
public class ItemName {

    public static final String MESSAGE_CONSTRAINTS =
            "Item names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the item name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code ItemName}.
     *
     * @param name A valid name.
     */
    public ItemName(String name) {
        requireNonNull(name);
        checkArgument(isValidItemName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid itemName.
     */
    public static boolean isValidItemName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemName // instanceof handles nulls
                && name.toLowerCase().equals(((ItemName) other).name.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
