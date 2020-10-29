package seedu.address.model.dataset.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_DANIEL;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_ELLE;
import static seedu.address.testutil.meeting.TypicalMeetings.LUNCH_FIONA;
import static seedu.address.testutil.meeting.TypicalMeetings.MEET_ALICE;
import static seedu.address.testutil.sale.TypicalSales.DRUMS;
import static seedu.address.testutil.sale.TypicalSales.GUITAR;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.dataset.DataSet;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.sale.Sale;

public class MonthlyListMapTest {

    private MonthlyListMap<Meeting> meetingMonthlyListMap;

    @BeforeEach
    public void init() {
        this.meetingMonthlyListMap = new MonthlyListMap<>();
    }

    @Test
    public void addItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyListMap.getItemCount(month, year));
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        assertEquals(2, this.meetingMonthlyListMap.getItemCount(month, year));
    }

    @Test
    public void removeItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.removeItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyListMap.getItemCount(month, year));
        this.meetingMonthlyListMap.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyListMap.getItemCount(month, year));
        this.meetingMonthlyListMap.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyListMap.getItemCount(month, year));
    }

    @Test
    public void clear_noInput_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        assertEquals(2, this.meetingMonthlyListMap.getItemCount(month, year));
        this.meetingMonthlyListMap.clear();
        assertEquals(0, this.meetingMonthlyListMap.getItemCount(month, year));
    }

    @Test
    public void getMultipleMonthCount_valid_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);

        Month month1 = LUNCH_FIONA.getStartDate().getMonth();
        Year year1 = Year.of(LUNCH_FIONA.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month1, year1, LUNCH_FIONA);
        this.meetingMonthlyListMap.addItem(month1, year1, LUNCH_FIONA);

        DataSet<MonthlyCountData> actual = this.meetingMonthlyListMap
                .getMultipleMonthCount(month, year, 3);
        DataSet<MonthlyCountData> expected = new DataSet<MonthlyCountData>(Arrays.asList(
                new MonthlyCountData(new MonthAndYear(month1, year1), 2),
                new MonthlyCountData(new MonthAndYear(month.minus(1), year), 0),
                new MonthlyCountData(new MonthAndYear(month, year), 4)));
        assertEquals(expected, actual);
    }

    @Test
    public void getMultipleMonthCount_validButMonthStartFromFeb_success() {
        Month month = LUNCH_DANIEL.getStartDate().getMonth();
        Year year = Year.of(LUNCH_DANIEL.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month, year, LUNCH_DANIEL);
        this.meetingMonthlyListMap.addItem(month, year, LUNCH_DANIEL);

        Month month1 = LUNCH_ELLE.getStartDate().getMonth();
        Year year1 = Year.of(LUNCH_ELLE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month1, year1, LUNCH_ELLE);

        DataSet<MonthlyCountData> actual = this.meetingMonthlyListMap
                .getMultipleMonthCount(month1, year1, 6);
        DataSet<MonthlyCountData> expected = new DataSet<MonthlyCountData>(Arrays.asList(
                new MonthlyCountData(new MonthAndYear(month.minus(4), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month.minus(3), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month.minus(2), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month.minus(1), year.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(month, year), 2),
                new MonthlyCountData(new MonthAndYear(month1, year1), 1)));
        assertEquals(expected, actual);
    }

    @Test
    public void getItems_validInputs_success() {
        MonthlyListMap<Sale> saleMonthlyListMap = new MonthlyListMap<>();

        Month month = GUITAR.getDatetimeOfPurchase().getMonth();
        Year year = Year.of(GUITAR.getDatetimeOfPurchase().getYear());
        saleMonthlyListMap.addItem(month, year, GUITAR);
        saleMonthlyListMap.addItem(month, year, DRUMS);

        List<Sale> expectedSaleList = Arrays.asList(GUITAR, DRUMS);
        assertEquals(expectedSaleList, saleMonthlyListMap.getItems(month, year));
    }

}
