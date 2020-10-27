package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_PERSONS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "contact list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all contacts in StonksBook.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_UNARCHIVED_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand; // instanceof handles nulls
    }
}
