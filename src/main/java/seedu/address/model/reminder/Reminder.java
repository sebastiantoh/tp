package seedu.address.model.reminder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.Message;
import seedu.address.model.person.Person;

/**
 * Represents a Reminder that is associated with a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reminder implements Comparable<Reminder> {
    // For formatting of the scheduled date that is to be printed to the UI.
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("E, dd MMM yyyy, HH:mm");

    private final Person person;
    private final Message message;
    private final LocalDateTime scheduledDate;
    private final Boolean completed;

    /**
     * Constructs a {@code Reminder}. Every field must be present and not null. By default, the reminder is set to
     * be incomplete.
     *
     * @param person        The person associated with this reminder.
     * @param message       The message associated with this reminder.
     * @param scheduledDate The date this reminder is scheduled for.
     */
    public Reminder(Person person, Message message, LocalDateTime scheduledDate) {
        requireAllNonNull(person, message, scheduledDate);
        this.person = person;
        this.message = message;
        this.scheduledDate = scheduledDate;
        this.completed = false;
    }

    /**
     * Constructs a {@code Reminder}. Every field must be present and not null. The completion status of this
     * reminder depends on the field.
     *
     * @param person        The person associated with this reminder.
     * @param message       The message associated with this reminder.
     * @param scheduledDate The date this reminder is scheduled for.
     * @param completed     The completion status of this reminder.
     */
    public Reminder(Person person, Message message, LocalDateTime scheduledDate, Boolean completed) {
        requireAllNonNull(person, message, scheduledDate);
        this.person = person;
        this.message = message;
        this.scheduledDate = scheduledDate;
        this.completed = completed;
    }

    public Person getPerson() {
        return this.person;
    }

    public int getPersonId() {
        return this.person.getId();
    }

    public Message getMessage() {
        return this.message;
    }

    public LocalDateTime getScheduledDate() {
        return this.scheduledDate;
    }

    /**
     * Returns a formatted string representation of the meeting's scheduled date.
     * Output format is "E, dd MMM yyyy, HH:mm".
     */
    public String getFormattedScheduledDate() {
        return getScheduledDate().format(DATE_TIME_FORMATTER);
    }

    /**
     * Returns true if the reminder is not yet complete and the scheduled date is past the current date.
     */
    public boolean isOverdue() {
        return !this.completed && this.getScheduledDate().isBefore(LocalDateTime.now());
    }

    /**
     * Returns true if the reminder has been marked as completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getMessage())
                .append(String.format(" - %s ", getPerson().getName()))
                .append(String.format("(Scheduled for: %s)", getScheduledDate().format(DATE_TIME_FORMATTER)));

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
                && this.message.equals(otherReminder.message)
                && this.scheduledDate.equals(otherReminder.scheduledDate)
                && this.completed == otherReminder.completed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.person, this.message, this.scheduledDate, this.completed);
    }

    /**
     * Compares this reminder to the specified Reminder. A Reminder is "less" than another Reminder if and only if it
     * is scheduled earlier than the other Reminder.
     *
     * @param otherReminder The other Reminder to compare to
     * @return The comparator value, negative if less, positive if greater.
     */
    @Override
    public int compareTo(Reminder otherReminder) {
        return this.getScheduledDate().compareTo(otherReminder.scheduledDate);
    }
}
