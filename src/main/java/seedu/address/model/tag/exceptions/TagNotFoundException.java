package seedu.address.model.tag.exceptions;

/**
 * Signals that the operation is unable to find the specified tag.
 */
public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super("The specified tag does not exist in the StonksBook");
    }
}
