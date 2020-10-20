package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an attribute that stores text cannot be blank, and should only contain alphanumeric characters
 * and spaces.
 * Guarantees: immutable; is valid as declared in {@link #isValidMessage(String)}
 */
public class Message {

    public static final String MESSAGE_CONSTRAINTS =
            "Messages should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String message;

    /**
     * Constructs a {@code Message}.
     *
     * @param message A valid message.
     */
    public Message(String message) {
        requireNonNull(message);
        checkArgument(isValidMessage(message), MESSAGE_CONSTRAINTS);
        this.message = message;
    }

    /**
     * Returns true if a given string is a valid message.
     */
    public static boolean isValidMessage(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return message;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Message // instanceof handles nulls
                // Case-insensitive equality checking
                && message.strip().toLowerCase().equals(((Message) other).message.strip().toLowerCase()));
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

}
