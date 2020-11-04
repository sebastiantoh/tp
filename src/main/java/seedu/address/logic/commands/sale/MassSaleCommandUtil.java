package seedu.address.logic.commands.sale;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.sale.Sale;

public class MassSaleCommandUtil {

    /**
     * Checks if person indexes provided are valid.
     * @param persons The list of persons in StonksBook.
     * @param indexList The list of indexes provided to the Sale command.
     * @return True if all person indexes provided are valid, and throws a CommandException otherwise.
     * @throws CommandException If any person indexes provided are not valid.
     */
    public static boolean arePersonIndexesValid(List<Person> persons, List<Index> indexList) throws CommandException {
        List<Index> invalidIndexes = indexList
                .parallelStream().filter(personIndex -> personIndex.getZeroBased() >= persons.size())
                .collect(Collectors.toList());

        if (!invalidIndexes.isEmpty()) {
            throw new CommandException(MassSaleCommandUtil.generateInvalidIndexMessage(
                    Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEXES, invalidIndexes));
        }
        return true;
    }

    /**
     * Checks if sale indexes provided are valid.
     * @param sales The list of sales in StonksBook.
     * @param indexList The list of indexes provided to the Sale command.
     * @return True if all sale indexes provided are valid, and throws a CommandException otherwise.
     * @throws CommandException If any sale indexes provided are not valid.
     */
    public static boolean areSaleIndexesValid(List<Sale> sales, List<Index> indexList) throws CommandException {
        List<Index> invalidIndexes = indexList
                .parallelStream().filter(personIndex -> personIndex.getZeroBased() >= sales.size())
                .collect(Collectors.toList());

        if (!invalidIndexes.isEmpty()) {
            throw new CommandException(MassSaleCommandUtil.generateInvalidIndexMessage(
                    Messages.MESSAGE_INVALID_SALE_DISPLAYED_INDEX, invalidIndexes));
        }
        return true;
    }

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
            listOfInvalidIndexes.append(invalidIndex.getOneBasedInString()).append(", ");
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
