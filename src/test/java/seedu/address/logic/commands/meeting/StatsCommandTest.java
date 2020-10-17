package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class StatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_validInputs_success() {
        Model expectedModel = new ModelManager(this.model.getAddressBook(), new UserPrefs());
        Month month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year year = Year.now();
        int expectedCount = model.getMonthMeetingsCount(month, year);
        StatsCommand statsCommand = new StatsCommand(month, year);
        String expectedResult = String.format(StatsCommand.MESSAGE_SUCCESS, month.name(), year.getValue(), expectedCount);
        assertCommandSuccess(statsCommand, model, expectedResult, expectedModel);
    }


    @Test
    public void equals() {
        Month month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year year = Year.now();
        Month month1 = Month.APRIL;
        Year year1 = Year.of(120);
        StatsCommand firstCommand = new StatsCommand(month, year);
        StatsCommand secondCommand = new StatsCommand(month1, year1);
        StatsCommand thirdCommand = new StatsCommand(month, year1);
        StatsCommand fourthCommand = new StatsCommand(month1, year);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        StatsCommand firstCommandCopy = new StatsCommand(month, year);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different month -> returns false
        assertFalse(firstCommand.equals(thirdCommand));

        // different year -> returns false
        assertFalse(firstCommand.equals(fourthCommand));
    }
}

