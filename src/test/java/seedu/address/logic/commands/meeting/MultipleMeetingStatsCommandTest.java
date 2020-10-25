package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBookInReverse;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import seedu.address.commons.dataset.date.MonthlyCountDataSet;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MultipleMeetingStatsCommand}
 */
public class MultipleMeetingStatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookInReverse(), new UserPrefs());

    @Test
    public void execute_validInputs_success() throws CommandException {
        Model expectedModel = new ModelManager(this.model.getAddressBook(), new UserPrefs());
        Month currentMonth = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
        Year currentYear = Year.now();
        int numberOfMonths = 3;
        MonthlyCountDataSet expectedResult =
                expectedModel.getMultipleMonthMeetingsCount(currentMonth, currentYear, numberOfMonths);
        expectedResult.setTitle(MultipleMeetingStatsCommand.DATASET_TITLE);

        StatsCommand statsCommand = new MultipleMeetingStatsCommand(numberOfMonths);
        CommandResult expectedCommandResult = new CommandResult(
                MultipleMeetingStatsCommand.MESSAGE_SUCCESS, expectedResult);
        CommandResult actualResult = statsCommand.execute(model);
        assertEquals(expectedCommandResult, actualResult);
        assertEquals(expectedModel, model);
    }


    @Test
    public void equals() {
        StatsCommand firstCommand = new MultipleMeetingStatsCommand(1);
        StatsCommand secondCommand = new MultipleMeetingStatsCommand(1);
        StatsCommand thirdCommand = new MultipleMeetingStatsCommand(2);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        assertTrue(firstCommand.equals(secondCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different numberOfMonths -> returns false
        assertFalse(firstCommand.equals(thirdCommand));
    }
}
