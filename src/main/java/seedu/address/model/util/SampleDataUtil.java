package seedu.address.model.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.UniqueReminderList;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UniqueSaleList;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Sale NOTEBOOK = new Sale(new ItemName("Notebook"), new Quantity("10"),
            new UnitPrice(3, 50), new HashSet<Tag>());
    private static final Sale BLACK_PEN = new Sale(new ItemName("Black Pen"), new Quantity("300"),
            new UnitPrice(2, 0), new HashSet<Tag>());
    private static final Sale BINDER = new Sale(new ItemName("Binder"), new Quantity("250"),
            new UnitPrice(4, 99), new HashSet<Tag>());
    private static final Sale TAPE = new Sale(new ItemName("Scotch Tape"), new Quantity("1000"),
            new UnitPrice(2, 10), new HashSet<Tag>());
    private static final Sale ERASER = new Sale(new ItemName("Eraser"), new Quantity("1200"),
            new UnitPrice(0, 80), new HashSet<Tag>());
    private static final Sale RULER = new Sale(new ItemName("Ruler"), new Quantity("1200"),
            new UnitPrice(0, 99), new HashSet<Tag>());

    /** UniqueSaleList */
    private static final UniqueSaleList SALE_LIST_1 = new UniqueSaleList().add(NOTEBOOK).add(BINDER).add(TAPE);
    private static final UniqueSaleList SALE_LIST_2 = new UniqueSaleList().add(RULER).add(BLACK_PEN).add(TAPE);
    private static final UniqueSaleList SALE_LIST_3 = new UniqueSaleList().add(BINDER).add(ERASER).add(NOTEBOOK);
    private static final UniqueSaleList SALE_LIST_4 = new UniqueSaleList().add(BLACK_PEN).add(RULER).add(TAPE);

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends"),
                    new Remark("Not available on Fridays"), SALE_LIST_1),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("colleagues", "friends"),
                    new Remark("Owns a small stationery business"), SALE_LIST_2),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new Remark(""), SALE_LIST_3),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Remark(""), SALE_LIST_4),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Remark("Interested in paper-based products")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Remark(""))
        };
    }

    public static UniqueReminderList getSampleReminderList() {
        Person[] samplePersons = getSamplePersons();
        Person alex = samplePersons[0];
        Person charlotte = samplePersons[2];

        UniqueReminderList reminders = new UniqueReminderList();
        reminders.add(new Reminder(alex, "Send follow up email",
                LocalDateTime.of(2020, 11, 30, 15, 30)));
        reminders.add(new Reminder(charlotte, "Draft up sales proposal for upcoming meeting",
                LocalDateTime.of(2020, 12, 15, 9, 0)));

        return reminders;
    }

    public static UniqueMeetingList getSampleMeetingList() {
        Person[] samplePersons = getSamplePersons();
        Person bernice = samplePersons[1];
        Person charlotte = samplePersons[2];

        UniqueMeetingList meetings = new UniqueMeetingList();
        meetings.add(new Meeting(bernice, "Sales Call",
                LocalDateTime.of(2020, 11, 20, 15, 30), Duration.ofMinutes(30)));
        meetings.add(new Meeting(charlotte, "Lunch to discuss new recurring purchase requirements",
                LocalDateTime.of(2020, 12, 20, 12, 0), Duration.ofMinutes(90)));

        return meetings;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Reminder sampleReminder : getSampleReminderList()) {
            sampleAb.addReminder(sampleReminder);
        }
        for (Meeting sampleMeeting : getSampleMeetingList()) {
            sampleAb.addMeeting(sampleMeeting);
        }
        return sampleAb;
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
