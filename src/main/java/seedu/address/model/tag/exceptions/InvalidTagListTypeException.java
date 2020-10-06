package seedu.address.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate Tags (Tags are considered duplicates if they have the same
 * name).
 */
public class InvalidTagListTypeException extends RuntimeException {
    public InvalidTagListTypeException() {
        super("Operation require a list of tags for contact.");
    }
}
