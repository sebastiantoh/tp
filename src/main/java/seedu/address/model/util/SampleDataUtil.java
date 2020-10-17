package seedu.address.model.util;

import java.math.BigDecimal;
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
    /** Contacts */
    private static final Person ALEX_YEOH = new Person(1, new Name("Alex Yeoh"), new Phone("87438807"),
            new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
            getTagSet("friends"), new Remark("Not available on Fridays"), new BigDecimal("3382.5"));
    private static final Person BERNICE_YU = new Person(2, new Name("Bernice Yu"),
            new Phone("99272758"), new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("colleagues", "friends"),
            new Remark("Owns a small stationery business"), new BigDecimal("1788"));
    private static final Person CHARLOTTE_OLIVEIRO = new Person(3, new Name("Charlotte Oliveiro"), new Phone("93210283"),
            new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
            getTagSet("neighbours"), new Remark(""), new BigDecimal("960"));
    private static final Person DAVID_LI = new Person(4, new Name("David Li"), new Phone("91031282"),
            new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
            getTagSet("family"), new Remark(""), BigDecimal.ZERO);
    private static final Person IRFAN_IBRAHIM = new Person(5, new Name("Irfan Ibrahim"), new Phone("92492021"),
            new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
            getTagSet("classmates"), new Remark("Interested in paper-based products"), BigDecimal.ZERO);
    private static final Person ROY_BALAKRISHNAN = new Person(6, new Name("Roy Balakrishnan"), new Phone("92624417"),
            new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
            getTagSet("colleagues"), new Remark(""), BigDecimal.ZERO);

    /** Sales */
    private static final Sale NOTEBOOK = new Sale(new ItemName("Notebook"), ALEX_YEOH.getId(),
            LocalDateTime.of(2020, 11, 29, 15, 40),
            new Quantity("10"), new UnitPrice(new BigDecimal("3.5")), new HashSet<Tag>());
    private static final Sale BLACK_PEN = new Sale(new ItemName("Black Pen"), BERNICE_YU.getId(),
            LocalDateTime.of(2020, 10, 6, 9, 50),
            new Quantity("300"), new UnitPrice(new BigDecimal("2.0")), new HashSet<Tag>());
    private static final Sale BINDER = new Sale(new ItemName("Binder"), ALEX_YEOH.getId(),
            LocalDateTime.of(2020, 11, 13, 11, 45),
            new Quantity("250"), new UnitPrice(new BigDecimal("4.99")), new HashSet<Tag>());
    private static final Sale TAPE = new Sale(new ItemName("Scotch Tape"), ALEX_YEOH.getId(),
            LocalDateTime.of(2020, 10, 17, 15, 20),
            new Quantity("1000"), new UnitPrice(new BigDecimal("2.1")), new HashSet<Tag>());
    private static final Sale ERASER = new Sale(new ItemName("Eraser"), CHARLOTTE_OLIVEIRO.getId(),
            LocalDateTime.of(2020, 9, 20, 14, 10),
            new Quantity("1200"), new UnitPrice(new BigDecimal("0.8")), new HashSet<Tag>());
    private static final Sale RULER = new Sale(new ItemName("Ruler"), BERNICE_YU.getId(),
            LocalDateTime.of(2020, 11, 1, 19, 00),
            new Quantity("1200"), new UnitPrice(new BigDecimal("0.99")), new HashSet<Tag>());

    public static Person[] getSamplePersons() {
        return new Person[] {
            ALEX_YEOH, BERNICE_YU, CHARLOTTE_OLIVEIRO, DAVID_LI, IRFAN_IBRAHIM, ROY_BALAKRISHNAN
        };
    }

    public static UniqueSaleList getSampleSales() {
        return new UniqueSaleList().add(NOTEBOOK).add(BLACK_PEN).add(BINDER).add(TAPE).add(ERASER).add(RULER);
    }

    public static UniqueReminderList getSampleReminderList() {
        UniqueReminderList reminders = new UniqueReminderList();
        reminders.add(new Reminder(ALEX_YEOH, "Send follow up email",
                LocalDateTime.of(2020, 11, 30, 15, 30)));
        reminders.add(new Reminder(CHARLOTTE_OLIVEIRO, "Draft up sales proposal for upcoming meeting",
                LocalDateTime.of(2020, 12, 15, 9, 0)));

        return reminders;
    }

    public static UniqueMeetingList getSampleMeetingList() {
        UniqueMeetingList meetings = new UniqueMeetingList();
        meetings.add(new Meeting(BERNICE_YU, "Sales Call",
                LocalDateTime.of(2020, 11, 20, 15, 30), Duration.ofMinutes(30)));
        meetings.add(new Meeting(CHARLOTTE_OLIVEIRO, "Lunch to discuss new recurring purchase requirements",
                LocalDateTime.of(2020, 12, 20, 12, 0), Duration.ofMinutes(90)));

        return meetings;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Sale sampleSale : getSampleSales()) {
            sampleAb.addSale(sampleSale);
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
