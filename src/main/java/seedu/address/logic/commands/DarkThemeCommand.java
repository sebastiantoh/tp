package seedu.address.logic.commands;

import seedu.address.model.Model;

public class DarkThemeCommand extends Command {

    public static final String COMMAND_WORD = "darkmode";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the theme of the GUI to dark theme.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Changed to dark theme!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, 0);
    }

}
