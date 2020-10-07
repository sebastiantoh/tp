package seedu.address.model.reminder.exceptions;

/**
 * Signals that the operation is unable to find the specified reminder.
 */
public class ReminderNotFoundException extends RuntimeException {
    public ReminderNotFoundException() {
        super("Specified reminder was not found.");
    }
}
