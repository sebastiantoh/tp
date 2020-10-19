package seedu.address.commons;

import static java.util.Objects.requireNonNull;

/**
 * Store a data object for monthly count data statistic.
 */
public class MonthlyCountData {

    private final MonthAndYear monthAndYear;

    private final int count;

    /**
     * Creates MonthlyCountData Object from the given {@code monthAndYear} and {@code count}.
     */
    public MonthlyCountData(MonthAndYear monthAndYear, int count) {
        requireNonNull(monthAndYear);

        this.monthAndYear = monthAndYear;
        this.count = count;
    }

    /**
     * Returns the String representation of MonthAndYear.
     */
    public String getMonthAndYearAsStr() {
        return String.format("%s %s", monthAndYear.getMonth(), monthAndYear.getYear());
    }

    /**
     * Returns the number of counts related to MonthAndYear object.
     */
    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MonthlyCountData // instanceof handles nulls
                && this.monthAndYear.equals(((MonthlyCountData) other).monthAndYear)
                && this.count == ((MonthlyCountData) other).count);
    }
}
