package seedu.address.model.dataset;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Data stored in DataSet for statistics.
 */
public class Data<T> {

    protected final T key;

    protected final int count;

    /**
     * Creates Data Object from the given {@code key} and {@code count}.
     */
    public Data(T key, int count) {
        requireNonNull(key);

        this.key = key;
        this.count = count;
    }

    /**
     * Returns the String representation of Key.
     */
    public String getKeyAsStr() {
        return key.toString();
    }

    /**
     * Returns the number of counts related to key.
     */
    public int getCount() {
        assert count >= 0;

        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Data<?> data = (Data<?>) o;
        return count == data.count
                && key.equals(data.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, count);
    }
}
