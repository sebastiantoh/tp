package seedu.address.commons;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class MonthlyList<T> {

    private final Map<Pair<Month, Integer>, List<T>> monthlyList;

    public MonthlyList() {
        this.monthlyList = new HashMap<>();
    }


    public void addItem(Month month, int year, T item) {
        Pair<Month, Integer> key = new Pair<>(month, year);
        if (this.monthlyList.containsKey(key)) {
            this.monthlyList.get(key).add(item);
        } else {
            this.monthlyList.put(key, new ArrayList<>(Collections.singleton(item)));
        }
        print();
    }

    public void removeItem(Month month, int year, T item) {
        Pair<Month, Integer> key = new Pair<>(month, year);
        if (this.monthlyList.containsKey(key)) {
            this.monthlyList.get(key).remove(item);
        }
        print();
    }

    public void print() {
        for (Pair<Month, Integer>  p : this.monthlyList.keySet()) {
            for (T t : this.monthlyList.get(p)) {
                System.out.println(t);
            }
        }
    }

    public int getMonthCount(Month month, Year year) {
        Pair<Month, Integer> key = new Pair<>(month, year.getValue());
        if (this.monthlyList.containsKey(key)) {
            return this.monthlyList.get(key).size();
        }
        return 0;
    }

    public void clear() {
        this.monthlyList.clear();
    }
}
