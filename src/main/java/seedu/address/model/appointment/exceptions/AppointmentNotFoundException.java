package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation is unable to find the specified reminder.
 */
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super("Specified appointment was not found.");
    }
}
