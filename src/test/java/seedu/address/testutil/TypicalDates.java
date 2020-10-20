package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A utility class containing a list of {@code LocalDateTime} objects to be used in tests.
 */
public class TypicalDates {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm"));

    public static final LocalDateTime TYPICAL_DATE_1 = LocalDateTime.parse(VALID_DATE_1, FORMATTER);
    public static final LocalDateTime TYPICAL_DATE_2 = LocalDateTime.parse(VALID_DATE_2, FORMATTER);
    public static final LocalDateTime TYPICAL_DATE_3 = LocalDateTime.parse(VALID_DATE_3, FORMATTER);
}
