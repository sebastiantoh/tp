package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";
    public static final String MESSAGE_DUPLICATE_MEETING = "Meetings list contains duplicate meeting(s).";
    public static final String MESSAGE_DUPLICATE_REMINDER = "Reminders list contains duplicate reminder(s).";
    public static final String MESSAGE_DUPLICATE_SALE = "Sales list contains duplicate sale(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTag> contactTags = new ArrayList<>();
    private final List<JsonAdaptedTag> saleTags = new ArrayList<>();
    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();
    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();
    private final List<JsonAdaptedSale> sales = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, meetings and reminders
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("contactTags") List<JsonAdaptedTag> contactTags,
                                       @JsonProperty("saleTags") List<JsonAdaptedTag> saleTags,
                                       @JsonProperty("meetings") List<JsonAdaptedMeeting> meetings,
                                       @JsonProperty("reminders") List<JsonAdaptedReminder> reminders,
                                       @JsonProperty("sales") List<JsonAdaptedSale> sales) {
        this.persons.addAll(persons);
        this.contactTags.addAll(contactTags);
        this.saleTags.addAll(saleTags);
        this.meetings.addAll(meetings);
        this.reminders.addAll(reminders);
        this.sales.addAll(sales);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        contactTags.addAll(source.getContactTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        saleTags.addAll(source.getSaleTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        meetings
            .addAll(source.getMeetingList().stream().map(JsonAdaptedMeeting::new).collect(Collectors.toList()));
        reminders.addAll(source.getReminderList().stream().map(JsonAdaptedReminder::new).collect(Collectors.toList()));
        sales.addAll(source.getSaleList().stream().map(JsonAdaptedSale::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedTag jsonAdaptedTag : saleTags) {
            Tag tag = jsonAdaptedTag.toModelType();
            addressBook.addSaleTag(tag);
        }

        for (JsonAdaptedTag jsonAdaptedTag : contactTags) {
            Tag tag = jsonAdaptedTag.toModelType();
            addressBook.addContactTag(tag);
        }

        addressBook.sortTags();

        for (JsonAdaptedMeeting jsonAdaptedMeeting : meetings) {
            Meeting meeting = jsonAdaptedMeeting.toModelType(addressBook.getPersonList());
            if (addressBook.hasMeeting(meeting)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING);
            }
            addressBook.addMeeting(meeting);
        }

        for (JsonAdaptedReminder jsonAdaptedReminder : reminders) {
            Reminder reminder = jsonAdaptedReminder.toModelType(addressBook.getPersonList());
            if (addressBook.hasReminder(reminder)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_REMINDER);
            }
            addressBook.addReminder(reminder);
        }

        for (JsonAdaptedSale jsonAdaptedSale : sales) {
            Sale sale = jsonAdaptedSale.toModelType(addressBook.getPersonList());
            if (addressBook.hasSale(sale)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SALE);
            }
            addressBook.addSale(sale);
        }
        return addressBook;
    }
}
