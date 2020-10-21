package seedu.address.testutil.sale;

import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BENSON;
import static seedu.address.testutil.person.TypicalPersons.CARL;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.model.sale.Sale;

/**
 * A utility class containing a list of {@code Sale} objects to be used in tests.
 */
public class TypicalSales {

    public static final Sale APPLE = new SaleBuilder().withItemName("Apple")
            .withBuyerId(BENSON.getId()).withQuantity("10")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 10, 30, 15, 0))
            .withUnitPrice(new BigDecimal("3.5")).withTags("fruits").build();
    public static final Sale BALL = new SaleBuilder().withItemName("Ball").withBuyerId(ALICE.getId()).withQuantity("1")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 9, 22, 12, 40))
            .withUnitPrice(new BigDecimal("0.8")).withTags("sports").build();
    public static final Sale CAMERA = new SaleBuilder().withItemName("Camera").withBuyerId(CARL.getId())
            .withQuantity("2")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 11, 1, 9, 5))
            .withUnitPrice(new BigDecimal("1000.5")).withTags("electronics").build();
    public static final Sale GUITAR = new SaleBuilder().withItemName("Fender guitar").withBuyerId(BENSON.getId())
            .withQuantity("5")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 11, 1, 9, 5))
            .withUnitPrice(new BigDecimal("7000.0")).withTags("electronics").build();
    public static final Sale DRUMS = new SaleBuilder().withItemName("Yamaha Drum set").withBuyerId(BENSON.getId())
            .withQuantity("1")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 11, 1, 9, 5))
            .withUnitPrice(new BigDecimal("20000.0")).withTags("electronics").build();

    private TypicalSales() {} // prevents instantiation

    public static List<Sale> getTypicalSales() {
        return new ArrayList<>(Arrays.asList(APPLE, BALL, CAMERA, GUITAR, DRUMS));
    }

    public static List<Sale> getTypicalSalesInReverse() {
        List<Sale> typicalSales = new ArrayList<>(Arrays.asList(APPLE, BALL, CAMERA, GUITAR, DRUMS));
        Collections.reverse(typicalSales);
        return typicalSales;
    }
}
