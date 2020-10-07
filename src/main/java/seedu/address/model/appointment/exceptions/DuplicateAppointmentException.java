package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation will result in duplicate appointments (Appointments are considered duplicates if they
 * have the same fields.
 */
public class DuplicateAppointmentException extends RuntimeException {
    public DuplicateAppointmentException() {
        super("Operation would result in duplicate appointments");
    }
}
