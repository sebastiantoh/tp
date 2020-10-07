package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalContactTags.CLASSMATES;
import static seedu.address.testutil.TypicalContactTags.COLLEAGUES;
import static seedu.address.testutil.TypicalContactTags.FRIENDS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

class UniqueContactTagListTest {
    private final UniqueContactTagList uniqueContactTagList = new UniqueContactTagList();

    @Test
    void contains_nullContactTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactTagList.contains(null));
    }

    @Test
    void contains_contactTagNotInList_returnsFalse() {
        assertFalse(uniqueContactTagList.contains(FRIENDS));
    }

    @Test
    void contains_contactTagList_returnsTrue() {
        uniqueContactTagList.add(FRIENDS);
        assertTrue(uniqueContactTagList.contains(FRIENDS));
    }

    @Test
    void add_nullContactTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactTagList.add(null));
    }

    @Test
    void add_duplicateContactTag_doesNotChangeState() {
        uniqueContactTagList.add(FRIENDS);
        uniqueContactTagList.add(FRIENDS);
        assertEquals(1, uniqueContactTagList.asUnmodifiableObservableList().size());
    }

    @Test
    void setTags_duplicateContactTag_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(FRIENDS, FRIENDS);
        assertThrows(DuplicateTagException.class, () -> uniqueContactTagList.setTags(listWithDuplicateTags));
    }

    @Test
    void setTags_nullUniqueContactTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactTagList.setTags((UniqueContactTagList) null));
    }

    @Test
    void setTags_uniqueContactTagList_replacesOwnListWithProvidedUniqueContactTagList() {
        uniqueContactTagList.add(FRIENDS);
        UniqueContactTagList newUniqueContactTagList = new UniqueContactTagList();
        newUniqueContactTagList.add(CLASSMATES);
        uniqueContactTagList.setTags(newUniqueContactTagList);
        assertEquals(newUniqueContactTagList, uniqueContactTagList);
    }

    @Test
    void setTags_uniqueContactTagList_replacesOwnListWithProvidedList() {
        uniqueContactTagList.add(FRIENDS);
        List<Tag> tagList = Collections.singletonList(COLLEAGUES);
        uniqueContactTagList.setTags(tagList);
        UniqueContactTagList newUniqueContactTagList = new UniqueContactTagList();
        newUniqueContactTagList.add(COLLEAGUES);
        assertEquals(uniqueContactTagList, newUniqueContactTagList);
    }

    @Test
    void remove_nullContactTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueContactTagList.remove(null));
    }

    @Test
    void remove_contactTagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueContactTagList.remove(CLASSMATES));
    }

    @Test
    void remove_existingContactTag_removesContactTag() {
        uniqueContactTagList.add(FRIENDS);
        uniqueContactTagList.remove(FRIENDS);
        assertEquals(0, uniqueContactTagList.asUnmodifiableObservableList().size());
    }

    @Test
    void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        uniqueContactTagList.add(FRIENDS);
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueContactTagList.asUnmodifiableObservableList().remove(0));
    }
}
