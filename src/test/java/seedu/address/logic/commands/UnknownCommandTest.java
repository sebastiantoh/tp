package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MOST_SIMILAR_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.ListCommand;

public class UnknownCommandTest {

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnknownCommand(null));
    }

    @Test
    public void execute_oneWordHaveSimilar_success() {
        UnknownCommand unknownCommand = new UnknownCommand("hel");
        CommandResult expectedCommandResult = new CommandResult(String.format(MOST_SIMILAR_COMMAND, HelpCommand.COMMAND_WORD));
        assertEquals(expectedCommandResult, unknownCommand.execute(null));

        unknownCommand = new UnknownCommand("contactl");
        expectedCommandResult = new CommandResult(String.format(MOST_SIMILAR_COMMAND, ListCommand.COMMAND_WORD));
        assertEquals(expectedCommandResult, unknownCommand.execute(null));
    }

    @Test
    public void execute_twoWordHaveSimilar_success() {
        UnknownCommand unknownCommand = new UnknownCommand("contact l");
        CommandResult expectedCommandResult = new CommandResult(String.format(MOST_SIMILAR_COMMAND, ListCommand.COMMAND_WORD));
        assertEquals(expectedCommandResult, unknownCommand.execute(null));

        unknownCommand = new UnknownCommand("cont l");
        expectedCommandResult = new CommandResult(String.format(MOST_SIMILAR_COMMAND, ListCommand.COMMAND_WORD));
        assertEquals(expectedCommandResult, unknownCommand.execute(null));
    }


    @Test
    public void execute_noSimilar_success() {
        UnknownCommand unknownCommand = new UnknownCommand("asciajsocaisj");
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNKNOWN_COMMAND);
        assertEquals(expectedCommandResult, unknownCommand.execute(null));

        unknownCommand = new UnknownCommand("cont ljoaisjcoiacjk");
        expectedCommandResult = new CommandResult(MESSAGE_UNKNOWN_COMMAND);
        assertEquals(expectedCommandResult, unknownCommand.execute(null));
    }
}
