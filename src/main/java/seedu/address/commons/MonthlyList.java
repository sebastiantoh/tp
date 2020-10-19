package seedu.address.commons;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/**
 * Stores a list of items of type T based on the key of < month, year >
 * @param <T>
 */
public class MonthlyList<T> {

    private final Map<Pair<Month, Year>, List<T>> monthlyList;

    public MonthlyList() {
        this.monthlyList = new HashMap<>();
    }

    /**
     * Adds {@code item} of type T based on the key of {@code month} and {@code year}.
     *
     * @param month a valid month number
     * @param year a valid year number
     * @param item item of type T
     */
    public void addItem(Month month, Year year, T item) {
        Pair<Month, Year> key = new Pair<>(month, year);
        if (this.monthlyList.containsKey(key)) {
            this.monthlyList.get(key).add(item);
        } else {
            this.monthlyList.put(key, new ArrayList<>(Collections.singleton(item)));
        }
    }

    /**
     * Removes {@code item} of type T based on the key of {@code month} and {@code year} if exists.
     *
     * @param month a valid month number
     * @param year a valid year number
     * @param item item of type T
     */
    public void removeItem(Month month, Year year, T item) {
        Pair<Month, Year> key = new Pair<>(month, year);
        if (this.monthlyList.containsKey(key)) {
            if (this.monthlyList.get(key).size() == 1) {
                this.monthlyList.remove(key);
            } else {
                this.monthlyList.get(key).remove(item);
            }
        }
    }

    /**
     * Gets the number of meetings started based on the key of {@code month} and {@code year}.
     * If the key of {@code month} and {@code year} does not exist, the number is 0.
     *
     * @param month a valid month number
     * @param year a valid year number
     * @return the number of meeting started in that month and year
     */
    public int getMonthCount(Month month, Year year) {
        Pair<Month, Year> key = new Pair<>(month, year);
        if (this.monthlyList.containsKey(key)) {
            return this.monthlyList.get(key).size();
        }
        return 0;
    }

    /**
     * Removes all entries in the monthlyList.
     */
    public void clear() {
        this.monthlyList.clear();
    }

    public List<Pair<Pair<Month, Year>, Integer>> getMultipleMonthCount(Month month, Year year, int numberOfMonths) {
        List<Pair<Pair<Month, Year>, Integer>> result = new ArrayList<>();

        Month currentMonth = month;
        Year currentYear = year;
        Pair<Month, Year> currentMonthAndYear = new Pair<>(currentMonth, currentYear);
        result.add(new Pair<>(currentMonthAndYear,
                this.monthlyList.getOrDefault(currentMonthAndYear, Collections.emptyList()).size()));
        for (int i = 1; i < numberOfMonths; i++) {
            Pair<Month, Year> previousMonthAndYear = getPreviousMonthAndYear(currentMonth, currentYear);
            currentMonth = previousMonthAndYear.getKey();
            currentYear = previousMonthAndYear.getValue();
            result.add(new Pair<>(previousMonthAndYear,
                    this.monthlyList.getOrDefault(previousMonthAndYear, Collections.emptyList()).size()));
        }
        Collections.reverse(result);
        return result;
    }

    private Pair<Month, Year> getPreviousMonthAndYear(Month month, Year year) {
        Year yearResult = year;
        int monthResult = month.getValue() - 1;
        if (monthResult <= 0) {
            monthResult += 12;
            yearResult = year.minusYears(1);
        }
        Month previousMonth = Month.of(monthResult);
        return new Pair<>(previousMonth, yearResult);
    }
}
