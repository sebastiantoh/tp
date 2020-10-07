package seedu.address.testutil.sale;

import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UnitPrice;

/**
 * A utility class to help with building Sale objects.
 */
public class SaleBuilder {

    public static final String DEFAULT_ITEM_NAME = "Pizza";
    public static final String DEFAULT_QUANTITY = "50";
    public static final Integer DEFAULT_UNIT_PRICE_DOLLARS = 20;
    public static final Integer DEFAULT_UNIT_PRICE_CENTS = 50;

    private ItemName name;
    private Quantity quantity;
    private UnitPrice unitPrice;

    /**
     * Creates a {@code SaleBuilder} with the default details.
     */
    public SaleBuilder() {
        name = new ItemName(DEFAULT_ITEM_NAME);
        quantity = new Quantity(DEFAULT_QUANTITY);
        unitPrice = new UnitPrice(DEFAULT_UNIT_PRICE_DOLLARS, DEFAULT_UNIT_PRICE_CENTS);
    }

    /**
     * Initializes the SaleBuilder with the data of {@code saleToCopy}.
     */
    public SaleBuilder(Sale saleToCopy) {
        name = saleToCopy.getItemName();
        quantity = saleToCopy.getQuantity();
        unitPrice = saleToCopy.getUnitPrice();
    }

    /**
     * Sets the {@code ItemName} of the {@code Sale} that we are building.
     */
    public SaleBuilder withItemName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Sale} that we are building.
     */
    public SaleBuilder withQuantity(String quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code UnitPrice} of the {@code Sale} that we are building.
     */
    public SaleBuilder withUnitPrice(Integer dollars, Integer cents) {
        this.unitPrice = new UnitPrice(dollars, cents);
        return this;
    }

    public Sale build() {
        return new Sale(name, quantity, unitPrice);
    }
}
