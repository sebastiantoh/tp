package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.dataset.DataSet;
import seedu.address.model.dataset.date.MonthlyCountData;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false)));
        assertTrue(commandResult.equals(new CommandResult("feedback", (Integer) null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false)));

        // different clear value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true)));

        // different theme value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", 1)));

        // different statistics result -> returns false
        assertFalse(commandResult.equals(new CommandResult(
                "feedback", new DataSet<MonthlyCountData>(Collections.emptyList()))));
        assertFalse(new CommandResult(
                "feedback", new DataSet<MonthlyCountData>(Collections.emptyList())).equals(commandResult));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", true, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, true, false).hashCode());

        // different clear value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", false, false, true).hashCode());

        // different theme value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", 1));

        // different statistic result -> returns different hashcode
        assertNotEquals(commandResult.hashCode(),
                new CommandResult("feedback", new DataSet<MonthlyCountData>(Collections.emptyList())).hashCode());
    }
}
