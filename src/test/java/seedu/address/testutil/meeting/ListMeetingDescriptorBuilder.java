package seedu.address.testutil.meeting;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.ListCommand.ListMeetingDescriptor;

/**
 * A utility class to help with building ListMeetingDescriptor objects.
 */
public class ListMeetingDescriptorBuilder {

    private ListMeetingDescriptor descriptor;

    public ListMeetingDescriptorBuilder() {
        descriptor = new ListMeetingDescriptor();
    }

    public ListMeetingDescriptorBuilder(ListMeetingDescriptor descriptor) {
        this.descriptor = new ListMeetingDescriptor(descriptor);
    }

    /**
     * Sets the {@code contactIndex} of the {@code ListMeetingDescriptor} that we are building.
     */
    public ListMeetingDescriptorBuilder withContactIndex(Index contactIndex) {
        descriptor.setContactIndex(contactIndex);
        return this;
    }

    /**
     * Sets the {@code shouldShowAll} of the {@code ListMeetingDescriptor} that we are building.
     */
    public ListMeetingDescriptorBuilder withShouldShowAll(boolean shouldShowAll) {
        descriptor.setShouldShowAll(shouldShowAll);
        return this;
    }

    public ListMeetingDescriptor build() {
        return descriptor;
    }
}
