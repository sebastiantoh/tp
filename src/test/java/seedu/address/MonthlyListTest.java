package seedu.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

import seedu.address.commons.MonthlyList;
import seedu.address.model.meeting.Meeting;

public class MonthlyListTest {

    private final MonthlyList<Meeting> meetingMonthlyList = new MonthlyList<>();

    @Test
    public void addItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        int year = MEET_ALICE.getStartDate().getYear();
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyList.getMonthCount(month, Year.of(year)));
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        assertEquals(2, this.meetingMonthlyList.getMonthCount(month, Year.of(year)));
    }

    @Test
    public void removeItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        int year = MEET_ALICE.getStartDate().getYear();
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyList.getMonthCount(month, Year.of(year)));
        this.meetingMonthlyList.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyList.getMonthCount(month, Year.of(year)));
    }
}
