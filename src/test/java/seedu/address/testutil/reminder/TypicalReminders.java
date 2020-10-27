package seedu.address.testutil.reminder;

import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.CARL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Message;
import seedu.address.model.reminder.Reminder;

/**
 * A utility class containing a list of {@code Reminder} objects to be used in tests.
 */
public class TypicalReminders {

    public static final Reminder CALL_ALICE = new Reminder(ALICE, new Message("Call Alice"),
            LocalDateTime.of(2020, 10, 30, 15, 19));
    public static final Reminder EMAIL_BENSON = new Reminder(BENSON, new Message("Email Benson"),
            LocalDateTime.of(2018, 12, 20, 12, 0));
    public static final Reminder SET_MEETING_CARL = new Reminder(CARL, new Message("Set meeting with Carl"),
            LocalDateTime.of(2020, 12, 20, 12, 12));
    public static final Reminder CALL_ALICE_COMPLETED = new Reminder(ALICE, new Message("Call Alice"),
            LocalDateTime.of(2020, 10, 30, 15, 19),
            true);

    // prevents instantiation
    private TypicalReminders() {
    }

    public static List<Reminder> getTypicalReminders() {
        return new ArrayList<>(Arrays.asList(CALL_ALICE, EMAIL_BENSON, SET_MEETING_CARL));
    }
}
