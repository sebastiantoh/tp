package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Clears the chat box.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the chat box.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

}
