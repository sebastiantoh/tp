package seedu.address.logic.parser.meeting;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MeetingCommandsParserTest {

    private final MeetingCommandsParser meetingCommandsParser = new MeetingCommandsParser();

    @Test
    public void parse_listCommand_returnsListCommand() {
        assertParseSuccess(meetingCommandsParser, "meeting list", null, new ListCommand());
    }

    @Test
    public void parse_unknownCommand_throwsParseException() throws ParseException {
        assertTrue(meetingCommandsParser.parse("unknownCommand", "lol")
                instanceof UnknownCommand);
    }
}
