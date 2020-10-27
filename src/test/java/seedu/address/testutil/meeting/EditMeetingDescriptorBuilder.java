package seedu.address.testutil.meeting;

import java.time.Duration;
import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.model.Message;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Sets the {@code contactIndex} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withContactIndex(Index contactIndex) {
        descriptor.setContactIndex(contactIndex);
        return this;
    }

    /**
     * Sets the {@code message} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withMessage(String message) {
        descriptor.setMessage(new Message(message));
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withStartDate(LocalDateTime scheduledDate) {
        descriptor.setStartDate(scheduledDate);
        return this;
    }

    /**
     * Sets the {@code duration} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDuration(Duration duration) {
        descriptor.setDuration(duration);
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
