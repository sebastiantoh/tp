package seedu.address.logic.parser.archive;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.archive.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ArchiveCommandsParserTest {

    private final ArchiveCommandsParser archiveCommandsParser = new ArchiveCommandsParser();

    @Test
    public void parse_listCommand_returnsListCommand() {
        assertParseSuccess(archiveCommandsParser, "archive list", null, new ListCommand());
    }

    @Test
    public void parse_unknownCommand_throwsParseException() throws ParseException {
        assertTrue(archiveCommandsParser.parse("unknownCommand", "lol")
                instanceof UnknownCommand);
    }
}
