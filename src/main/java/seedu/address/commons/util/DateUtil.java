package seedu.address.commons.util;

import java.time.LocalDateTime;

/**
 * A container for LocalDateTime specific utility functions
 */
public class DateUtil {
    /**
     * Returns true if {@code date1} and @{code date2} refers to the same day without regards to the time.
     */
    public static boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {
        return LocalDateTime.of(date1.getYear(), date1.getMonthValue(), date1.getDayOfMonth(), 0, 0).equals(
                LocalDateTime.of(date2.getYear(), date2.getMonthValue(), date2.getDayOfMonth(), 0, 0)
        );
    }

}
