package seedu.address.logic.commands.sale;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.sale.Sale;

public class MassSaleCommandUtil {
    /**
     * Generates a message indicating which indexes were invalid.
     * @param message The message preceding the list of invalid indexes.
     * @param invalidIndexes The list of invalid indexes provided by the user.
     * @return A message indicating which indexes were invalid.
     */
    public static String generateInvalidIndexMessage(String message, List<Index> invalidIndexes) {
        assert !invalidIndexes.isEmpty();
        StringBuilder listOfInvalidIndexes = new StringBuilder(message + ": ");
        for (Index invalidIndex : invalidIndexes) {
            listOfInvalidIndexes.append(invalidIndex.getOneBased()).append(", ");
        }
        String completedListOfInvalidIndexes = listOfInvalidIndexes.toString();
        return completedListOfInvalidIndexes.substring(0, completedListOfInvalidIndexes.length() - 2);
    }

    /**
     * Prints all sales in a list form.
     * @param sales The list of sales to be printed.
     * @return A string containing a list of sales.
     */
    public static String listAllSales(List<Sale> sales) {
        assert !sales.isEmpty();
        StringBuilder listOfSales = new StringBuilder();
        for (int i = 0; i < sales.size(); i++) {
            listOfSales.append(i + 1).append(". ").append(sales.get(i)).append("\n");
        }
        return "\n" + listOfSales.toString();
    }
}
