package seedu.address.testutil;

import static seedu.address.testutil.meeting.TypicalMeetings.getTypicalMeetings;
import static seedu.address.testutil.person.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.person.TypicalPersons.getTypicalPersonsInReverse;
import static seedu.address.testutil.reminder.TypicalReminders.getTypicalReminders;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;

public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical entries.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Reminder reminder : getTypicalReminders()) {
            ab.addReminder(reminder);
        }
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }

    /**
     * Returns an {@code AddressBook} with all the typical entries in reverse order.
     */
    public static AddressBook getTypicalAddressBookInReverse() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersonsInReverse()) {
            ab.addPerson(person);
        }
        for (Reminder reminder : getTypicalReminders()) {
            ab.addReminder(reminder);
        }
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }
}
