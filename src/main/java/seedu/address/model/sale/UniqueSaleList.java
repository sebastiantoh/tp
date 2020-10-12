package seedu.address.model.sale;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.sale.exceptions.DuplicateSaleException;
import seedu.address.model.sale.exceptions.SaleNotFoundException;

/**
 * A list of sales that enforces uniqueness between its elements and does not allow nulls.
 * A sale is considered unique by comparing using {@code Sale#isSameSale(Sale)}. As such, adding and updating of
 * sales uses Sale#isSameSale(Sale) for equality so as to ensure that the sale being added or updated is
 * unique in terms of identity in the UniqueSaleList. However, the removal of a sale uses Sale#equals(Object) so
 * as to ensure that the sale with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Sale#isSameSale(Sale)
 */
public class UniqueSaleList implements Iterable<Sale> {

    private final ObservableList<Sale> internalList = FXCollections.observableArrayList();
    private final ObservableList<Sale> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent sale as the given argument.
     */
    public boolean contains(Sale toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSale);
    }

    /**
     * Adds a sale to the list.
     * The sale must not already exist in the list.
     */
    public void add(Sale toAdd) throws DuplicateSaleException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateSaleException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the sale {@code target} in the list with {@code editedSale}.
     * {@code sale} must exist in the list.
     * The sale identity of {@code editedSale} must not be the same as another existing sale in the list.
     */
    public void setSale(Sale target, Sale editedSale) {
        requireAllNonNull(target, editedSale);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new SaleNotFoundException();
        }

        if (!target.isSameSale(editedSale) && contains(editedSale)) {
            throw new DuplicateSaleException();
        }

        internalList.set(index, editedSale);
    }

    /**
     * Removes the equivalent sale from the list.
     * The sale must exist in the list.
     */
    public void remove(Sale toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new SaleNotFoundException();
        }
    }

    public void setSales(UniqueSaleList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code sales}.
     * {@code sales} must not contain duplicate sales.
     */
    public void setSales(List<Sale> sales) {
        requireAllNonNull(sales);
        if (!salesAreUnique(sales)) {
            throw new DuplicateSaleException();
        }

        internalList.setAll(sales);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Sale> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Sale> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueSaleList // instanceof handles nulls
                && internalList.equals(((UniqueSaleList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code sales} contains only unique sales.
     */
    private boolean salesAreUnique(List<Sale> sales) {
        for (int i = 0; i < sales.size() - 1; i++) {
            for (int j = i + 1; j < sales.size(); j++) {
                if (sales.get(i).isSameSale(sales.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
