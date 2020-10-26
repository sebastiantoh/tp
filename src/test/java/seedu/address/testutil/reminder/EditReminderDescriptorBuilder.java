package seedu.address.testutil.reminder;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.reminder.EditCommand.EditReminderDescriptor;
import seedu.address.model.Message;

/**
 * A utility class to help with building EditReminderDescriptor objects.
 */
public class EditReminderDescriptorBuilder {

    private EditReminderDescriptor descriptor;

    public EditReminderDescriptorBuilder() {
        descriptor = new EditReminderDescriptor();
    }

    public EditReminderDescriptorBuilder(EditReminderDescriptor descriptor) {
        this.descriptor = new EditReminderDescriptor(descriptor);
    }

    /**
     * Sets the {@code contactIndex} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withContactIndex(Index contactIndex) {
        descriptor.setContactIndex(contactIndex);
        return this;
    }

    /**
     * Sets the {@code message} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withMessage(String message) {
        descriptor.setMessage(new Message(message));
        return this;
    }

    /**
     * Sets the {@code scheduledDate} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withScheduledDate(LocalDateTime scheduledDate) {
        descriptor.setScheduledDate(scheduledDate);
        return this;
    }

    /**
     * Sets the {@code status} of the {@code EditReminderDescriptor} that we are building.
     */
    public EditReminderDescriptorBuilder withStatus(Boolean status) {
        descriptor.setCompleted(status);
        return this;
    }

    public EditReminderDescriptor build() {
        return descriptor;
    }
}
