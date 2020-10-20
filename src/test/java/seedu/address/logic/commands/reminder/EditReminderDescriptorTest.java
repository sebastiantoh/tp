package seedu.address.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.reminder.EditCommand.EditReminderDescriptor;
import seedu.address.testutil.TypicalDates;
import seedu.address.testutil.reminder.EditReminderDescriptorBuilder;

public class EditReminderDescriptorTest {

    @Test
    public void equals() {
        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM).build();
        EditReminderDescriptor descriptorWithSameValues =
                new EditReminderDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM).build();
        EditReminderDescriptor descriptorWithDiffValues =
                new EditReminderDescriptorBuilder().withContactIndex(INDEX_SECOND_ITEM)
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_2).build();

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
