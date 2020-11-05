package seedu.address.model.dataset.date;

import static java.util.Objects.requireNonNull;

import java.time.Month;
import java.time.Year;
import java.util.Objects;

//@@author AaronnSeah
/**
 * Store a month and year.
 */
public class MonthAndYear {

    private Month month;
    private Year year;

    /**
     * Creates a MonthAndYear Object from the given {@code month} and {@code year}
     */
    public MonthAndYear(Month month, Year year) {
        requireNonNull(month);
        requireNonNull(year);

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
