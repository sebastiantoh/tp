package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all existing tags in StonksBook.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "tag list";

    public static final String MESSAGE_LIST_TAGS_SUCCESS = "Listed all tags!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all tags in StonksBook.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(MESSAGE_LIST_TAGS_SUCCESS, true, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        return other instanceof ListCommand;
    }
}
