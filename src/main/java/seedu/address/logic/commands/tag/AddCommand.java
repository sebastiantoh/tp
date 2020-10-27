package seedu.address.logic.commands.tag;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALES_TAG;

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

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Adds a tag (for contacts or for sales) to the StonksBook. "
            + "Parameters: "
            + String.format("%s (or %s) TAG\n", PREFIX_CONTACT_TAG, PREFIX_SALES_TAG)
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SALES_TAG + "fruits";

    public static final String MESSAGE_CONTACT_SUCCESS = "New contact tag added: %1$s";
    public static final String MESSAGE_SALES_SUCCESS = "New sales tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_CONTACT_TAG = "This contact tag already exists in StonksBook";
    public static final String MESSAGE_DUPLICATE_SALES_TAG = "This sales tag already exists in StonksBook";
    public static final String MESSAGE_CONFLICT_TYPES =
            String.format("Invalid tag type provided! Please use either %s or %s, but not both.\n",
                    PREFIX_CONTACT_TAG, PREFIX_SALES_TAG);

    private final Tag toAdd;
    private final boolean isContact;

    /**
     * Creates an AddCommand to add the specified {@code Tag}
     */
    public AddCommand(Tag tag, boolean isContact) {
        requireNonNull(tag);
        toAdd = tag;
        this.isContact = isContact;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (isContact) {
            if (model.hasContactTag(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_CONTACT_TAG);
            }
            model.addContactTag(toAdd);
            return new CommandResult(String.format(MESSAGE_CONTACT_SUCCESS, toAdd), true, false);
        } else {
            if (model.hasSaleTag(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_SALES_TAG);
            }
            model.addSaleTag(toAdd);
            return new CommandResult(String.format(MESSAGE_SALES_SUCCESS, toAdd), true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
