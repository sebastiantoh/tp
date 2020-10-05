package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;


/**
 * Adds a tag to the StonksBook.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "tag add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the StonksBook. "
            + "Parameters: "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG + "friends";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the StonksBook";

    private final Tag toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Tag}
     */
    public AddCommand(Tag tag) {
        requireNonNull(tag);
        toAdd = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        model.addTag(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.logic.commands.tag.AddCommand // instanceof handles nulls
            && toAdd.equals(((seedu.address.logic.commands.tag.AddCommand) other).toAdd));
    }
}
