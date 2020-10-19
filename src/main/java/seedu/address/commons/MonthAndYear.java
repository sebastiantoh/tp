package seedu.address.commons;

import java.time.Month;
import java.time.Year;
import java.util.Objects;

/**
 * Store a month and year.
 */
public class MonthAndYear {

    private Month month;
    private Year year;

    public MonthAndYear(Month month, Year year) {
        this.month = month;
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.month, this.year);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MonthAndYear // instanceof handles nulls
                && this.month.equals(((MonthAndYear) other).month)
                && this.year.equals(((MonthAndYear) other).year));
    }
}
