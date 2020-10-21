package seedu.address.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Stores past user inputs within the session.
 */
public class UserInputHistory {

    private final LinkedList<String> inputHistory;

    private ListIterator<String> inputHistoryIterator;

    /**
     * Initialises an instance of {@code UserInputHistory} with an empty list and its iterator.
     */
    public UserInputHistory() {
        this.inputHistory = new LinkedList<>();
        this.inputHistoryIterator = inputHistory.listIterator(inputHistory.size());
    }

    /**
     * Gets the input history list. Should only be used for tests.
     */
    List<String> getHistoryList() {
        return inputHistory;
    }

    /**
     * Gets the history list iterator. Should only be used for tests.
     */
    ListIterator<String> getListIterator() {
        return inputHistoryIterator;
    }

    /**
     * Gets the input before the input the user navigated to in the input history.
     * Returns empty String if there is no previous input.
     */
    public String getPreviousInput() {
        if (inputHistoryIterator.hasPrevious()) {
            return inputHistoryIterator.previous();
        }
        return "";
    }

    /**
     * Gets the input after the input the user navigated to in the input history.
     * Returns empty String if there is no input after the current input.
     */
    public String getNextInput() {
        if (inputHistoryIterator.hasNext()) {
            return inputHistoryIterator.next();
        }
        return "";
    }

    /**
     * Adds the input entered by the user into the input history.
     */
    public void addToHistory(String userInput) {
        inputHistoryIterator = null;
        inputHistory.add(userInput);
        inputHistoryIterator = inputHistory.listIterator(inputHistory.size());
    }

}
