package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

class JsonAdaptedTagTest {
    private static final String FRIENDS = "friends";
    private static final String CLASSMATES = "classmates";

    private static final Tag VALID_TAG = new Tag(FRIENDS);

    @Test
    void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(FRIENDS);
        assertEquals(VALID_TAG, tag.toModelType());
    }
}
