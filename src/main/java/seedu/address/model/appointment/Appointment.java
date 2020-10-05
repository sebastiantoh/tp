package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Appointment that is associated with a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    private final Person person;
    private final String message;
    private final LocalDateTime startDate;
    private final Duration duration;
    
    /**
     * Constructs an {@code Appointment}. Every field must be present and not null.
     *
     * @param person    The person associated with this appointment.
     * @param message   The message associated with this appointment.
     * @param startDate The date this appointment starts.
     * @param duration  The duration of the appointment.
     */
    public Appointment(Person person, String message, LocalDateTime startDate, Duration duration) {
        requireAllNonNull(person, message, startDate, duration);
        this.person = person;
        this.message = message.strip();
        this.startDate = startDate;
        this.duration = duration;
    }

    public Person getPerson() {
        return this.person;
    }

    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public Duration getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Appointment with: ")
            .append(getPerson().getName())
            .append(" Message: ")
            .append(getMessage())
            .append(" From ")
            .append(getStartDate())
            .append(" to ")
            .append(getStartDate().plus(getDuration()));

        return builder.toString();
    }

    /**
     * Checks for equality against another object.
     *
     * @param other The other object to check for equality against.
     * @return Returns true if and only if two appointments are associated with the same Person, have the same message
     * (case-insensitive), have the same start date and duration.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;

        return this.person.equals(otherAppointment.person)
            // Case-insensitive equality checking
            && this.message.toLowerCase().equals(otherAppointment.message.toLowerCase())
            && this.startDate.equals(otherAppointment.startDate)
            && this.duration.equals(otherAppointment.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.person, this.message, this.startDate, this.duration);
    }

}
