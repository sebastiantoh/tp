package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MeetingCommandsParserTest {

    private final MeetingCommandsParser meetingCommandsParser = new MeetingCommandsParser();

    @Test
    public void parse_listCommand_returnsListCommand() {
        assertParseSuccess(meetingCommandsParser, "meeting list", null, new ListCommand());
    }

    @Test
    public void parse_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                meetingCommandsParser.parse("unknownCommand", null));
    }
}
