package seedu.address.testutil;

import static seedu.address.testutil.TypicalReminders.getTypicalReminders;
import static seedu.address.testutil.person.TypicalPersons.getTypicalPersons;

import seedu.address.model.AddressBook;
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
        return ab;
    }
}
