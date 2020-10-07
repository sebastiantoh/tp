package seedu.address.model.tag.exceptions;

/**
 * Signals that this operation is not provided with a list of contact tags.
 */
public class InvalidTagListTypeException extends RuntimeException {
    public InvalidTagListTypeException() {
        super("Operation require a list of tags for contact.");
    }
}
