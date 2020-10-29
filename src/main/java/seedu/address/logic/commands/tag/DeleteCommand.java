package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALES_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Deletes a tag based on its displayed index in the tag list and updates all
 * items associated with this tag.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "tag delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tag identified by the index number used in the displayed reminder list. "
            + "Note that all associations with this tag will be cleared.\n"
            + String.format("Parameters: (%s or %s) INDEX \n", PREFIX_CONTACT_TAG, PREFIX_SALES_TAG)
            + "Example: " + COMMAND_WORD + " " + PREFIX_CONTACT_TAG + "1";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted tag: %1$s";

    private final Index targetIndex;

    private final boolean isContact;

    /**
     * Instantiates a new DeleteCommand for tags.
     */
    public DeleteCommand(Index targetIndex, boolean isContact) {
        this.targetIndex = targetIndex;
        this.isContact = isContact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Contact tags will be displayed in front of Sale tags.
        List<Tag> contactTagList = model.getContactTagList();
        List<Tag> saleTagList = model.getSaleTagList();

        if (isContact && targetIndex.getOneBased() > contactTagList.size()
                || !isContact && targetIndex.getOneBased() > saleTagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToDelete;
        if (!isContact) {
            tagToDelete = saleTagList.get(targetIndex.getZeroBased());
            if (model.anySalesWithoutTags(tagToDelete)) {
                throw new CommandException(Messages.MESSAGE_SALES_NO_TAGS);
            } else {
                model.deleteSaleTag(tagToDelete);
            }
        } else {
            tagToDelete = contactTagList.get(targetIndex.getZeroBased());
            model.deleteContactTag(tagToDelete);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagToDelete), true, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        // state check
        DeleteCommand d = (DeleteCommand) other;
        return targetIndex.equals(d.targetIndex) && isContact == d.isContact;
    }
}
