package seedu.address.logic.commands.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests and unit tests for MonthlyListCommand.
 */
class MonthlyListCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MonthlyListCommand(null, null));
        assertThrows(NullPointerException.class, () -> new MonthlyListCommand(null, Year.now()));
        assertThrows(NullPointerException.class, () -> new MonthlyListCommand(Month.APRIL, null));
    }

    @Test
    public void execute_valid_success() {
        MonthlyListCommand monthlyListCommand = new MonthlyListCommand(Month.NOVEMBER, Year.of(2020));

        String expectedResult = String.format(MonthlyListCommand.MESSAGE_SUCCESS,
                1, Month.NOVEMBER, Year.of(2020));

        CommandResult actualCommandResult = monthlyListCommand.execute(model);
        assertEquals(new CommandResult(expectedResult), actualCommandResult);
    }

}
