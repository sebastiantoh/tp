package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Sets the GUI to light theme.
 */
public class LightThemeCommand extends Command {

    public static final String COMMAND_WORD = "lightmode";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the theme of the GUI to light theme.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Changed to light theme!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, 1);
    }

}
