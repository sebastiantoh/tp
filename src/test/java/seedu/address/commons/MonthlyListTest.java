package seedu.address.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.Meeting;

public class MonthlyListTest {

    private final MonthlyList<Meeting> meetingMonthlyList = new MonthlyList<>();

    @Test
    public void addItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyList.getItemCount(month, year));
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        assertEquals(2, this.meetingMonthlyList.getItemCount(month, year));
    }

    @Test
    public void removeItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.removeItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyList.getItemCount(month, year));
        this.meetingMonthlyList.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyList.getItemCount(month, year));
        this.meetingMonthlyList.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyList.getItemCount(month, year));
    }

    @Test
    public void clear_noInput_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        assertEquals(2, this.meetingMonthlyList.getItemCount(month, year));
        this.meetingMonthlyList.clear();
        assertEquals(0, this.meetingMonthlyList.getItemCount(month, year));
    }
}
