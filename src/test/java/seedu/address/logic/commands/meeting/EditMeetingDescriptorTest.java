package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.testutil.TypicalDates;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {

    @Test
    public void equals() {
        EditMeetingDescriptor descriptor =
                new EditMeetingDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM).build();
        EditMeetingDescriptor descriptorWithSameValues =
                new EditMeetingDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM).build();
        EditMeetingDescriptor descriptorWithDiffValues =
                new EditMeetingDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM)
                        .withStartDate(TypicalDates.TYPICAL_DATE_2).build();

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
