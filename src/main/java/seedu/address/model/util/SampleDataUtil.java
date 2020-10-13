package seedu.address.model.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
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
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        getTagSet("friends"), new Remark("")),
                new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        getTagSet("colleagues", "friends"), new Remark("")),
                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        getTagSet("neighbours"), new Remark("")),
                new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        getTagSet("family"), new Remark("")),
                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        getTagSet("classmates"), new Remark("")),
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
