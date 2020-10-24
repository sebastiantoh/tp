package seedu.address.logic.commands.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.sale.Sale;

public interface MassSaleCommand {
    default String generateInvalidIndexMessage(List<Index> invalidIndexes) {
        StringBuilder listOfInvalidIndexes = new StringBuilder(MESSAGE_INVALID_SALE_DISPLAYED_INDEX + ": ");
        for (Index invalidIndex : invalidIndexes) {
            listOfInvalidIndexes.append(invalidIndex.getOneBased()).append(", ");
        }
        String completedListOfInvalidIndexes = listOfInvalidIndexes.toString();
        return completedListOfInvalidIndexes.substring(0, completedListOfInvalidIndexes.length() - 2);
    }

    default String listAllSales(List<Sale> sales) {
        StringBuilder listOfSales = new StringBuilder();
        for (int i = 0; i < sales.size(); i++) {
            listOfSales.append(i + 1).append(". ").append(sales.get(i)).append("\n");
        }
        return "\n" + listOfSales.toString();
    }
}
