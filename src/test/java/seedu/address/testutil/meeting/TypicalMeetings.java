package seedu.address.testutil.meeting;

import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.CARL;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting MEET_ALICE =
        new Meeting(ALICE, "Meet Alice to discuss pricing", LocalDateTime.of(2020, 10, 30, 15, 19),
            Duration.ofMinutes(60));

    public static final Meeting PRESENT_PROPOSAL_BENSON =
        new Meeting(BENSON, "Present proposal to Benson", LocalDateTime.of(2018, 12, 20, 12, 0),
            Duration.ofMinutes(90));

    public static final Meeting LUNCH_CARL =
        new Meeting(CARL, "Lunch with Carl", LocalDateTime.of(2020, 12, 20, 12, 12),
            Duration.ofMinutes(45));


    private TypicalMeetings() {
    } // prevents instantiation

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEET_ALICE, PRESENT_PROPOSAL_BENSON, LUNCH_CARL));
    }
}
