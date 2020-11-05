package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
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
    private final Integer buyerId;
    private final String datetimeOfPurchase;
    private final Integer quantity;
    private final String unitPrice;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedSale} with the given sale details.
     */
    @JsonCreator
    public JsonAdaptedSale(@JsonProperty("itemName") String itemName,
                           @JsonProperty("buyerId") Integer buyerId,
                           @JsonProperty("datetimeOfPurchase") String datetimeOfPurchase,
                           @JsonProperty("quantity") Integer quantity,
                           @JsonProperty("unitPrice") String unitPrice,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.itemName = itemName;
        this.buyerId = buyerId;
        this.datetimeOfPurchase = datetimeOfPurchase;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Sale} into this class for Jackson use.
     */
    public JsonAdaptedSale(Sale source) {
        itemName = source.getItemName().name;
        buyerId = source.getBuyer().getId();
        datetimeOfPurchase = source.getDatetimeOfPurchase().toString();
        quantity = source.getQuantity().quantity;
        unitPrice = source.getUnitPrice().getUnitPriceString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted sale object into the model's {@code Sale} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted sale.
     */
    public Sale toModelType(ObservableList<Person> personList) throws IllegalValueException {
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

        if (this.buyerId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }
        final Person modelBuyer = personList.stream().filter(person -> person.getId().equals(this.buyerId))
                .findFirst().orElseThrow(() -> new IllegalValueException("Invalid buyer specified"));

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

        if (unitPrice == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName()));
        }
        if (!UnitPrice.isValidUnitPriceString(unitPrice)) {
            throw new IllegalValueException(UnitPrice.MESSAGE_CONSTRAINTS);
        }
        final UnitPrice modelUnitPrice = new UnitPrice(new BigDecimal(unitPrice));

        final Set<Tag> saleTagsSet = new HashSet<>(saleTags);

        return new Sale(modelItemName, modelBuyer, modelDatetimeOfPurchase, modelQuantity, modelUnitPrice, saleTagsSet);
    }
}
