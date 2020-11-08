package seedu.address.model.dataset.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

public class MonthAndYearTest {

    private final MonthAndYear monthAndYear =
            new MonthAndYear(Month.AUGUST, Year.of(2010));

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MonthAndYear(null, null));
        assertThrows(NullPointerException.class, () -> new MonthAndYear(Month.APRIL, null));
        assertThrows(NullPointerException.class, () -> new MonthAndYear(null, Year.now()));
    }

    @Test
    public void getMonth_valid_success() {
        assertEquals(Month.AUGUST, this.monthAndYear.getMonth());
    }

    @Test
    public void getYear_valid_success() {
        assertEquals(Year.of(2010), this.monthAndYear.getYear());
    }

    @Test
    public void equals_valid_success() {
        //same object -> return true
        assertEquals(monthAndYear, monthAndYear);

        //same values -> return true
        assertEquals(monthAndYear, new MonthAndYear(Month.AUGUST, Year.of(2010)));

        //null -> return false
        assertNotEquals(monthAndYear, null);

        //different month -> return false
        assertNotEquals(monthAndYear, new MonthAndYear(Month.APRIL, Year.of(2010)));

        //different year -> return false
        assertNotEquals(monthAndYear, new MonthAndYear(Month.AUGUST, Year.of(1990)));
    }
}
