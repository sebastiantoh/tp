package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_CARL;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.reminder.TypicalReminders.CALL_ALICE;

import java.text.NumberFormat;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.exceptions.DuplicateReminderException;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalContactTags;
import seedu.address.testutil.TypicalDates;
import seedu.address.testutil.TypicalDurations;
import seedu.address.testutil.TypicalSaleTags;
import seedu.address.testutil.person.PersonBuilder;
import seedu.address.testutil.sale.TypicalSales;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons, Collections.emptyList(),
                Collections.emptyList(), Collections.emptyList());

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasContactTag_contactTagInAddressBook_returnsTrue() {
        addressBook.addContactTag(TypicalContactTags.CLASSMATES);
        assertTrue(addressBook.hasContactTag(TypicalContactTags.CLASSMATES));
    }

    @Test
    public void hasSaleTag_saleTagInAddressBook_returnsTrue() {
        addressBook.addSaleTag(TypicalSaleTags.IMPORTANT);
        assertTrue(addressBook.hasSaleTag(TypicalSaleTags.IMPORTANT));
    }

    @Test
    public void hasContactTag_contactTagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasContactTag(TypicalContactTags.CLASSMATES));
    }

    @Test
    public void hasSaleTag_saleTagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasSaleTag(TypicalSaleTags.IMPORTANT));
    }

    @Test
    public void findByContactTag_contactTagInAddressBook_success() {
        AddressBook addressBook = getTypicalAddressBook();
        assertEquals("Listing 3 contacts associated with: [friends]\n"
                + "1. Alice Pauline Phone: 94351253 Email: alice@example.com "
                + "Address: 123, Jurong West Ave 6, #08-111 Tags: [friends] Remark: Likes chocolates\n"
                + "2. Benson Meier Phone: 98765432 Email: johnd@example.com "
                + "Address: 311, Clementi Ave 2, #02-25 Tags: [owesMoney][friends] Remark: Owes me $10\n"
                + "3. Daniel Meier Phone: 87652533 Email: cornelia@example.com "
                + "Address: 10th street Tags: [friends]\n",
                addressBook.findByContactTag(TypicalContactTags.FRIENDS));
    }

    @Test
    public void findByContactTag_contactTagNotInAddressBook_success() {
        AddressBook addressBookCopy = getTypicalAddressBook();
        assertEquals("No matching contact found for tag: [random]\n",
                addressBookCopy.findByContactTag(new Tag("random")));
    }

    @Test
    public void findSalesBySaleTag_saleTagInAddressBook_success() {
        AddressBook addressBookCopy = new AddressBook();
        addressBookCopy.addPerson(BENSON);
        addressBookCopy.addSale(TypicalSales.APPLE);
        assertEquals("Listing 1 sales items associated with: [fruits]\n"
                        + "1. Apple (Date of Purchase: Fri, 30 Oct 2020, 15:00, Quantity: 10, Unit Price: "
                        + NumberFormat.getCurrencyInstance().format(3.50)
                        + ", Tags: [[fruits]]) (Client: Benson Meier)\n",
                addressBookCopy.findSalesBySaleTag(new Tag("fruits")));
    }

    @Test
    public void findSalesBySaleTag_noMatchingSales_success() {
        assertEquals("No matching sales found for tag: [random]\n",
                addressBook.findSalesBySaleTag(new Tag("random")));
    }

    @Test
    public void findContactsBySaleTag_saleTagInAddressBook_success() {
        AddressBook addressBookCopy = new AddressBook();
        addressBookCopy.addPerson(BENSON);
        addressBookCopy.addSale(TypicalSales.APPLE);
        assertEquals("The following 1 contact(s) have purchased items in this category: [fruits]\n"
                        + "1. Benson Meier Phone: 98765432 Email: johnd@example.com "
                        + "Address: 311, Clementi Ave 2, #02-25 Tags: [owesMoney][friends] Remark: Owes me $10\n",
                addressBookCopy.findContactsBySaleTag(new Tag("fruits")));
    }

    @Test
    public void resetData_withDuplicateReminder_throwsDuplicateReminderException() {
        List<Reminder> newReminders = Arrays.asList(CALL_ALICE, CALL_ALICE);
        AddressBookStub newData = new AddressBookStub(Collections.emptyList(), Collections.emptyList(),
                newReminders, Collections.emptyList());

        assertThrows(DuplicateReminderException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasMeeting(MEET_ALICE));
    }

    @Test
    public void hasMeeting_meetingInAddressBook_returnsTrue() {
        addressBook.addMeeting(MEET_ALICE);
        assertTrue(addressBook.hasMeeting(MEET_ALICE));
    }

    @Test
    public void getConflictingMeetings_noConflicts_returnsEmptyList() {
        assertEquals(Collections.EMPTY_LIST, addressBook.getConflictingMeetings(MEET_ALICE));
    }

    @Test
    public void getConflictingMeetings_hasConflicts_returnsListOfConflictingMeetings() {
        addressBook.addMeeting(MEET_ALICE);
        List<Meeting> expectedConflictingMeeting = List.of(MEET_ALICE);
        assertEquals(expectedConflictingMeeting, addressBook.getConflictingMeetings(MEET_ALICE));
    }

    @Test
    public void getConflictingMeetings_hasConflicts_returnsSortedListOfConflictingMeetings() {
        addressBook.addMeeting(LUNCH_CARL);
        addressBook.addMeeting(MEET_ALICE);

        Meeting meetingWhichSpansTenYear = new Meeting(ALICE, new Message("Long meeting with Alice"),
                TypicalDates.TYPICAL_DATE_2, TypicalDurations.TYPICAL_DURATION_ONE_YEAR.multipliedBy(10));

        List<Meeting> expectedConflictingMeeting = List.of(MEET_ALICE, LUNCH_CARL);
        assertEquals(expectedConflictingMeeting, addressBook.getConflictingMeetings(meetingWhichSpansTenYear));
    }

    @Test
    public void getConflictingMeetings_hasConflictsButExcluded_returnsEmptyList() {
        addressBook.addMeeting(MEET_ALICE);
        assertEquals(Collections.EMPTY_LIST, addressBook.getConflictingMeetings(MEET_ALICE, MEET_ALICE));
    }

    @Test
    public void getMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getMeetingList().remove(0));
    }

    @Test
    public void hasReminder_nullReminder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasReminder(null));
    }

    @Test
    public void hasReminder_reminderNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasReminder(CALL_ALICE));
    }

    @Test
    public void hasReminder_reminderInAddressBook_returnsTrue() {
        addressBook.addReminder(CALL_ALICE);
        assertTrue(addressBook.hasReminder(CALL_ALICE));
    }

    @Test
    public void getReminderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getReminderList().remove(0));
    }

    @Test
    public void getMonthMeetingsCount_valid_success() {
        assertTrue(addressBook.getMonthMeetingsCount(Month.APRIL, Year.now()) >= 0);
    }

    @Test
    public void getMonthlySaleList_valid_success() {
        assertFalse(Objects.isNull(addressBook.getMonthlySaleList(Month.NOVEMBER, Year.of(2020))));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Reminder> reminders = FXCollections.observableArrayList();
        private final ObservableList<Tag> contactTags = FXCollections.observableArrayList();
        private final ObservableList<Tag> saleTags = FXCollections.observableArrayList();
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();
        private final ObservableList<Sale> sales = FXCollections.observableArrayList();


        AddressBookStub(Collection<Person> persons, Collection<Meeting> meetings,
                        Collection<Reminder> reminders, Collection<Sale> sales) {
            this.persons.setAll(persons);
            this.meetings.setAll(meetings);
            this.reminders.setAll(reminders);
            this.sales.setAll(sales);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Tag> getContactTagList() {
            return contactTags;
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }

        @Override
        public ObservableList<Reminder> getReminderList() {
            return reminders;
        }

        @Override
        public ObservableList<Sale> getSaleList() {
            return sales;
        }

        @Override
        public ObservableList<Tag> getSaleTagList() {
            return saleTags;
        }
    }
}
