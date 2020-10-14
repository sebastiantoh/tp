package seedu.address.testutil.sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.sale.Sale;

/**
 * A utility class containing a list of {@code Sale} objects to be used in tests.
 */
public class TypicalSales {

    public static final Sale APPLE = new SaleBuilder().withItemName("Apple").withQuantity("10")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 10, 30, 15, 0))
            .withUnitPrice(3, 50).withTags("fruits").build();
    public static final Sale BALL = new SaleBuilder().withItemName("Ball").withQuantity("1")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 9, 22, 12, 40))
            .withUnitPrice(0, 80).withTags("sports").build();
    public static final Sale CAMERA = new SaleBuilder().withItemName("Camera").withQuantity("2")
            .withDatetimeOfPurchase(LocalDateTime.of(2020, 11, 1, 9, 5))
            .withUnitPrice(1000, 50).withTags("electronics").build();

    private TypicalSales() {} // prevents instantiation

    public static List<Sale> getTypicalSales() {
        return new ArrayList<>(Arrays.asList(APPLE, BALL, CAMERA));
    }
}
