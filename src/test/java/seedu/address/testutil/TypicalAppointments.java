package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment MEET_ALICE =
        new Appointment(ALICE, "Meet Alice to discuss pricing", LocalDateTime.of(2020, 10, 30, 15, 19),
            Duration.ofMinutes(60));

    public static final Appointment PRESENT_PROPOSAL_BENSON =
        new Appointment(BENSON, "Present proposal to Benson", LocalDateTime.of(2018, 12, 20, 12, 0),
            Duration.ofMinutes(90));

    public static final Appointment LUNCH_CARL =
        new Appointment(CARL, "Lunch with Carl", LocalDateTime.of(2020, 12, 20, 12, 12),
            Duration.ofMinutes(45));


    private TypicalAppointments() {
    } // prevents instantiation

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(MEET_ALICE, PRESENT_PROPOSAL_BENSON, LUNCH_CARL));
    }
}
