package seedu.address.model.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.sale.TypicalSales.APPLE;
import static seedu.address.testutil.sale.TypicalSales.BALL;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.sale.exceptions.DuplicateSaleException;
import seedu.address.model.sale.exceptions.SaleNotFoundException;
import seedu.address.testutil.sale.SaleBuilder;

public class UniqueSaleListTest {

    private final UniqueSaleList uniqueSaleList = new UniqueSaleList();

    @Test
    public void contains_nullSale_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.contains(null));
    }

    @Test
    public void contains_saleNotInList_returnsFalse() {
        assertFalse(uniqueSaleList.contains(APPLE));
    }

    @Test
    public void contains_saleInList_returnsTrue() {
        uniqueSaleList.add(APPLE);
        assertTrue(uniqueSaleList.contains(APPLE));
    }

    @Test
    public void add_nullSale_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.add(null));
    }

    @Test
    public void add_duplicateSale_throwsDuplicateSaleException() {
        Month month = APPLE.getMonth();
        Year year = APPLE.getYear();
        List<Sale> oldMonthlySaleList = new ArrayList<>(uniqueSaleList.getMonthlySaleList(month, year));
        uniqueSaleList.add(APPLE);
        oldMonthlySaleList.add(APPLE);
        assertEquals(oldMonthlySaleList, uniqueSaleList.getMonthlySaleList(month, year));
        assertThrows(DuplicateSaleException.class, () -> uniqueSaleList.add(APPLE));
    }

    @Test
    public void setSale_nullTargetSale_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.setSale(null, APPLE));
    }

    @Test
    public void setSale_nullEditedSale_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.setSale(APPLE, null));
    }

    @Test
    public void setSale_targetSaleNotInList_throwsSaleNotFoundException() {
        assertThrows(SaleNotFoundException.class, () -> uniqueSaleList.setSale(APPLE, APPLE));
    }

    @Test
    public void setSale_editedSaleIsSameSale_success() {
        Month month = APPLE.getMonth();
        Year year = APPLE.getYear();
        uniqueSaleList.add(APPLE);
        List<Sale> oldMonthlySaleList = new ArrayList<>(uniqueSaleList.getMonthlySaleList(month, year));
        uniqueSaleList.setSale(APPLE, APPLE);
        UniqueSaleList expectedUniqueSaleList = new UniqueSaleList();
        expectedUniqueSaleList.add(APPLE);
        assertEquals(expectedUniqueSaleList, uniqueSaleList);
        assertEquals(oldMonthlySaleList, uniqueSaleList.getMonthlySaleList(month, year));
    }

    @Test
    public void setSale_editedSaleHasSameIdentity_success() {
        uniqueSaleList.add(APPLE);
        Sale editedApple = new SaleBuilder(APPLE).withUnitPrice(new BigDecimal("5.60")).withQuantity(20).build();
        uniqueSaleList.setSale(APPLE, editedApple);
        UniqueSaleList expectedUniqueSaleList = new UniqueSaleList();
        expectedUniqueSaleList.add(editedApple);
        assertEquals(expectedUniqueSaleList, uniqueSaleList);
    }

    @Test
    public void setSale_editedSaleHasDifferentIdentity_success() {
        uniqueSaleList.add(APPLE);
        uniqueSaleList.setSale(APPLE, BALL);
        UniqueSaleList expectedUniqueSaleList = new UniqueSaleList();
        expectedUniqueSaleList.add(BALL);
        assertEquals(expectedUniqueSaleList, uniqueSaleList);
    }

    @Test
    public void setSale_editedSaleHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueSaleList.add(APPLE);
        uniqueSaleList.add(BALL);
        assertThrows(DuplicateSaleException.class, () -> uniqueSaleList.setSale(APPLE, BALL));
    }

    @Test
    public void remove_nullSale_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.remove(null));
    }

    @Test
    public void remove_saleDoesNotExist_throwsSaleNotFoundException() {
        assertThrows(SaleNotFoundException.class, () -> uniqueSaleList.remove(APPLE));
    }

    @Test
    public void remove_existingSale_removesSale() {
        Month month = APPLE.getMonth();
        Year year = APPLE.getYear();
        uniqueSaleList.add(APPLE);
        uniqueSaleList.remove(APPLE);
        UniqueSaleList expectedUniqueSaleList = new UniqueSaleList();
        assertEquals(expectedUniqueSaleList, uniqueSaleList);
        assertEquals(Collections.emptyList(), uniqueSaleList.getMonthlySaleList(month, year));

    }

    @Test
    public void setSales_nullUniqueSaleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.setSales((UniqueSaleList) null));
    }

    @Test
    public void setSales_uniqueSaleList_replacesOwnListWithProvidedUniqueSaleList() {
        uniqueSaleList.add(APPLE);
        UniqueSaleList expectedUniqueSaleList = new UniqueSaleList();
        expectedUniqueSaleList.add(BALL);
        uniqueSaleList.setSales(expectedUniqueSaleList);
        assertEquals(expectedUniqueSaleList, uniqueSaleList);
    }

    @Test
    public void setSales_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleList.setSales((List<Sale>) null));
    }

    @Test
    public void setSales_list_replacesOwnListWithProvidedList() {
        uniqueSaleList.add(APPLE);
        List<Sale> saleList = Collections.singletonList(BALL);
        uniqueSaleList.setSales(saleList);
        UniqueSaleList expectedUniqueSaleList = new UniqueSaleList();
        expectedUniqueSaleList.add(BALL);
        assertEquals(expectedUniqueSaleList, uniqueSaleList);
    }

    @Test
    public void setSales_listWithDuplicateSales_throwsDuplicateSaleException() {
        List<Sale> listWithDuplicateSales = Arrays.asList(APPLE, APPLE);
        assertThrows(DuplicateSaleException.class, () -> uniqueSaleList.setSales(listWithDuplicateSales));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueSaleList.asUnmodifiableObservableList().remove(0));
    }
}
