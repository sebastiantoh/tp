package seedu.address.model;

import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.date.MonthlyCountData;
import seedu.address.model.dataset.tag.SaleTagCountData;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that checks whether the {@code Person} is not archived.
     */
    Predicate<Person> PREDICATE_SHOW_UNARCHIVED_PERSONS = person -> !person.isArchived();

    /**
     * {@code Predicate} that checks whether the {@code Person} is archived.
     */
    Predicate<Person> PREDICATE_SHOW_ARCHIVED_PERSONS = Person::isArchived;

    /**
     * {@code Comparator} that is used for default sorting of person list in alphabetical order,
     * ignoring case.
     */
    Comparator<Person> DEFAULT_PERSON_COMPARATOR = (person1, person2) -> (
            person1.getName().fullName.compareToIgnoreCase(person2.getName().fullName));

    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Sale> PREDICATE_SHOW_ALL_SALES = unused -> true;

    /**
     * {@code Predicate} that is used for filtering completed reminders.
     */
    Predicate<Reminder> PREDICATE_SHOW_COMPLETED_REMINDERS = Reminder::isCompleted;

    /**
     * {@code Predicate} that is used for filtering pending reminders.
     */
    Predicate<Reminder> PREDICATE_SHOW_PENDING_REMINDERS = reminder -> !reminder.isCompleted();

    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

    /**
     * {@code Predicate} that checks whether the {@code Meeting} is not yet over.
     */
    Predicate<Meeting> PREDICATE_SHOW_UPCOMING_MEETINGS = meeting -> !meeting.isOver();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook.
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a contact tag with the same name {@code tag} exists in StonksBook.
     */
    boolean hasContactTag(Tag tag);

    /**
     * Returns true if a sale tag with the same name {@code tag} exists in StonksBook.
     */
    boolean hasSaleTag(Tag tag);

    /**
     * Adds the given {@code tag} to the contact tag list.
     * {@code tag} must not already exist in StonksBook.
     */
    void addContactTag(Tag tag);

    /**
     * Adds the given {@code tag} to the sales tag list.
     * {@code tag} must not already exist in StonksBook.
     */
    void addSaleTag(Tag tag);

    /**
     * Replaces the given {@code tag} with {@code editedTag}.
     * {@code target} must exist in the contact tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in StonksBook.
     */
    void editContactTag(Tag target, Tag editedTag);

    /**
     * Replaces the given {@code tag} with {@code editedTag}.
     * {@code target} must exist in the sale tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in StonksBook.
     */
    void editSaleTag(Tag target, Tag editedTag);

    /**
     * Deletes the given tag from the contact tag list.
     * The tag must exist in StonksBook.
     */
    void deleteContactTag(Tag target);

    /**
     * Returns true if deleting this tag {@code tagToDelete} will result in some Sales not having any tags.
     */
    boolean anySalesWithoutTags(Tag tagToDelete);

    /**
     * Deletes the given tag from the sale tag list.
     * The tag must exist in StonksBook.
     */
    void deleteSaleTag(Tag target);

    /**
     * Returns the number of contacts associated with {@code target} tag.
     */
    String findByContactTag(Tag target);

    /**
     * Lists all sale items associated with {@code target} tag.
     */
    String findSalesBySaleTag(Tag target);

    /**
     * Lists all contacts who have bought items associated with {@code target} tag.
     */
    String findContactsBySaleTag(Tag target);

    /**
     * Returns if the {@code sale} item's tags are present in StonksBook.
     */
    boolean saleTagsExist(Set<Tag> tags);

    /**
     * Returns if the {@code person}'s tags are present in StonksBook.
     */
    boolean contactTagsExist(Person person);

    /**
     * Returns an unmodifiable view of the contact tag list.
     */
    ObservableList<Tag> getContactTagList();

    /**
     * Returns an unmodifiable view of the sale tag list.
     */
    ObservableList<Tag> getSaleTagList();

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the person list.
     */
    ObservableList<Person> getAllPersons();

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the sorted person list.
     */
    ObservableList<Person> getSortedPersonList();

    /**
     * Updates the comparator of the sorted person list to sort by the given {@code comparator}.
     */
    void updateSortedPersonList(Comparator<Person> comparator);

    /**
     * Returns an unmodifiable view of the filtered sale list.
     */
    ObservableList<Sale> getFilteredSaleList();

    /**
     * Updates the filter of the filtered sale list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSaleList(Predicate<Sale> predicate);

    /**
     * Returns an unmodifiable view of the sorted sale list.
     */
    ObservableList<Sale> getSortedSaleList();

    /**
     * Updates the comparator of the sorted sale list to sort by the given {@code comparator}.
     */
    void updateSortedSaleList(Comparator<Sale> comparator);

    /**
     * Returns an unmodifiable view of the filtered meeting list.
     */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    /**
     * Returns an unmodifiable view of the sorted meeting list.
     * .
     */
    ObservableList<Meeting> getSortedMeetingList();

    /**
     * Returns true if a meeting with same fields as {@code meeting} exists in StonksBook.
     */
    boolean hasMeeting(Meeting meeting);

    /**
     * Returns a sorted list of meetings that conflict with {@code meeting}.
     * Meetings in {@code meetingsToExclude} will not be included in the return list even if they do conflict
     * with {@code meeting}.
     *
     * @param meeting           The meeting to check for conflicts against.
     * @param meetingsToExclude The meetings that should not be checked for conflicts.
     * @return A list of meetings that conflict with @{meeting}
     */
    List<Meeting> getConflictingMeetings(Meeting meeting, Meeting... meetingsToExclude);

    /**
     * Deletes the given meeting.
     * {@code target} must exist in StonksBook.
     */
    void deleteMeeting(Meeting target);

    /**
     * Adds the given meeting.
     * {@code meeting} must not already exist in StonksBook.
     */
    void addMeeting(Meeting meeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting {@code editedMeeting} must not be the same as another existing meeting in the address
     * book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /**
     * Returns an unmodifiable view of the reminder list
     * .
     */
    ObservableList<Reminder> getSortedReminderList();

    /**
     * Returns true if a reminder with the same fields {@code reminder} exists in StonksBook.
     */
    boolean hasReminder(Reminder reminder);

    /**
     * Deletes the given reminder.
     * The reminder must exist in StonksBook.
     */
    void deleteReminder(Reminder target);

    /**
     * Adds the given reminder.
     * {@code reminder} must not already exist in StonksBook.
     */
    void addReminder(Reminder reminder);

    /**
     * Replaces the given reminder {@code target} with {@code editedReminder}.
     * {@code target} must exist in the address book.
     * The reminder {@code editedReminder} must not be the same as another existing reminder in the address
     * book.
     */
    void setReminder(Reminder target, Reminder editedReminder);

    /**
     * Set the predicate for filtering of reminders list.
     */
    void updateFilteredRemindersList(Predicate<Reminder> predicate);

    /**
     * Returns an unmodifiable list view of filtered reminders.
     */
    ObservableList<Reminder> getFilteredReminderList();

    /**
     * Returns true if a sale with the same fields {@code sale} exists in StonksBook.
     */
    boolean hasSale(Sale sale);

    /**
     * Adds the given sale.
     * {@code sale} must not already exist in StonksBook.
     */
    void addSale(Sale sale);

    /**
     * Replaces the given sale {@code target} with {@code editedSale}.
     * {@code target} must exist in the address book.
     * The sale identity of {@code editedSale} must not be the same as another existing sale in the address book.
     */
    void setSale(Sale target, Sale editedSale);

    /**
     * Removes the given sale.
     * The sale must exist in StonksBook.
     */
    void removeSale(Sale sale);

    /**
     * Gets the number of meetings in {@code month} and {@code year}.
     */
    int getMonthMeetingsCount(Month month, Year year);

    /**
     * Gets the monthly meeting count for each month between {@code month} and {@code year} and
     * the previous {@code numberOfMonths} - 1 months inclusive.
     */
    DataSet<MonthlyCountData> getMultipleMonthMeetingsCount(Month month, Year year, int numberOfMonths);

    /**
     * Gets the monthly sale count for each month between {@code month} and {@code year} and
     * the previous {@code numberOfMonths} - 1 months inclusive.
     */
    DataSet<MonthlyCountData> getMultipleMonthSaleCount(Month month, Year year, int numberOfMonths);

    /**
     * Gets a breakdown of the proportion of sales in each tag.
     */
    DataSet<SaleTagCountData> getSaleTagCount();

    void initialiseLatestContactId();

    int getLatestContactId();

    /**
     * Gets the monthly sale list for {@code month} and {@code year}.
     */
    List<Sale> getMonthlySaleList(Month month, Year year);
}
