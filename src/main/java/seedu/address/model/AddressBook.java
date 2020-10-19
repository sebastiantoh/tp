package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.UniqueReminderList;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueContactTagList;
import seedu.address.model.tag.UniqueSaleTagList;

/**
 * Wraps all data at the address-book level.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueContactTagList contactTags;
    private final UniqueSaleTagList saleTags;
    private final UniqueMeetingList meetings;
    private final UniqueReminderList reminders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     *
     */
    {
        persons = new UniquePersonList();
        contactTags = new UniqueContactTagList();
        saleTags = new UniqueSaleTagList();
        meetings = new UniqueMeetingList();
        reminders = new UniqueReminderList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the data in the {@code toBeCopied}.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the  contents of the tag list with {@code contactTags}.
     * {@code contactTags} must not contain duplicate contactTags.
     */
    public void setContactTags(List<Tag> contactTags) {
        this.contactTags.setTags(contactTags);
    }

    public void setSaleTags(List<Tag> saleTags) {
        this.saleTags.setTags(saleTags);
    }

    /**
     * Replaces the contents of the meetings list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Replaces the contents of the reminders list with {@code reminders}.
     * {@code reminders} must not contain duplicate reminders.
     */
    public void setReminders(List<Reminder> reminders) {
        this.reminders.setReminders(reminders);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setContactTags(newData.getContactTagList());
        setSaleTags(newData.getSaleTagList());
        setMeetings(newData.getMeetingList());
        setReminders(newData.getReminderList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        for (Tag t : p.getTags()) {
            contactTags.add(t);
        }
        for (Sale s : p.getSalesList()) {
            for (Tag t : s.getTags()) {
                saleTags.add(t);
            }
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        for (Tag t : editedPerson.getTags()) {
            contactTags.add(t);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. All associated meetings and reminders will be removed as well.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        meetings.removeMeetingsWithContact(key);
        reminders.removeRemindersWithContact(key);
    }

    //// tag-level operations

    /**
     * Returns true if a contact tag with the same identity as {@code tag} exists in StonksBook.
     */
    public boolean hasContactTag(Tag tag) {
        requireNonNull(tag);
        return contactTags.contains(tag);
    }

    /**
     * Returns true if a sale tag with the same identity as {@code tag} exists in StonksBOok.
     */
    public boolean hasSaleTag(Tag tag) {
        requireNonNull(tag);
        return saleTags.contains(tag);
    }

    /**
     * Adds the specified contact tag to StonksBook.
     * If the tag already exists in the contact tag list, no action will be performed.
     */
    public void addContactTag(Tag tag) {
        contactTags.add(tag);
    }

    /**
     * Adds the specified sale tag to StonksBook.
     * If the tag already exists in the sale tag list, no action will be performed.
     */
    public void addSaleTag(Tag tag) {
        saleTags.add(tag);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     */
    public void removeContactTag(Tag key) {
        contactTags.remove(key);
        persons.removeContactTag(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     */
    public void removeSaleTag(Tag key) {
        saleTags.remove(key);
        persons.removeSaleTag(key);
    }

    /**
     * Replaces the given {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the contact tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in StonksBook.
     */
    public void editContactTag(Tag target, Tag editedTag) {
        contactTags.setTag(target, editedTag);
        persons.setContactTag(target, editedTag);
    }

    /**
     * Replaces the given {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the sale tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in StonksBook.
     */
    public void editSaleTag(Tag target, Tag editedTag) {
        saleTags.setTag(target, editedTag);
        persons.setSaleTag(target, editedTag);
    }

    /**
     * Returns the number of contacts who are associated with the {@code target} tag.
     */
    public String findByContactTag(Tag target) {
        StringBuilder output = new StringBuilder();
        int count = 0;
        for (Person p : persons) {
            if (p.getTags().contains(target)) {
                output.append(String.format("%d. %s\n", ++count, p.toString()));
            }
        }
        if (count == 0) {
            return String.format("No matching contact found for tag: %s\n", target.toString());
        }
        return String.format("Listing %d contacts associated with: %s\n", count, target.toString()) + output.toString();
    }

    /**
     * Lists all sale items associated with {@code target} tag.
     */
    public String findSalesBySaleTag(Tag target) {
        StringBuilder output = new StringBuilder();
        int count = 0;
        for (Person p : persons) {
            int len = p.getSalesList().asUnmodifiableObservableList().size();
            for (int i = 0; i < len; i++) {
                Sale s = p.getSalesList().asUnmodifiableObservableList().get(i);
                if (s.getTags().contains(target)) {
                    output.append(String.format("%d. %s (Client: %s)\n", ++count, s, p.getName()));
                }
            }
        }
        if (count == 0) {
            return String.format("No matching sales found for tag: %s\n", target.toString());
        }
        return String.format("Listing %d sales items associated with: %s\n",
                count,
                target.toString()) + output.toString();
    }

    /**
     * Lists all contacts who have purchased items associated with {@code target} tag.
     */
    public String findContactsBySaleTag(Tag target) {
        StringBuilder output = new StringBuilder();
        int count = 0;
        for (Person p : persons) {
            for (Sale s : p.getSalesList()) {
                if (s.getTags().contains(target)) {
                    output.append(String.format("%d. %s\n", count++ + 1, p.toString()));
                    break;
                }
            }
        }
        return String.format("The following %d contact(s) have purchased items in this category: %s\n",
                count,
                target.toString()) + output.toString();
    }

    /**
     * List all the existing tags in StonksBook.
     */
    public String listTags() {
        StringBuilder output = new StringBuilder();
        ObservableList<Tag> contactTagList = contactTags.asUnmodifiableObservableList();
        ObservableList<Tag> saleTagList = saleTags.asUnmodifiableObservableList();
        if (contactTagList.size() == 0 && saleTagList.size() == 0) {
            output.append("No tags found!");
        } else if (contactTagList.size() == 0) {
            output.append("No contact tags found! ").append("Listing sale tags:\n");
            for (int i = 0; i < saleTagList.size(); i++) {
                output.append(String.format("%d. %s\n", i + 1, saleTagList.get(i)));
            }
        } else if (saleTagList.size() == 0) {
            output.append("No sale tags found! ").append("Listing contact tags:\n");
            for (int i = 0; i < contactTagList.size(); i++) {
                output.append(String.format("%d. %s\n", i + 1, contactTagList.get(i)));
            }
        } else {
            output.append("Listing contact tags:\n");
            for (int i = 0; i < contactTagList.size(); i++) {
                output.append(String.format("%d. %s\n", i + 1, contactTagList.get(i)));
            }
            output.append("\nListing sale tags:\n");
            for (int i = 0; i < saleTagList.size(); i++) {
                output.append(String.format("%d. %s\n", i + 1 + contactTagList.size(), saleTagList.get(i)));
            }
        }
        return output.toString();
    }

    /**
     * Returns true if all the tags of the provided {@code sale} item exist in StonksBook.
     */
    public boolean saleTagsExist(Sale sale) {
        for (Tag t : sale.getTags()) {
            if (!saleTags.contains(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if all the tags of the provided {@code sale} item exist in StonksBook.
     */
    public boolean contactTagsExist(Person person) {
        for (Tag t : person.getTags()) {
            if (!contactTags.contains(t)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Re-order all the existing tags in StonksBook.
     */
    public void sortTags() {
        contactTags.sort();
        saleTags.sort();
    }

    //// meeting-level operations

    /**
     * Returns true if an equivalent meeting exists in the address book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds an meeting to the address book.
     * The meeting must not already exist in the address book.
     */
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    //// reminder-level operations

    /**
     * Returns true if an equivalent reminder exists in StonksBook.
     */
    public boolean hasReminder(Reminder reminder) {
        requireNonNull(reminder);
        return reminders.contains(reminder);
    }

    /**
     * Adds a reminder to StonksBook.
     * The reminder must not already exist in StonksBook.
     */
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeReminder(Reminder key) {
        reminders.remove(key);
    }

    /**
     * Replaces the given reminder {@code target} in the list with {@code editedReminder}.
     * {@code target} must exist in the address book.
     * The reminder {@code editedReminder} must not be the same as another existing reminder in the address book.
     */
    public void setReminder(Reminder target, Reminder editedReminder) {
        requireAllNonNull(target, editedReminder);

        reminders.setReminder(target, editedReminder);
    }

    /**
     * Add a sale item to a contact.
     */
    public void addSaleToPerson(Person person, Sale sale) {
        Person editedPerson = Person.copyOf(person);
        editedPerson.getSalesList().add(sale);
        setPerson(person, editedPerson);

        // TODO: extract the next few lines to another method (SRP)
        for (Tag t : sale.getTags()) {
            addSaleTag(t);
        }
    }

    /**
     * Removes a sale item from a contact.
     */
    public void removeSaleFromPerson(Person person, Sale sale) {
        Person editedPerson = Person.copyOf(person);
        editedPerson.getSalesList().remove(sale);
        setPerson(person, editedPerson);

        // TODO: extract the below to a new method (SRP)
        Set<Tag> toRemove = new HashSet<>(sale.getTags());
        for (Tag t : sale.getTags()) {
            for (Person p : persons) {
                for (Sale s : p.getSalesList()) {
                    if (s.getTags().contains(t)) {
                        toRemove.remove(t);
                    }
                }
            }
        }
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getContactTagList() {
        return contactTags.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getSaleTagList() {
        return saleTags.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Reminder> getReminderList() {
        return reminders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;

        return persons.equals(otherAddressBook.persons) && reminders.equals(otherAddressBook.reminders)
            && meetings.equals(otherAddressBook.meetings)
            && contactTags.equals(((AddressBook) other).contactTags);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
