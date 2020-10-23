package seedu.address.logic.commands.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEXES;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.sale.Sale;

public interface MassSaleCommand {
    default String generateInvalidIndexMessage(List<Index> invalidIndexes) {
        StringBuilder listOfInvalidIndexes = new StringBuilder();
        for (int i = 0; i < invalidIndexes.size(); i++) {
            listOfInvalidIndexes.append(i + 1).append(". ").append(invalidIndexes.get(i));
        }
        return MESSAGE_INVALID_PERSON_DISPLAYED_INDEXES + "\n" + listOfInvalidIndexes.toString();
    }

    default String listAllSales(List<Sale> sales) {
        StringBuilder listOfSales = new StringBuilder();
        for (int i = 0; i < sales.size(); i++) {
            listOfSales.append(i + 1).append(". ").append(sales.get(i));
        }

        return "\n" + listOfSales.toString();
    }
}
