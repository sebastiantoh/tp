package seedu.address.testutil.sale;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.sale.Sale;

/**
 * A utility class containing a list of {@code Sale} objects to be used in tests.
 */
public class TypicalSales {

    public static final Sale APPLE = new SaleBuilder().withItemName("Apple").withQuantity("10")
            .withUnitPrice(3, 50).build();
    public static final Sale BALL = new SaleBuilder().withItemName("Ball").withQuantity("1")
            .withUnitPrice(0, 80).build();
    public static final Sale CAMERA = new SaleBuilder().withItemName("Camera").withQuantity("2")
            .withUnitPrice(1000, 50).build();

    private TypicalSales() {} // prevents instantiation

    public static List<Sale> getTypicalSales() {
        return new ArrayList<>(Arrays.asList(APPLE, BALL, CAMERA));
    }
}
