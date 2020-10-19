package seedu.address.commons;

/**
 * Store a data object for monthly count data statistic.
 */
public class MonthlyCountData {

    private final MonthAndYear monthAndYear;

    private final int count;

    public MonthlyCountData(MonthAndYear monthAndYear, int count) {
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
}
