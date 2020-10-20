package seedu.address.model;

import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.MonthlyCountData;
import seedu.address.commons.core.GuiSettings;
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
     * {@code Comparator} that is used for default sorting of person list in alphabetical order,
     * ignoring case.
     */
    Comparator<Person> DEFAULT_PERSON_COMPARATOR = (person1, person2) -> (
            person1.getName().fullName.compareToIgnoreCase(person2.getName().fullName));

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
     * Lists all existing tags.
     */
    String listTags();

    /**
     * Returns if the {@code sale} item's tags are present in StonksBook.
     */
    boolean saleTagsExist(Sale sale);

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
     * Returns an unmodifiable view of the sorted meeting list.
     * .
     */
    ObservableList<Meeting> getSortedMeetingList();

    /**
     * Returns true if an meeting with same fields as {@code meeting} exists in StonksBook.
     */
    boolean hasMeeting(Meeting meeting);

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
     * Returns true if a sale with the same fields {@code sale} exists in StonksBook.
     */
    boolean hasSale(Sale sale);

    /**
     * Adds the given sale.
     * {@code sale} must not already exist in StonksBook.
     */
    void addSale(Sale sale);

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
     * Gets multiple number of meeting count for months between {@code month} and {@code year} and
     * the previous {@code numberOfMonths} - 1 months inclusive.
     */
    List<MonthlyCountData> getMultipleMonthMeetingsCount(Month month, Year year, int numberOfMonths);
}
