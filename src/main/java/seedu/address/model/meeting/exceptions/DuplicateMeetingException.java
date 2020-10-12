package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in duplicate meetings (Meetings are considered duplicates if they
 * have the same fields.
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super("Operation would result in duplicate meetings");
    }
}
