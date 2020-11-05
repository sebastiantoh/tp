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
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasContactTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasSaleTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addContactTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addSaleTag(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editContactTag(Tag target, Tag editedTag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void editSaleTag(Tag target, Tag editedTag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteContactTag(Tag target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean anySalesWithoutTags(Tag tagToDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteSaleTag(Tag target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String findByContactTag(Tag target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String findSalesBySaleTag(Tag target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String findContactsBySaleTag(Tag target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean saleTagsExist(Set<Tag> tag) {
        return true;
    }

    @Override
    public boolean contactTagsExist(Person person) {
        return true;
    }

    @Override
    public ObservableList<Tag> getContactTagList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Tag> getSaleTagList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getAllPersons() {
        return new UniquePersonList().asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getSortedPersonList() {
        return new UniquePersonList().asUnmodifiableObservableList();
    }

    public void updateSortedPersonList(Comparator<Person> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Sale> getFilteredSaleList() {
        return null;
    }

    @Override
    public void updateFilteredSaleList(Predicate<Sale> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Sale> getSortedSaleList() {
        return null;
    }

    @Override
    public void updateSortedSaleList(Comparator<Sale> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Meeting> getSortedMeetingList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<Meeting> getConflictingMeetings(Meeting meeting, Meeting... meetingsToExclude) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteMeeting(Meeting target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addMeeting(Meeting meeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Reminder> getSortedReminderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasReminder(Reminder reminder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteReminder(Reminder target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addReminder(Reminder reminder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setReminder(Reminder target, Reminder editedReminder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRemindersList(Predicate<Reminder> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Reminder> getFilteredReminderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasSale(Sale sale) {
        return false;
    }

    @Override
    public void addSale(Sale sale) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setSale(Sale target, Sale editedSale) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeSale(Sale sale) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getMonthMeetingsCount(Month month, Year year) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public DataSet<MonthlyCountData> getMultipleMonthMeetingsCount(Month month, Year year, int numberOfMonths) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public DataSet<MonthlyCountData> getMultipleMonthSaleCount(Month month, Year year, int numberOfMonths) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public DataSet<SaleTagCountData> getSaleTagCount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void initialiseLatestContactId() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getLatestContactId() {
        return 0;
    }

    public List<Sale> getMonthlySaleList(Month month, Year year) {
        throw new AssertionError("This method should not be called.");
    }
}
