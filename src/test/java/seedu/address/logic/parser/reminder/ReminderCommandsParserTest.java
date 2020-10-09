package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.reminder.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ReminderCommandsParserTest {

    private final ReminderCommandsParser reminderCommandsParser = new ReminderCommandsParser();

    @Test
    public void parse_listCommand_returnsListCommand() {
        assertParseSuccess(reminderCommandsParser, "reminder list", null, new ListCommand());
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                reminderCommandsParser.parse("unknownCommand", null));
    }
}
