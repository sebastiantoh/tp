package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Message;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

/**
 * Jackson-friendly version of {@link Reminder}.
 */
class JsonAdaptedReminder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reminder's %s field is missing!";
    public static final String INVALID_CONTACT_ID = "Invalid contact specified";

    private final Integer personId;
    private final String message;
    // Serialised and stored in ISO-8601 format
    private final String scheduledDate;
    private final Boolean completed;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given reminder details.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("personId") Integer personId,
                               @JsonProperty("message") String message,
                               @JsonProperty("scheduledDate") String scheduledDate,
                               @JsonProperty("completed") Boolean completed) {
        this.personId = personId;
        this.message = message;
        this.scheduledDate = scheduledDate;
        this.completed = completed;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        this.personId = source.getPersonId();
        this.message = source.getMessage().message;
        this.scheduledDate = source.getScheduledDate().toString();
        this.completed = source.isCompleted();
    }


    /**
     * Converts this Jackson-friendly adapted reminder object into the model's {@code Reminder} object.
     *
     * @param personList The list of all persons currently in StonksBook
     * @return The @{Reminder} object converted from the Jackson-friendly adapted reminder object.
     * @throws IllegalValueException IllegalValueException if there were any data constraints violated in the adapted
     *                               reminder.
     */
    public Reminder toModelType(List<Person> personList) throws IllegalValueException {
        if (this.personId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        final Person associatedPerson = personList
                .stream()
                .filter(person -> person.getId().equals(this.personId))
                .findFirst()
                .orElseThrow(() -> new IllegalValueException(INVALID_CONTACT_ID));

        if (this.message == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Message"));
        }
        final Message message = new Message(this.message);

        if (this.scheduledDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "DateTime"));
        }

        final LocalDateTime scheduledDate;
        try {
            scheduledDate = LocalDateTime.parse(this.scheduledDate);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(MESSAGE_INVALID_DATETIME);
        }

        return new Reminder(associatedPerson, message, scheduledDate, this.completed == null ? false : completed);
    }
}
