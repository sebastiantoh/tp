package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.date.MonthlyCountData;
import seedu.address.model.dataset.tag.SaleTagCountData;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.UniqueReminderList;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UniqueSaleList;
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
    private final UniqueSaleList sales;

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
        sales = new UniqueSaleList();
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
     * Replaces the contents of the sales list with {@code sales}.
     * {@code reminders} must not contain duplicate sales.
     */
    public void setSales(List<Sale> sales) {
        this.sales.setSales(sales);
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
        setSales(newData.getSaleList());
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
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}. This change will also
     * propagate to all associated meetings and reminders.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        for (Tag t : editedPerson.getTags()) {
            contactTags.add(t);
        }
        sales.updateSalesWithContact(editedPerson);
        meetings.updateMeetingsWithContact(editedPerson);
        reminders.updateRemindersWithContact(editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. All associated meetings and reminders will be removed as well.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        meetings.removeMeetingsWithContact(key);
        reminders.removeRemindersWithContact(key);
        sales.removeSalesWithContact(key);
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
     * Replaces the given sale {@code target} in the list with {@code editedSale}.
     * {@code target} must exist in the address book.
     * The sale identity of {@code editedSale} must not be the same as another existing sale in the address book.
     */
    public void setSale(Sale target, Sale editedSale) {
        requireNonNull(editedSale);

        sales.setSale(target, editedSale);
        for (Tag t : editedSale.getTags()) {
            saleTags.add(t);
        }
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
     * Returns true if deleting this tag {@code target} will result in some Sales not having any tags.
     */
    public boolean anySalesWithoutTags(Tag target) {
        return sales.anySalesWithoutTags(target);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     */
    public void removeSaleTag(Tag key) {
        saleTags.remove(key);
        sales.removeSaleTag(key);
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
        sales.setSaleTag(target, editedTag);
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
        for (Sale s : sales.asUnmodifiableObservableList()) {
            if (s.getTags().contains(target)) {
                output.append(String.format("%d. %s\n", count + 1, s));
                count += 1;
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
        List<Person> contactsWithSaleTag = new ArrayList<>();

        for (Sale s : sales.asUnmodifiableObservableList()) {
            if (s.getTags().contains(target)) {
                Person buyer = persons.asUnmodifiableObservableList().stream()
                        .filter(person -> person.hasSameId(s.getBuyer()))
                        .findAny()
                        .orElse(null);
                assert buyer != null;

                if (!contactsWithSaleTag.contains(buyer)) {
                    contactsWithSaleTag.add(buyer);
                    output.append(String.format("%d. %s\n", count++ + 1, buyer.toString()));
                }
            }
        }
        return String.format("The following %d contact(s) have purchased items in this category: %s\n",
                count,
                target.toString()) + output.toString();
    }

    /**
     * Returns true if all the tags of the provided {@code sale} item exist in StonksBook.
     */
    public boolean saleTagsExist(Set<Tag> tags) {
        for (Tag t : tags) {
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
     * Returns a sorted list of meetings that conflict with {@code meeting}.
     * Meetings in {@code meetingsToExclude} will not be included in the return list even if they do conflict
     * with {@code meeting}.
     *
     * @param meeting           The meeting to check for conflicts against.
     * @param meetingsToExclude The meetings that should not be checked for conflicts.
     * @return A list of meetings that conflict with @{meeting}
     */
    public List<Meeting> getConflictingMeetings(Meeting meeting, Meeting... meetingsToExclude) {
        assert meeting != null;
        assert meetingsToExclude != null;

        List<Meeting> excludedMeetings = List.of(meetingsToExclude);

        List<Meeting> conflictingMeetings = new ArrayList<>();
        for (Meeting meetingToCheckAgainst : meetings) {
            if (!excludedMeetings.contains(meetingToCheckAgainst)
                    && meetingToCheckAgainst.isConflicting(meeting)) {
                conflictingMeetings.add(meetingToCheckAgainst);
            }
        }

        Collections.sort(conflictingMeetings);

        return conflictingMeetings;
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

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting {@code editedMeeting} must not be the same as another existing meeting in the address book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireAllNonNull(target, editedMeeting);

        meetings.setMeeting(target, editedMeeting);
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
     * Returns true if an equivalent sale exists in the address book.
     */
    public boolean hasSale(Sale sale) {
        requireNonNull(sale);
        return sales.contains(sale);
    }

    /**
     * Adds a sale to StonksBook.
     * The sale must not already exist in StonksBook.
     */
    public void addSale(Sale sale) {
        sales.add(sale);

        for (Tag t : sale.getTags()) {
            addSaleTag(t);
        }
    }

    /**
     * Removes {@code sale} from this {@code AddressBook}.
     * {@code sale} must exist in the address book.
     */
    public void removeSale(Sale sale) {
        sales.remove(sale);

        Set<Tag> toRemove = new HashSet<>(sale.getTags());
        for (Tag t : sale.getTags()) {
            for (Sale s : sales) {
                if (s.getTags().contains(t)) {
                    toRemove.remove(t);
                }
            }
        }
    }

    /**
    * Gets the number of meetings in {@code month} and {@code year}.
    */
    public int getMonthMeetingsCount(Month month, Year year) {
        return this.meetings.getMonthMeetingsCount(month, year);
    }

    /**
     * Gets the monthly meeting count for each month between {@code month} and {@code year} and
     * the previous {@code numberOfMonths} - 1 months inclusive.
     */
    public DataSet<MonthlyCountData> getMultipleMonthMeetingsCount(Month month, Year year, int numberOfMonths) {
        return this.meetings.getMultipleMonthMeetingsCount(month, year, numberOfMonths);
    }

    /**
     * Gets the monthly sale list for {@code month} and {@code year}.
     */
    public List<Sale> getMonthlySaleList(Month month, Year year) {
        return this.sales.getMonthlySaleList(month, year);
    }

    /**
     * Gets the monthly sale count for each month between {@code month} and {@code year} and
     * the previous {@code numberOfMonths} - 1 months inclusive.
     */
    public DataSet<MonthlyCountData> getMultipleMonthSaleCount(Month month, Year year, int numberOfMonths) {
        return this.sales.getMultipleMonthSaleCount(month, year, numberOfMonths);
    }

    /**
     * Gets a breakdown of the proportion of sales in each tag.
     */
    public DataSet<SaleTagCountData> getSaleTagCount() {
        return this.sales.getSaleTagCount();
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
    public ObservableList<Sale> getSaleList() {
        return sales.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;

        return persons.equals(otherAddressBook.persons)
            && reminders.equals(otherAddressBook.reminders)
            && meetings.equals(otherAddressBook.meetings)
            && contactTags.equals(((AddressBook) other).contactTags)
            && sales.equals(((AddressBook) other).sales);
    }

    @Override
    public int hashCode() {
        // TODO: refine later
        return persons.hashCode();
    }
}
