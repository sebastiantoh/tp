package seedu.address.commons.dataset;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.dataset.date.MonthAndYear;
import seedu.address.commons.dataset.date.MonthlyCountData;

class DataSetTest {
    private List<MonthlyCountData> monthlyCountDataList;

    private DataSet<MonthlyCountData> monthlyCountDataSet;

    @BeforeEach
    public void setUp() {
        monthlyCountDataList =
                Collections.singletonList(new MonthlyCountData(new MonthAndYear(AUGUST, Year.now()), 1));
        monthlyCountDataSet = new DataSet<>(monthlyCountDataList);
    }


    @Test
    public void getTestAndSetTest_valid_success() {
        String expectedTitle = "test 1";
        monthlyCountDataSet.setTitle(expectedTitle);
        assertEquals(expectedTitle, monthlyCountDataSet.getTitle());
    }

    @Test
    public void getMonthlyCountDataList_valid_success() {
        List<MonthlyCountData> expected = new ArrayList<>(this.monthlyCountDataList);
        assertEquals(expected, this.monthlyCountDataSet.getDataList());
    }

    @Test
    public void equals_valid_success() {
        assertEquals(monthlyCountDataSet, monthlyCountDataSet);

        DataSet<MonthlyCountData> monthlyCountDataSet1 = new DataSet<>(
                this.monthlyCountDataSet.getDataList());

        MonthlyCountData monthlyCountData2 = new MonthlyCountData(
                new MonthAndYear(APRIL, Year.now()), 1);
        DataSet<MonthlyCountData> monthlyCountDataSet2 = new DataSet<>(
                Collections.singletonList(monthlyCountData2));

        assertEquals(monthlyCountDataSet, monthlyCountDataSet1);

        assertNotEquals(monthlyCountDataSet, monthlyCountDataSet2);

        monthlyCountDataSet1.setTitle("test 1");
        assertNotEquals(monthlyCountDataSet1, monthlyCountDataSet);

    }
}
