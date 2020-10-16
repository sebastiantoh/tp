package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Sale}.
 */
class JsonAdaptedSale {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Sale's %s field is missing!";

    private final String itemName;
    private final String datetimeOfPurchase;
    private final String quantity;
    private final Integer unitPriceDollar;
    private final Integer unitPriceCent;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSale} with the given sale details.
     */
    @JsonCreator
    public JsonAdaptedSale(@JsonProperty("itemName") String itemName,
                           @JsonProperty("datetimeOfPurchase") String datetimeOfPurchase,
                           @JsonProperty("quantity") String quantity,
                           @JsonProperty("unitPriceDollar") Integer unitPriceDollar,
                           @JsonProperty("unitPriceCent") Integer unitPriceCent,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.itemName = itemName;
        this.datetimeOfPurchase = datetimeOfPurchase;
        this.quantity = quantity;
        this.unitPriceDollar = unitPriceDollar;
        this.unitPriceCent = unitPriceCent;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Sale} into this class for Jackson use.
     */
    public JsonAdaptedSale(Sale source) {
        itemName = source.getItemName().name;
        datetimeOfPurchase = source.getDatetimeOfPurchase().toString();
        quantity = source.getQuantity().toString();
        unitPriceDollar = source.getUnitPrice().dollars;
        unitPriceCent = source.getUnitPrice().cents;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted sale object into the model's {@code Sale} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted sale.
     */
    public Sale toModelType() throws IllegalValueException {
        final List<Tag> saleTags = new ArrayList<>();

        for (JsonAdaptedTag tag : tagged) {
            saleTags.add(tag.toModelType());
        }

        if (itemName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidItemName(itemName)) {
            throw new IllegalValueException(ItemName.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelItemName = new ItemName(itemName);

        if (datetimeOfPurchase == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Datetime of Purchase"));
        }
        final LocalDateTime modelDatetimeOfPurchase;
        try {
            modelDatetimeOfPurchase = LocalDateTime.parse(this.datetimeOfPurchase);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(MESSAGE_INVALID_DATETIME);
        }

        if (quantity == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (unitPriceDollar == null || unitPriceCent == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName()));
        }
        if (!UnitPrice.isValidUnitPrice(unitPriceDollar, unitPriceCent)) {
            throw new IllegalValueException(UnitPrice.MESSAGE_CONSTRAINTS);
        }
        final UnitPrice modelUnitPrice = new UnitPrice(unitPriceDollar, unitPriceCent);

        final Set<Tag> saleTagsSet = new HashSet<>(saleTags);

        return new Sale(modelItemName, modelDatetimeOfPurchase, modelQuantity, modelUnitPrice, saleTagsSet);
    }
}
