package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.exceptions.InvalidTagListTypeException;

/**
 * A list of tags for contacts that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#isSameTag(Tag)}. As such, adding and updating of
 * tags uses Tag#isSameTag(Tag) for equality so as to ensure that the tag being added or updated is
 * unique in terms of identity in the UniqueSaleTagList. However, the removal of a tag uses Tag#equals(Object) so
 * as to ensure that the tag with exactly the same tag name will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tag#isSameTag(Tag)
 */
public class UniqueSaleTagList extends UniqueTagList {

    public UniqueSaleTagList() {
        super();
    }

    @Override
    public boolean belongsToContact() {
        return false;
    }

    @Override
    public boolean belongsToSale() {
        return true;
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code replacement} must be another UniqueSaleTagList.
     */
    @Override
    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        if (replacement.belongsToSale()) {
            internalList.setAll(replacement.internalList);
        } else {
            throw new InvalidTagListTypeException();
        }
    }

    /**
     * Sort the internal list in lexicographical order.
     */
    public void sort() {
        Comparator<Tag> comparator = Comparator.naturalOrder();
        FXCollections.sort(internalList, comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueSaleTagList // instanceof handles nulls
                && internalList.equals(((UniqueSaleTagList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
