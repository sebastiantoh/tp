package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UnitPrice;

/**
 * Jackson-friendly version of {@link Sale}.
 */
class JsonAdaptedSale {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Sale's %s field is missing!";

    private final String itemName;
    private final String quantity;
    private final Integer unitPriceDollar;
    private final Integer unitPriceCent;

    /**
     * Constructs a {@code JsonAdaptedSale} with the given sale details.
     */
    @JsonCreator
    public JsonAdaptedSale(@JsonProperty("itemName") String itemName, @JsonProperty("quantity") String quantity,
                           @JsonProperty("unitPriceDollar") Integer unitPriceDollar,
                           @JsonProperty("unitPriceCent") Integer unitPriceCent) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPriceDollar = unitPriceDollar;
        this.unitPriceCent = unitPriceCent;
    }

    /**
     * Converts a given {@code Sale} into this class for Jackson use.
     */
    public JsonAdaptedSale(Sale source) {
        itemName = source.getItemName().name;
        quantity = source.getQuantity().toString();
        unitPriceDollar = source.getUnitPrice().dollars;
        unitPriceCent = source.getUnitPrice().cents;
    }

    /**
     * Converts this Jackson-friendly adapted sale object into the model's {@code Sale} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted sale.
     */
    public Sale toModelType() throws IllegalValueException {
        System.out.println("testing");
        if (itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidItemName(itemName)) {
            throw new IllegalValueException(ItemName.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelItemName = new ItemName(itemName);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (unitPriceDollar == null || unitPriceCent == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName()));
        }
        if (!UnitPrice.isValidUnitPrice(unitPriceDollar, unitPriceCent)) {
            throw new IllegalValueException(UnitPrice.MESSAGE_CONSTRAINTS);
        }
        final UnitPrice modelUnitPrice = new UnitPrice(unitPriceDollar, unitPriceCent);

        return new Sale(modelItemName, modelQuantity, modelUnitPrice);
    }

    public String toString() {
        return this.itemName;
    }

}
