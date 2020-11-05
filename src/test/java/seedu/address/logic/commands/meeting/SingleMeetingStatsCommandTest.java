package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SingleMeetingStatsCommand}
 */
public class SingleMeetingStatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SingleMeetingStatsCommand(null, null));
        assertThrows(NullPointerException.class, () -> new SingleMeetingStatsCommand(Month.APRIL, null));
        assertThrows(NullPointerException.class, () -> new SingleMeetingStatsCommand(null, Year.of(1850)));
    }

    private void assertCommandSuccess(SingleMeetingStatsCommand command, Month month, Year year) {
        Model expectedModel = new ModelManager(this.model.getAddressBook(), new UserPrefs());
        int expectedCount = expectedModel.getMonthMeetingsCount(month, year);
        String expectedMessage = String.format(SingleMeetingStatsCommand.MESSAGE_SUCCESS,
                month.name(), year.getValue(), expectedCount);

        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validInputs_success() {
        Month month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year year = Year.now();
        SingleMeetingStatsCommand statsCommand = new SingleMeetingStatsCommand(month, year);
        this.assertCommandSuccess(statsCommand, month, year);
    }

    @Test
    public void execute_noInputs_success() {
        Month month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year year = Year.now();
        SingleMeetingStatsCommand statsCommand = new SingleMeetingStatsCommand();
        this.assertCommandSuccess(statsCommand, month, year);
    }

    @Test
    public void equals() {
        Month month = Month.AUGUST;
        Year year = Year.of(2020);
        SingleMeetingStatsCommand command = new SingleMeetingStatsCommand(month, year);

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new SingleMeetingStatsCommand(month, year)));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different month -> returns false
        assertFalse(command.equals(new SingleMeetingStatsCommand(Month.APRIL, year)));

        // different year -> returns false
        assertFalse(command.equals(new SingleMeetingStatsCommand(month, Year.of(120))));
    }
}

