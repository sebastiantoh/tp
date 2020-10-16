package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.isSameDay;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateUtilTest {
    @Test
    public void isSameDay_sameObjects_returnTrue() {
        LocalDateTime date = LocalDateTime.of(2020, 10, 10, 10, 10, 10);
        assertTrue(isSameDay(date, date));
    }

    @Test
    public void isSameDay_sameDayDiffHour_returnTrue() {
        LocalDateTime date1 = LocalDateTime.of(2020, 10, 10, 10, 10, 10);
        LocalDateTime date2 = LocalDateTime.of(2020, 10, 10, 0, 10, 10);
        assertTrue(isSameDay(date1, date2));
    }

    @Test
    public void isSameDay_sameDayDiffYear_returnFalse() {
        LocalDateTime date1 = LocalDateTime.of(2020, 10, 10, 10, 10, 10);
        LocalDateTime date2 = LocalDateTime.of(2019, 10, 10, 10, 10, 10);
        assertFalse(isSameDay(date1, date2));
    }
}
