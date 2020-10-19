package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableList<Tag> getContactTagList();

    ObservableList<Tag> getSaleTagList();

    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();

    /**
     * Returns an unmodifiable view of the reminders list.
     * This list will not contain any duplicate reminders.
     */
    ObservableList<Reminder> getReminderList();

    /**
     * Returns an unmodifiable view of the sales list.
     * This list will not contain any duplicate sales.
     */
    ObservableList<Sale> getSaleList();

}
