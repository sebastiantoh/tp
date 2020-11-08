package seedu.address.model.dataset;

import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.dataset.date.MonthAndYear;
import seedu.address.model.dataset.date.MonthlyCountData;

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
        //same object -> return true
        assertEquals(monthlyCountDataSet, monthlyCountDataSet);

        //same values -> return true
        assertEquals(monthlyCountDataSet, new DataSet<>(
                this.monthlyCountDataSet.getDataList()));

        //different list -> returns false
        assertNotEquals(monthlyCountDataSet, new DataSet<>(Collections.emptyList()));

        //different title -> returns false
        DataSet<MonthlyCountData> monthlyCountDataSetTest = new DataSet<>(Collections.emptyList());
        monthlyCountDataSetTest.setTitle("test 1");
        assertNotEquals(monthlyCountDataSetTest, monthlyCountDataSet);

    }
}
