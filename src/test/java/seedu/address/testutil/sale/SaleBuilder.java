package seedu.address.testutil.sale;

import static seedu.address.testutil.person.TypicalPersons.BOB;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Sale objects.
 */
public class SaleBuilder {
    public static final Person DEFAULT_BUYER = BOB;
    public static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal("20.50");
    public static final String DEFAULT_ITEM_NAME = "Pizza";
    public static final Integer DEFAULT_QUANTITY = 50;
    private static final LocalDateTime DEFAULT_DATETIME =
            LocalDateTime.of(2020, 10, 17, 15, 20);

    private ItemName name;
    private Person buyer;
    private LocalDateTime datetimeOfPurchase;
    private Quantity quantity;
    private UnitPrice unitPrice;
    private Set<Tag> tags;

    /**
     * Creates a {@code SaleBuilder} with the default details.
     */
    public SaleBuilder() {
        name = new ItemName(DEFAULT_ITEM_NAME);
        buyer = BOB;
        datetimeOfPurchase = DEFAULT_DATETIME;
        quantity = new Quantity(DEFAULT_QUANTITY);
        unitPrice = new UnitPrice(DEFAULT_UNIT_PRICE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the SaleBuilder with the data of {@code saleToCopy}.
     */
    public SaleBuilder(Sale saleToCopy) {
        name = saleToCopy.getItemName();
        buyer = saleToCopy.getBuyer();
        datetimeOfPurchase = saleToCopy.getDatetimeOfPurchase();
        quantity = saleToCopy.getQuantity();
        unitPrice = saleToCopy.getUnitPrice();
        tags = saleToCopy.getTags();
    }

    /**
     * Sets the {@code ItemName} of the {@code Sale} that we are building.
     */
    public SaleBuilder withItemName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    /**
     * Sets the {@code Buyer} of the {@code Sale} that we are building.
     */
    public SaleBuilder withBuyer(Person buyer) {
        this.buyer = buyer;
        return this;
    }

    /**
     * Sets the {@code datetimeOfPurchase} of the {@code Sale} that we are building.
     */
    public SaleBuilder withDatetimeOfPurchase(LocalDateTime datetime) {
        this.datetimeOfPurchase = datetime;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Sale} that we are building.
     */
    public SaleBuilder withQuantity(Integer quantity) {
        this.quantity = new Quantity(quantity);
        return this;
    }

    /**
     * Sets the {@code UnitPrice} of the {@code Sale} that we are building.
     */
    public SaleBuilder withUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = new UnitPrice(unitPrice);
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code Sale} that we are building.
     */
    public SaleBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Sale build() {
        return new Sale(name, buyer, datetimeOfPurchase, quantity, unitPrice, tags);
    }
}
