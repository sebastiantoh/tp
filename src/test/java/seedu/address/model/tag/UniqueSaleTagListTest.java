package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSaleTags.FOLLOW_UP;
import static seedu.address.testutil.TypicalSaleTags.IMPORTANT;
import static seedu.address.testutil.TypicalSaleTags.PENDING;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

class UniqueSaleTagListTest {
    private final UniqueSaleTagList uniqueSaleTagList = new UniqueSaleTagList();

    @Test
    void contains_nullContactTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleTagList.contains(null));
    }

    @Test
    void contains_contactTagNotInList_returnsFalse() {
        assertFalse(uniqueSaleTagList.contains(IMPORTANT));
    }

    @Test
    void contains_contactTagList_returnsTrue() {
        uniqueSaleTagList.add(IMPORTANT);
        assertTrue(uniqueSaleTagList.contains(IMPORTANT));
    }

    @Test
    void add_nullContactTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleTagList.add(null));
    }

    @Test
    void add_duplicateContactTag_doesNotChangeState() {
        uniqueSaleTagList.add(IMPORTANT);
        uniqueSaleTagList.add(IMPORTANT);
        assertEquals(1, uniqueSaleTagList.asUnmodifiableObservableList().size());
    }

    @Test
    void setTags_duplicateContactTag_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(IMPORTANT, IMPORTANT);
        assertThrows(DuplicateTagException.class, () -> uniqueSaleTagList.setTags(listWithDuplicateTags));
    }

    @Test
    void setTags_nullUniqueSaleTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleTagList.setTags((UniqueSaleTagList) null));
    }

    @Test
    void setTags_uniqueSaleTagList_replacesOwnListWithProvidedUniqueSaleTagList() {
        uniqueSaleTagList.add(IMPORTANT);
        UniqueSaleTagList newUniqueSaleTagList = new UniqueSaleTagList();
        newUniqueSaleTagList.add(FOLLOW_UP);
        uniqueSaleTagList.setTags(newUniqueSaleTagList);
        assertEquals(newUniqueSaleTagList, uniqueSaleTagList);
    }

    @Test
    void setTags_uniqueSaleTagList_replacesOwnListWithProvidedList() {
        uniqueSaleTagList.add(IMPORTANT);
        List<Tag> tagList = Collections.singletonList(PENDING);
        uniqueSaleTagList.setTags(tagList);
        UniqueSaleTagList newUniqueSaleTagList = new UniqueSaleTagList();
        newUniqueSaleTagList.add(PENDING);
        assertEquals(uniqueSaleTagList, newUniqueSaleTagList);
    }

    @Test
    void remove_nullContactTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSaleTagList.remove(null));
    }

    @Test
    void remove_contactTagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueSaleTagList.remove(FOLLOW_UP));
    }

    @Test
    void remove_existingContactTag_removesContactTag() {
        uniqueSaleTagList.add(IMPORTANT);
        uniqueSaleTagList.remove(IMPORTANT);
        assertEquals(0, uniqueSaleTagList.asUnmodifiableObservableList().size());
    }

    @Test
    void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        uniqueSaleTagList.add(IMPORTANT);
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueSaleTagList.asUnmodifiableObservableList().remove(0));
    }
}
