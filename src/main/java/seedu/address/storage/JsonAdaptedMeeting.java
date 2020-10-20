package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Message;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";
    public static final String DESERIALIZING_DURATION_ERROR_MESSAGE = "An error occurred while deserializing the "
        + "duration of a meeting!";

    private final JsonAdaptedPerson person;
    private final String message;
    // Serialised and stored in ISO-8601 format
    private final String startDate;
    // Serialised using ISO-8601 seconds based representation
    private final String duration;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("person") JsonAdaptedPerson person,
                              @JsonProperty("message") String message,
                              @JsonProperty("startDate") String startDate,
                              @JsonProperty("duration") String duration) {
        this.person = person;
        this.message = message;
        this.startDate = startDate;
        this.duration = duration;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        this.person = new JsonAdaptedPerson(source.getPerson());
        this.message = source.getMessage().message;
        this.startDate = source.getStartDate().toString();
        this.duration = source.getDuration().toString();
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {

        if (this.person == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        final Person person = this.person.toModelType();

        if (this.message == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Message"));
        }
        final Message message = new Message(this.message);

        if (this.startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Start Date"));
        }
        final LocalDateTime scheduledDate;
        try {
            scheduledDate = LocalDateTime.parse(this.startDate);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(MESSAGE_INVALID_DATETIME);
        }

        if (this.duration == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Duration"));
        }
        final Duration duration;
        try {
            duration = Duration.parse(this.duration);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(DESERIALIZING_DURATION_ERROR_MESSAGE);
        }
        return new Meeting(person, message, scheduledDate, duration);
    }
}
