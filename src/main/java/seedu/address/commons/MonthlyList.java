package seedu.address.commons;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores a list of items of type T based on the key of < month, year >
 */
public class MonthlyList<T> {

    private final Map<MonthAndYear, List<T>> monthlyList;

    public MonthlyList() {
        this.monthlyList = new HashMap<>();
    }

    /**
     * Adds {@code item} of type T to an item list
     * based on the key of {@code month} and {@code year}.
     *
     * @param month a valid month number
     * @param year a valid year number
     * @param item item of type T
     */
    public void addItem(Month month, Year year, T item) {
        MonthAndYear key = new MonthAndYear(month, year);
        if (this.monthlyList.containsKey(key)) {
            this.monthlyList.get(key).add(item);
        } else {
            this.monthlyList.put(key, new ArrayList<>(Collections.singleton(item)));
        }
    }

    /**
     * Removes {@code item} of type T from an item list
     * based on the key of {@code month} and {@code year} if exists.
     *
     * @param month a valid month number
     * @param year a valid year number
     * @param item item of type T
     */
    public void removeItem(Month month, Year year, T item) {
        MonthAndYear key = new MonthAndYear(month, year);
        if (this.monthlyList.containsKey(key)) {
            if (this.monthlyList.get(key).size() == 1) {
                this.monthlyList.remove(key);
            } else {
                this.monthlyList.get(key).remove(item);
            }
        }
    }

    /**
     * Gets the number of items in an item list
     * based on the key of {@code month} and {@code year}.
     * If the key of {@code month} and {@code year} does not exist, the number is 0.
     *
     * @param month a valid month number
     * @param year a valid year number
     * @return the number of items in that month and year
     */
    public int getItemCount(Month month, Year year) {
        MonthAndYear key = new MonthAndYear(month, year);
        if (this.monthlyList.containsKey(key)) {
            return this.monthlyList.get(key).size();
        }
        return 0;
    }

    public List<T> getItems(Month month, Year year) {
        MonthAndYear key = new MonthAndYear(month, year);
        return this.monthlyList.getOrDefault(key, Collections.emptyList());
    }

    /**
     * Removes all entries in the monthlyList.
     */
    public void clear() {
        this.monthlyList.clear();
    }

    /**
     * Gets the item counts in the item list for {@code month}, {@code year}
     * and the previous @{numberOfMonths} - 1 months.
     * @param month valid month
     * @param year  valid year
     * @param numberOfMonths  non-negative integer
     * @return list of MonthlyCountData objects, ordered by non-decreasing year and month
     */
    public List<MonthlyCountData> getMultipleMonthCount(Month month, Year year, int numberOfMonths) {
        List<MonthlyCountData> result = new ArrayList<>();

        MonthAndYear currentMonthAndYear = new MonthAndYear(month, year);
        result.add(new MonthlyCountData(currentMonthAndYear,
                this.monthlyList.getOrDefault(currentMonthAndYear, Collections.emptyList()).size()));

        for (int i = 1; i < numberOfMonths; i++) {
            MonthAndYear previousMonthAndYear = getPreviousMonthAndYear(month, year);
            result.add(new MonthlyCountData(previousMonthAndYear,
                    this.monthlyList.getOrDefault(previousMonthAndYear, Collections.emptyList()).size()));
            month = previousMonthAndYear.getMonth();
            year = previousMonthAndYear.getYear();
        }

        Collections.reverse(result);
        return result;
    }

    /**
     * Gets the month and year for
     * one month before {@code month} and {@code year}.
     * @param month valid month
     * @param year valid year
     * @return MonthAndYear object that is one month before the {@code month} and {@code year}
     */
    private MonthAndYear getPreviousMonthAndYear(Month month, Year year) {
        Year yearResult = year;
        int monthResult = month.getValue() - 1;
        if (monthResult <= 0) {
            monthResult += 12;
            yearResult = year.minusYears(1);
        }
        Month previousMonth = Month.of(monthResult);
        return new MonthAndYear(previousMonth, yearResult);
    }
}
