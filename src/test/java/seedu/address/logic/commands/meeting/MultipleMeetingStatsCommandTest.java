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

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.date.MonthlyCountData;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MultipleMeetingStatsCommand}
 */
public class MultipleMeetingStatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_validInputs_success() {
        Model expectedModel = new ModelManager(this.model.getAddressBook(), new UserPrefs());

        Month currentMonth = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year currentYear = Year.now();
        int numberOfMonths = 3;

        DataSet<MonthlyCountData> expectedResult =
                expectedModel.getMultipleMonthMeetingsCount(currentMonth, currentYear, numberOfMonths);
        expectedResult.setTitle(MultipleMeetingStatsCommand.DATASET_TITLE);

        CommandResult expectedCommandResult = new CommandResult(
                MultipleMeetingStatsCommand.MESSAGE_SUCCESS, expectedResult);

        StatsCommand statsCommand = new MultipleMeetingStatsCommand(numberOfMonths);

        assertCommandSuccess(statsCommand, model, expectedCommandResult, expectedModel);
    }


    @Test
    public void equals() {
        MultipleMeetingStatsCommand command = new MultipleMeetingStatsCommand(1);

        // same object -> returns true
        assertTrue(command.equals(command));

        // same values -> returns true
        assertTrue(command.equals(new MultipleMeetingStatsCommand(1)));

        // different types -> returns false
        assertFalse(command.equals(1));

        // null -> returns false
        assertFalse(command.equals(null));

        // different numberOfMonths -> returns false
        assertFalse(command.equals(new MultipleMeetingStatsCommand(2)));
    }
}
