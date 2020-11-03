package seedu.address.model.dataset.date;

import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

public class MonthlyCountDataTest {

    private final MonthlyCountData monthlyCountData =
            new MonthlyCountData(new MonthAndYear(AUGUST, Year.now()), 1);

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MonthlyCountData(null, 1));
    }

    @Test
    public void getCount_valid_success() {
        assertEquals(1, this.monthlyCountData.getCount());
    }

    @Test
    public void getKeyAsStr_valid_success() {
        String expectedString = String.format("%s %s", AUGUST, Year.now());
        MonthlyCountData monthlyCountData =
                new MonthlyCountData(new MonthAndYear(AUGUST, Year.now()), 1);

        assertEquals(expectedString, monthlyCountData.getKeyAsStr());
    }

    @Test
    public void equals_valid_success() {

        //same object -> return true
        assertEquals(monthlyCountData, monthlyCountData);

        //null -> return false
        assertNotEquals(monthlyCountData, null);

        //same values -> return true
        assertEquals(monthlyCountData, new MonthlyCountData(new MonthAndYear(AUGUST, Year.now()), 1));

        //different year -> return false
        assertNotEquals(monthlyCountData, new MonthlyCountData(new MonthAndYear(AUGUST, Year.of(2013)), 1));

        //different count -> return false
        assertNotEquals(monthlyCountData, new MonthlyCountData(new MonthAndYear(AUGUST, Year.now()), 2));

        //different month -> return false
        assertNotEquals(monthlyCountData, new MonthlyCountData(new MonthAndYear(Month.APRIL, Year.now()), 1));

    }
}
