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

        //Add item to empty list -> list size = 1
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyListMap.getItemCount(month, year));

        //Add item to list of size 1 -> list size = 2
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        assertEquals(2, this.meetingMonthlyListMap.getItemCount(month, year));
    }

    @Test
    public void removeItem_validInputs_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());

        //Remove existing item in a list of size 2 -> list size = 1
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);
        this.meetingMonthlyListMap.removeItem(month, year, MEET_ALICE);
        assertEquals(1, this.meetingMonthlyListMap.getItemCount(month, year));

        //Remove existing item in a list of size 1 -> list size = 0
        this.meetingMonthlyListMap.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyListMap.getItemCount(month, year));

        //Remove existing item in a list of size 0 -> list size = 0
        this.meetingMonthlyListMap.removeItem(month, year, MEET_ALICE);
        assertEquals(0, this.meetingMonthlyListMap.getItemCount(month, year));
    }

    @Test
    public void clear_noInput_success() {
        Month month = MEET_ALICE.getStartDate().getMonth();
        Year year = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(month, year, MEET_ALICE);

        this.meetingMonthlyListMap.clear();
        assertEquals(0, this.meetingMonthlyListMap.getItemCount(month, year));
    }

    @Test
    public void getMultipleMonthCount_valid_success() {

        //Fill list accessed by key <aliceMonth, aliceYear> -> list size = 3
        Month aliceMonth = MEET_ALICE.getStartDate().getMonth();
        Year aliceYear = Year.of(MEET_ALICE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(aliceMonth, aliceYear, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(aliceMonth, aliceYear, MEET_ALICE);
        this.meetingMonthlyListMap.addItem(aliceMonth, aliceYear, MEET_ALICE);

        //Fill list accessed by key <fionaMonth, fionaYear> -> list size 2
        Month fionaMonth = LUNCH_FIONA.getStartDate().getMonth();
        Year fionaYear = Year.of(LUNCH_FIONA.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(fionaMonth, fionaYear, LUNCH_FIONA);
        this.meetingMonthlyListMap.addItem(fionaMonth, fionaYear, LUNCH_FIONA);

        DataSet<MonthlyCountData> actual = this.meetingMonthlyListMap
                .getMultipleMonthCount(aliceMonth, aliceYear, 3);

        DataSet<MonthlyCountData> expected = new DataSet<MonthlyCountData>(Arrays.asList(
                new MonthlyCountData(new MonthAndYear(fionaMonth, fionaYear), 2),
                new MonthlyCountData(new MonthAndYear(aliceMonth.minus(1), aliceYear), 0),
                new MonthlyCountData(new MonthAndYear(aliceMonth, aliceYear), 3)));

        assertEquals(expected, actual);
    }

    @Test
    public void getMultipleMonthCount_validButMonthStartFromFeb_success() {

        //Fill list accessed by key <danielMonth, danielYear> -> list size 2
        Month danielMonth = LUNCH_DANIEL.getStartDate().getMonth();
        Year danielYear = Year.of(LUNCH_DANIEL.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(danielMonth, danielYear, LUNCH_DANIEL);
        this.meetingMonthlyListMap.addItem(danielMonth, danielYear, LUNCH_DANIEL);

        //Fill list accessed by key <elleMonth, elleYear> -> list size 2
        Month elleMonth = LUNCH_ELLE.getStartDate().getMonth();
        Year elleYear = Year.of(LUNCH_ELLE.getStartDate().getYear());
        this.meetingMonthlyListMap.addItem(elleMonth, elleYear, LUNCH_ELLE);

        DataSet<MonthlyCountData> actual = this.meetingMonthlyListMap
                .getMultipleMonthCount(elleMonth, elleYear, 6);

        DataSet<MonthlyCountData> expected = new DataSet<MonthlyCountData>(Arrays.asList(
                new MonthlyCountData(new MonthAndYear(danielMonth.minus(4), danielYear.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(danielMonth.minus(3), danielYear.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(danielMonth.minus(2), danielYear.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(danielMonth.minus(1), danielYear.minusYears(1)), 0),
                new MonthlyCountData(new MonthAndYear(danielMonth, danielYear), 2),
                new MonthlyCountData(new MonthAndYear(elleMonth, elleYear), 1)));

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
