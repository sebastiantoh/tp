package seedu.address.model.dataset.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSaleTags.FOLLOW_UP;
import static seedu.address.testutil.TypicalSaleTags.IMPORTANT;

import org.junit.jupiter.api.Test;

public class TagKeyTest {

    private final TagKey tagKey = new TagKey(IMPORTANT);

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagKey(null));
    }

    @Test
    public void getTag_valid_success() {
        assertEquals(IMPORTANT, this.tagKey.getTag());
    }

    @Test
    public void setTag_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagKey.setTag(null));
    }

    @Test
    public void setTag_valid_success() {
        tagKey.setTag(FOLLOW_UP);
        assertEquals(FOLLOW_UP, tagKey.getTag());
    }

    @Test
    public void equals_valid_success() {
        assertEquals(tagKey, tagKey);

        TagKey tagKey1 = new TagKey(IMPORTANT);
        assertEquals(tagKey, tagKey1);

        tagKey1 = new TagKey(FOLLOW_UP);
        assertNotEquals(tagKey, tagKey1);

        assertNotEquals(tagKey, null);
    }
}
