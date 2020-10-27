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
            + ": Finds all the contacts or sales items associated with this tag.\n"
            + "Parameters:  ct/INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " ct/1";

    private final Index targetIndex;
    private final boolean isContact;
    private final boolean isClient;

    /**
     * Instantiates a FindCommand object depending on whether the user specified whether to find contacts or sales.
     */
    public FindCommand(Index targetIndex, Boolean isContact) {
        requireNonNull(targetIndex);
        requireNonNull(isContact);

        this.targetIndex = targetIndex;
        this.isContact = isContact;
        this.isClient = false;
    }

    /**
     * Instantiates a FindCommand object that finds clients who bought sales items associated with the given sales tag.
     */
    public FindCommand(Index targetIndex, boolean isContact, boolean isClient) {
        this.targetIndex = targetIndex;
        this.isContact = isContact;
        this.isClient = isClient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tag> contactTagList = model.getContactTagList();
        List<Tag> saleTagList = model.getSaleTagList();

        if (isContact && targetIndex.getOneBased() > contactTagList.size()
                || !isContact && targetIndex.getOneBased() > saleTagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToFind;
        if (!isContact) {
            tagToFind = saleTagList.get(targetIndex.getZeroBased());
            if (isClient) {
                return new CommandResult(model.findContactsBySaleTag(tagToFind), true, false);
            } else {
                return new CommandResult(model.findSalesBySaleTag(tagToFind), true, false);
            }
        } else {
            tagToFind = contactTagList.get(targetIndex.getZeroBased());
            return new CommandResult(model.findByContactTag(tagToFind), true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        // state check
        FindCommand f = (FindCommand) other;
        return targetIndex.equals((f.targetIndex)) && isContact == f.isContact;
    }
}
