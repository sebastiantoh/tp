package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Lists all contacts or sale items associated with the specified tag.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "tag find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all the contacts or sales items associated with this tag. "
            + "Note that all contacts or sales associated with this tag "
            + "will be updated automatically with the updated tag.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;
    private final boolean isContact;

    /**
     * Instantiates a FindCommand object depending on whether the user specified whether to find contacts or sales.
     */
    public FindCommand(Index targetIndex, boolean isContact) {
        this.targetIndex = targetIndex;
        this.isContact = isContact;
    }

    /**
     * Instantiates a FindCommand object that finds sales.
     */
    public FindCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.isContact = false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tag> contactTagList = model.getContactTagList();
        List<Tag> saleTagList = model.getSaleTagList();

        if (targetIndex.getOneBased() > contactTagList.size() + saleTagList.size() || targetIndex.getOneBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToFind;
        if (targetIndex.getOneBased() > contactTagList.size()) {
            tagToFind = saleTagList.get(targetIndex.getZeroBased() - contactTagList.size());
            if (isContact) {
                return new CommandResult(model.findContactsBySaleTag(tagToFind));
            }
            return new CommandResult(model.findSalesBySaleTag(tagToFind), true, false);
        } else {
            tagToFind = contactTagList.get(targetIndex.getZeroBased());
            return new CommandResult(model.findByContactTag(tagToFind), true, false);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        return (obj instanceof FindCommand) && targetIndex.equals(((FindCommand) obj).targetIndex);
    }
}
