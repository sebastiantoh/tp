package seedu.address.model.dataset.date;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.dataset.DataSet;

//@@author AaronnSeah
/**
 * Stores a list of items of type T based on the key of < month, year >.
 */
public class MonthlyListMap<T> {

    private final Map<MonthAndYear, List<T>> monthlyListMap;

    public MonthlyListMap() {
        this.monthlyListMap = new HashMap<>();
    }

    /**
     * Adds {@code item} of type T to an item list
     * based on the key of {@code month} and {@code year}.
     *
     * @param month a valid month number.
     * @param year a valid year number.
     * @param item item of type T.
     */
    public void addItem(Month month, Year year, T item) {
        MonthAndYear key = new MonthAndYear(month, year);
        if (this.monthlyListMap.containsKey(key)) {
            this.monthlyListMap.get(key).add(item);
        } else {
            this.monthlyListMap.put(key, new ArrayList<>(Collections.singleton(item)));
        }
    }

    /**
     * Removes {@code item} of type T from an item list
     * based on the key of {@code month} and {@code year} if exists.
     *
     * @param month a valid month number.
     * @param year a valid year number.
     * @param item item of type T.
     */
    public void removeItem(Month month, Year year, T item) {
        MonthAndYear key = new MonthAndYear(month, year);
        if (this.monthlyListMap.containsKey(key)) {
            if (this.monthlyListMap.get(key).size() == 1) {
                this.monthlyListMap.remove(key);
            } else {
                this.monthlyListMap.get(key).remove(item);
            }
        }
    }

    /**
     * Gets the monthly item list for {@code month} and {@code year}.
     * @param month valid month.
     * @param year  valid year.
     * @return list of items in its natural order.
     */
    public List<T> getItems(Month month, Year year) {
        MonthAndYear key = new MonthAndYear(month, year);
        return this.monthlyListMap.getOrDefault(key, Collections.emptyList());
    }

    /**
     * Gets the number of items in an item list
     * based on the key of {@code month} and {@code year}.
     * If the key of {@code month} and {@code year} does not exist, the number is 0.
     *
     * @param month a valid month number.
     * @param year a valid year number.
     * @return the number of items in that month and year.
     */
    public int getItemCount(Month month, Year year) {
        return this.getItems(month, year).size();
    }


    /**
     * Removes all entries in the monthlyListMap.
     */
    public void clear() {
        this.monthlyListMap.clear();
    }

    /**
     * Gets the item counts in the item list for {@code month}, {@code year}
     * and the previous @{numberOfMonths} - 1 months.
     * @param month valid month.
     * @param year  valid year.
     * @param numberOfMonths  non-negative integer.
     * @return MonthlyCountDataSet object, where the data is ordered by non-decreasing year and month.
     */
    public DataSet<MonthlyCountData> getMultipleMonthCount(Month month, Year year, int numberOfMonths) {
        List<MonthlyCountData> result = new ArrayList<>();

        MonthAndYear currentMonthAndYear = new MonthAndYear(month, year);
        result.add(new MonthlyCountData(currentMonthAndYear, getItemCount(month, year)));

        for (int i = 1; i < numberOfMonths; i++) {
            MonthAndYear previousMonthAndYear = getPreviousMonthAndYear(month, year);
            month = previousMonthAndYear.getMonth();
            year = previousMonthAndYear.getYear();
            result.add(new MonthlyCountData(previousMonthAndYear, getItemCount(month, year)));
        }

        Collections.reverse(result);
        return new DataSet<>(result);
    }

    /**
     * Gets the month and year for
     * one month before {@code month} and {@code year}.
     * @param month valid month.
     * @param year valid year.
     * @return MonthAndYear object that is one month before the {@code month} and {@code year}.
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
