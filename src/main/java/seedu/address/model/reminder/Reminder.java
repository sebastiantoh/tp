package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Date;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Reminder associated with a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder {

    private final Person person;
    private final String message;
    private final Date scheduledDate;

    /**
     * Constructs a {@code Reminder}. Every field must be present and not null.
     *
     * @param person        The person associated with this reminder.
     * @param message       The message associated with this reminder.
     * @param scheduledDate The date this reminder is scheduled for.
     */
    public Reminder(Person person, String message, Date scheduledDate) {
        requireAllNonNull(person, message, scheduledDate);
        this.person = person;
        this.message = message.strip();
        this.scheduledDate = scheduledDate;
    }

    public Person getPerson() {
        return this.person;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getScheduledDate() {
        return this.scheduledDate;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append("Reminder for: ")
            .append(getPerson().getName())
            .append(" Message: ")
            .append(getMessage())
            .append(" Scheduled for: ")
            .append(getScheduledDate());

        return builder.toString();
    }

    /**
     * Checks for equality against another object.
     *
     * @param other The other object to check for equality against.
     * @return Returns true if and only if two reminders are associated with the same Person, have the same message
     * (case-insensitive), and have the same scheduled date.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Reminder)) {
            return false;
        }

        Reminder otherReminder = (Reminder) other;

        return this.person.equals(otherReminder.person)
            // Case-insensitive equality checking
            && this.message.toLowerCase().equals(otherReminder.message.toLowerCase())
            && this.scheduledDate.equals(otherReminder.scheduledDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.person, this.message, this.scheduledDate);
    }

}
