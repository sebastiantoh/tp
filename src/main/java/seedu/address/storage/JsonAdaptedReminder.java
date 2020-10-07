package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Jackson-friendly version of {@link Reminder}.
 */
class JsonAdaptedReminder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder's %s field is missing!";

    private final JsonAdaptedPerson person;
    private final String message;
    // Serialised and stored in ISO-8601 format
    private final String scheduledDate;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given reminder details.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("person") JsonAdaptedPerson person,
                               @JsonProperty("message") String message,
                               @JsonProperty("scheduledDate") String scheduledDate) {
        this.person = person;
        this.message = message;
        this.scheduledDate = scheduledDate;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        this.person = new JsonAdaptedPerson(source.getPerson());
        this.message = source.getMessage();
        this.scheduledDate = source.getScheduledDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted reminder object into the model's {@code Reminder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reminder.
     */
    public Reminder toModelType() throws IllegalValueException {
        if (this.person == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        final Person person = this.person.toModelType();

        if (this.message == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Message"));
        }
        final String message = this.message;

        if (this.scheduledDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "DateTime"));
        }


        final LocalDateTime scheduledDate;
        try {
            scheduledDate = LocalDateTime.parse(this.scheduledDate);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(MESSAGE_INVALID_DATETIME);
        }

        return new Reminder(person, message, scheduledDate);
    }
}
