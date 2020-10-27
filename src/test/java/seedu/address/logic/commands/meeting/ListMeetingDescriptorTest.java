package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.ListCommand.ListMeetingDescriptor;
import seedu.address.testutil.meeting.ListMeetingDescriptorBuilder;

public class ListMeetingDescriptorTest {

    @Test
    public void equals() {
        ListMeetingDescriptor descriptor =
                new ListMeetingDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM).build();
        ListMeetingDescriptor descriptorWithSameValues =
                new ListMeetingDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM).build();
        ListMeetingDescriptor descriptorWithDiffValues =
                new ListMeetingDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM)
                        .withShouldShowAll(true).build();

        // same values -> returns true
        assertTrue(descriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        assertFalse(descriptor.equals(null));

        // different types -> returns false
        assertFalse(descriptor.equals(5));

        // different values -> returns false
        assertFalse(descriptor.equals(descriptorWithDiffValues));
    }
}
