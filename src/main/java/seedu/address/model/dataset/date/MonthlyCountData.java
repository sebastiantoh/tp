package seedu.address.model.dataset.date;

import java.util.Objects;

import seedu.address.model.dataset.Data;

//@@author AaronnSeah
/**
 * Store a data object for monthly count data statistic.
 */
public class MonthlyCountData extends Data<MonthAndYear> {

    /**
     * Creates MonthlyCountData Object from the given {@code monthAndYear} and {@code count}.
     */
    public MonthlyCountData(MonthAndYear monthAndYear, int count) {
        super(monthAndYear, count);
    }

    /**
     * Returns the String representation of MonthAndYear.
     */
    @Override
    public String getKeyAsStr() {
        assert !Objects.isNull(key.getMonth())
                && !Objects.isNull(key.getYear());

        return String.format("%s %s", key.getMonth(), key.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MonthlyCountData // instanceof handles nulls
                && this.key.equals(((MonthlyCountData) other).key)
                && this.count == ((MonthlyCountData) other).count);
    }
}
