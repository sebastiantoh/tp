package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code UserInputHistory}.
 */
public class UserInputHistoryTest {

    @Test
    public void addToHistory_success() {
        String testInput = "test input";
        UserInputHistory inputHistoryUnit = new UserInputHistory();
        inputHistoryUnit.addToHistory(testInput);

        LinkedList<String> expectedHistoryList = new LinkedList<>();
        expectedHistoryList.add(testInput);

        assertEquals(inputHistoryUnit.getHistoryList(), expectedHistoryList);
    }

    @Test
    public void getPreviousInput_noPreviousInput_returnsEmptyString() {
        UserInputHistory inputHistoryUnit = new UserInputHistory();
        assertEquals(inputHistoryUnit.getPreviousInput(), "");
    }

    @Test
    public void getPreviousInput_hasPreviousInput_success() {
        String testInput = "test input";
        UserInputHistory inputHistoryUnit = new UserInputHistory();
        inputHistoryUnit.addToHistory(testInput);

        assertEquals(inputHistoryUnit.getPreviousInput(), testInput);
    }

    @Test
    public void getNextInput_noNextInput_returnsEmptyString() {
        UserInputHistory inputHistoryUnit = new UserInputHistory();
        assertEquals(inputHistoryUnit.getNextInput(), "");
    }

    @Test
    public void getNextInput_hasNextInput_success() {
        String testInput = "test input";
        UserInputHistory inputHistoryUnit = new UserInputHistory();
        inputHistoryUnit.addToHistory(testInput);
        inputHistoryUnit.getListIterator().previous();

        assertEquals(inputHistoryUnit.getNextInput(), testInput);
    }

}
