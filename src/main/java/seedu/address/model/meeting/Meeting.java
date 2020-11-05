package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateUtil.isSameDay;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.model.Message;
import seedu.address.model.person.Person;

/**
 * Represents a Meeting that is associated with a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting implements Comparable<Meeting> {
    // For formatting of the scheduled date that is to be printed to the UI.
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("E, dd MMM yyyy, HH:mm");
    // For formatting of dates without the time
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
    // For formatting of just the hours and minutes
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final Person person;
    private final Message message;
    private final LocalDateTime startDate;
    private final Duration duration;

    /**
     * Constructs an {@code Meeting}. Every field must be present and not null.
     *
     * @param person    The person associated with this meeting.
     * @param message   The message associated with this meeting.
     * @param startDate The date this meeting starts.
     * @param duration  The duration of the meeting.
     */
    public Meeting(Person person, Message message, LocalDateTime startDate, Duration duration) {
        requireAllNonNull(person, message, startDate, duration);
        this.person = person;
        this.message = message;
        this.startDate = startDate;
        this.duration = duration;
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

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public Duration getDuration() {
        return this.duration;
    }

    private LocalDateTime getEndDate() {
        return this.startDate.plus(getDuration());
    }

    /**
     * Returns a formatted string representation of the meeting's start and end date.
     * If the meeting starts and ends on the same date, then the format output would be "E, dd MMM yyyy, HH:mm - HH:mm".
     * Otherwise, the format output is "E, dd MMM yyyy, HH:mm - E, dd MMM yyyy, HH:mm".
     */
    public String getFormattedStartEndDate() {
        LocalDateTime start = getStartDate();
        LocalDateTime end = getStartDate().plus(getDuration());
        if (isSameDay(start, end)) {
            return String.format("%s, %s - %s", start.format(DATE_FORMATTER),
                    start.format(TIME_FORMATTER), end.format(TIME_FORMATTER));
        } else {
            return String.format("%s - %s",
                    getStartDate().format(DATE_TIME_FORMATTER),
                    getEndDate().format(DATE_TIME_FORMATTER)
            );
        }
    }

    /**
     * Returns true if and only if this meeting conflicts with the {@code otherMeeting}.
     * A meeting is considered conflicting with another if and only if the time interval for which the meeting is
     * active overlaps.
     */
    public boolean isConflicting(Meeting otherMeeting) {
        return this.startDate.isBefore(otherMeeting.getEndDate()) && this.getEndDate()
                .isAfter(otherMeeting.getStartDate());
    }

    /**
     * Returns true if and only if the current meeting is over.
     * A meeting is considered over if the meeting's end date is past the current time.
     */
    public boolean isOver() {
        return LocalDateTime.now().isAfter(this.getEndDate());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getMessage())
                .append(String.format(" - %s ", getPerson().getName()))
                .append(String.format("(%s)", getFormattedStartEndDate()));

        return builder.toString();
    }

    /**
     * Checks for equality against another object.
     *
     * @param other The other object to check for equality against.
     * @return Returns true if and only if two meetings are associated with the same Person, have the same message
     * (case-insensitive), have the same start date and duration.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;

        return this.person.equals(otherMeeting.person)
                && this.message.equals(otherMeeting.message)
                && this.startDate.equals(otherMeeting.startDate)
                && this.duration.equals(otherMeeting.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.person, this.message, this.startDate, this.duration);
    }

    /**
     * Compares this meeting to the specified Meeting. A Meeting is "less" than another Meeting if and only if it
     * starts earlier than the other Meeting. If both Meetings have the same start date, than the meeting with the
     * shorter duration will be considered "less".
     *
     * @param otherMeeting The other meeting to compare to.
     * @return The comparator value, negative if less, positive if greater.
     */
    @Override
    public int compareTo(Meeting otherMeeting) {
        int cmpStartDate = this.startDate.compareTo(otherMeeting.startDate);
        if (cmpStartDate != 0) {
            return cmpStartDate;
        }

        return this.duration.compareTo(otherMeeting.duration);
    }
}
