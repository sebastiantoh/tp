package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.storage.JsonAdaptedSale.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.sale.TypicalSales.BALL;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.UnitPrice;

public class JsonAdaptedSaleTest {
    private static final String INVALID_ITEM_NAME = "@pple";
    private static final String INVALID_DATETIME_1 = "2020/10/10 10AM";
    private static final String INVALID_DATETIME_2 = "30/10/2020 12:12";
    private static final Integer INVALID_QUANTITY = 60000000;
    private static final String INVALID_UNIT_PRICE = "1.493";

    private static final String VALID_ITEM_NAME = BALL.getItemName().toString();
    private static final Person VALID_BUYER = ALICE;
    private static final String VALID_DATETIME = "2020-10-30T15:19";
    private static final Integer VALID_QUANTITY = BALL.getQuantity().quantity;
    private static final String VALID_UNIT_PRICE = BALL.getUnitPrice().getAmount().setScale(2).toPlainString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BALL.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    @BeforeEach
    public void setUp() {
        personList.add(VALID_BUYER);
    }

    @Test
    public void toModelType_validSaleDetails_returnsSale() throws IllegalValueException {
        JsonAdaptedSale sale = new JsonAdaptedSale(BALL);
        assertEquals(BALL, sale.toModelType(personList));
    }

    @Test
    public void toModelType_invalidItemName_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(INVALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        VALID_DATETIME,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = ItemName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_nullItemName_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(null,
                        VALID_BUYER.getId(),
                        VALID_DATETIME,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_nullBuyer_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        null,
                        VALID_DATETIME,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedSale sale1 =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        INVALID_DATETIME_1,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);

        JsonAdaptedSale sale2 =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        INVALID_DATETIME_2,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = MESSAGE_INVALID_DATETIME;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale1.toModelType(personList));
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale2.toModelType(personList));
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        null,
                        VALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Datetime of Purchase");
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        VALID_DATETIME,
                        INVALID_QUANTITY,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        VALID_DATETIME,
                        null,
                        VALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_invalidUnitPrice_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        VALID_DATETIME,
                        VALID_QUANTITY,
                        INVALID_UNIT_PRICE,
                        VALID_TAGS);
        String expectedMessage = UnitPrice.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }

    @Test
    public void toModelType_nullUnitPrice_throwsIllegalValueException() {
        JsonAdaptedSale sale =
                new JsonAdaptedSale(VALID_ITEM_NAME,
                        VALID_BUYER.getId(),
                        VALID_DATETIME,
                        VALID_QUANTITY,
                        null,
                        VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UnitPrice.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sale.toModelType(personList));
    }
}
