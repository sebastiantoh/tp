package seedu.address.logic.parser.reminder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.reminder.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ReminderCommandsParserTest {

    private final ReminderCommandsParser reminderCommandsParser = new ReminderCommandsParser();

    @Test
    public void parse_listCommand_returnsListCommand() {
        assertParseSuccess(reminderCommandsParser, "reminder list", null, new ListCommand(null));
    }

    @Test
    public void parse_listCommandPendingStatus_returnsListCommand() {
        assertParseSuccess(reminderCommandsParser, "reminder list", " st/pending", new ListCommand(false));
    }

    @Test
    public void parse_unknownCommand_throwsParseException() throws ParseException {
        assertTrue(reminderCommandsParser.parse("unknownCommand", null) instanceof UnknownCommand);
    }
}
