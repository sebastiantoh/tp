package seedu.address.model.dataset.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSaleTags.IMPORTANT;
import static seedu.address.testutil.TypicalSaleTags.PENDING;

import org.junit.jupiter.api.Test;

public class SaleTagCountDataTest {

    private final SaleTagCountData saleTagCountData =
            new SaleTagCountData(new TagKey(IMPORTANT), 1);

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SaleTagCountData(null, 1));
    }

    @Test
    public void getCount_valid_success() {
        assertEquals(1, this.saleTagCountData.getCount());
    }

    @Test
    public void getKeyAsStr_valid_success() {
        assertEquals(IMPORTANT.getTagName(), saleTagCountData.getKeyAsStr());
    }

    @Test
    public void equals_valid_success() {
        assertEquals(saleTagCountData, saleTagCountData);

        SaleTagCountData saleTagCountData1 = new SaleTagCountData(new TagKey(IMPORTANT), 1);
        assertEquals(saleTagCountData, saleTagCountData1);

        saleTagCountData1 = new SaleTagCountData(new TagKey(IMPORTANT), 2);
        assertNotEquals(saleTagCountData, saleTagCountData1);

        saleTagCountData1 = new SaleTagCountData(new TagKey(PENDING), 1);
        assertNotEquals(saleTagCountData, saleTagCountData1);

        saleTagCountData1 = new SaleTagCountData(new TagKey(PENDING), 2);
        assertNotEquals(saleTagCountData, saleTagCountData1);

        assertNotEquals(saleTagCountData, null);
    }
}
