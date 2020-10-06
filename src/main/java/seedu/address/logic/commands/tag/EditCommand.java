package seedu.address.logic.commands.tag;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "tag edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the tag identified by the index number used in the displayed tag list. "
            + "Note that all associations with this tag will be updated.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in StonksBook.";

    public static final String MESSAGE_EDIT_TAG_SUCCESS = "Edited tag %s to: %s";

    private final Index targetIndex;
    private final EditTagDescriptor editTagDescriptor;

    public EditCommand(Index targetIndex, EditTagDescriptor editTagDescriptor) {
        this.targetIndex = targetIndex;
        this.editTagDescriptor = editTagDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> tagList = model.getAddressBook().getTagList();

        if (targetIndex.getOneBased() > tagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToEdit = tagList.get(targetIndex.getZeroBased());
        Tag editedTag = createEditedTag(tagToEdit, editTagDescriptor);

        if (!tagToEdit.isSameTag(editedTag) && model.hasContactTag(editedTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        model.editContactTag(tagToEdit, editedTag);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_TAG_SUCCESS, tagToEdit, editedTag));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editTagDescriptor}.
     */
    private static Tag createEditedTag(Tag tagToEdit, seedu.address.logic.commands.tag.EditCommand.EditTagDescriptor editTagDescriptor) {
        assert tagToEdit != null;

        String updatedTagName = editTagDescriptor.getTagName().orElse(tagToEdit.getTagName());

        return new Tag(updatedTagName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.logic.commands.tag.EditCommand)) {
            return false;
        }

        // state check
        seedu.address.logic.commands.tag.EditCommand e = (seedu.address.logic.commands.tag.EditCommand) other;
        return targetIndex.equals(e.targetIndex)
                && editTagDescriptor.equals(e.editTagDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditTagDescriptor {
        private String tagName;

        public EditTagDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTagDescriptor(seedu.address.logic.commands.tag.EditCommand.EditTagDescriptor toCopy) {
            setTagName(toCopy.tagName);
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public Optional<String> getTagName() {
            return Optional.ofNullable(tagName);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof seedu.address.logic.commands.tag.EditCommand.EditTagDescriptor)) {
                return false;
            }

            // state check
            seedu.address.logic.commands.tag.EditCommand.EditTagDescriptor e = (seedu.address.logic.commands.tag.EditCommand.EditTagDescriptor) other;

            return getTagName().equals(e.getTagName());
        }
    }
}
