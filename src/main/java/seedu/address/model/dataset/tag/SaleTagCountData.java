package seedu.address.model.dataset.tag;

import java.util.Objects;

import seedu.address.model.dataset.Data;

/**
 * Store a data object for sale tag data statistic.
 */
public class SaleTagCountData extends Data<TagKey> {

    /**
     * Creates SaleTagCountData Object from the given {@code tagkey} and {@code count}.
     */
    public SaleTagCountData(TagKey tagkey, int count) {
        super(tagkey, count);
    }

    /**
     * Returns the String representation of TagKey.
     */
    @Override
    public String getKeyAsStr() {
        assert !Objects.isNull(key.getTag());

        return key.getTag().tagName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SaleTagCountData // instanceof handles nulls
                && this.key.equals(((SaleTagCountData) other).key)
                && this.count == ((SaleTagCountData) other).count);
    }
}
