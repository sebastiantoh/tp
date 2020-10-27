package seedu.address.commons.statistic;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.statistics.MonthlyCountData;
import seedu.address.commons.statistics.MonthlyCountDataSet;
import seedu.address.model.MonthAndYear;

class MonthlyCountDataSetTest {

    private final List<MonthlyCountData> monthlyCountDataList =
            Collections.singletonList(new MonthlyCountData(new MonthAndYear(AUGUST, Year.now()), 1));

    private final MonthlyCountDataSet monthlyCountDataSet = new MonthlyCountDataSet(monthlyCountDataList);

    @Test
    public void getTestAndSetTest_valid_success() {
        String expectedTitle = "test 1";
        monthlyCountDataSet.setTitle(expectedTitle);
        assertEquals(expectedTitle, monthlyCountDataSet.getTitle());
    }

    @Test
    public void getMonthlyCountDataList_valid_success() {
        List<MonthlyCountData> expected = new ArrayList<>(this.monthlyCountDataList);
        assertEquals(expected, this.monthlyCountDataSet.getMonthlyCountDataList());
    }

    @Test
    public void equals_valid_success() {
        assertEquals(monthlyCountDataSet, monthlyCountDataSet);

        MonthlyCountDataSet monthlyCountDataSet1 = new MonthlyCountDataSet(
                this.monthlyCountDataSet.getMonthlyCountDataList());

        MonthlyCountData monthlyCountData2 = new MonthlyCountData(
                new MonthAndYear(APRIL, Year.now()), 1);
        MonthlyCountDataSet monthlyCountDataSet2 = new MonthlyCountDataSet(
                Collections.singletonList(monthlyCountData2));

        assertEquals(monthlyCountDataSet, monthlyCountDataSet1);

        assertNotEquals(monthlyCountDataSet, monthlyCountDataSet2);

        monthlyCountDataSet1.setTitle("test 1");
        assertNotEquals(monthlyCountDataSet1, monthlyCountDataSet);

    }
}
