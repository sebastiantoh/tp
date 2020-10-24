package seedu.address.logic.commands.sale;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.sale.Sale;

public class MassSaleCommandUtil {
    public static String generateInvalidIndexMessage(String message, List<Index> invalidIndexes) {
        assert !invalidIndexes.isEmpty();
        StringBuilder listOfInvalidIndexes = new StringBuilder(message + ": ");
        for (Index invalidIndex : invalidIndexes) {
            listOfInvalidIndexes.append(invalidIndex.getOneBased()).append(", ");
        }
        String completedListOfInvalidIndexes = listOfInvalidIndexes.toString();
        return completedListOfInvalidIndexes.substring(0, completedListOfInvalidIndexes.length() - 2);
    }

    public static String listAllSales(List<Sale> sales) {
        assert !sales.isEmpty();
        StringBuilder listOfSales = new StringBuilder();
        for (int i = 0; i < sales.size(); i++) {
            listOfSales.append(i + 1).append(". ").append(sales.get(i)).append("\n");
        }
        return "\n" + listOfSales.toString();
    }
}
