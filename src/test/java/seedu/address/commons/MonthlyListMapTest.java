package seedu.address.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_DANIEL;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_ELLE;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_FIONA;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.Meeting;

public class MonthlyListTest {

    private MonthlyList<Meeting> meetingMonthlyList;

    @BeforeEach
    public void init() {
        this.meetingMonthlyList = new MonthlyList<>();
    }

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

    @Test
    public void getMultipleMonthCount_valid_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyList.addItem(month, year, MEET_ALICE);

        Month month1 = LUNCH_FIONA.getStartDate().getMonth();
        Year year1 = Year.of(LUNCH_FIONA.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month1, year1, LUNCH_FIONA);
        this.meetingMonthlyList.addItem(month1, year1, LUNCH_FIONA);

        List<MonthlyCountData> actual = this.meetingMonthlyList
                .getMultipleMonthCount(month, year, 3);
        List<MonthlyCountData> expected = Arrays.asList(
                new MonthlyCountData(new MonthAndYear(month1, year1), 2),
                new MonthlyCountData(new MonthAndYear(month.minus(1), year), 0),
                new MonthlyCountData(new MonthAndYear(month, year), 4));
        assertEquals(expected, actual);
    }

    @Test
    public void getMultipleMonthCount_validButMonthStartFromFeb_success() {
        Month month = LUNCH_DANIEL.getStartDate().getMonth();
        Year year = Year.of(LUNCH_DANIEL.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month, year, LUNCH_DANIEL);
        this.meetingMonthlyList.addItem(month, year, LUNCH_DANIEL);

        Month month1 = LUNCH_ELLE.getStartDate().getMonth();
        Year year1 = Year.of(LUNCH_ELLE.getStartDate().getYear());
        this.meetingMonthlyList.addItem(month1, year1, LUNCH_ELLE);

        List<MonthlyCountData> actual = this.meetingMonthlyList
                .getMultipleMonthCount(month1, year1, 6);
        List<MonthlyCountData> expected = Arrays.asList(
                new MonthlyCountData(new MonthAndYear(month.minus(4), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month.minus(3), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month.minus(2), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month.minus(1), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month, year), 2),
                new MonthlyCountData(new MonthAndYear(month1, year1), 1));
        assertEquals(expected, actual);
    }

}
