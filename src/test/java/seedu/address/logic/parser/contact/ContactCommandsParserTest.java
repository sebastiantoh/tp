package seedu.address.logic.parser.contact;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.commands.contact.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ContactCommandsParserTest {

    private final ContactCommandsParser contactCommandsParser = new ContactCommandsParser();

    @Test
    public void parse_listCommand_returnsListCommand() {
        assertParseSuccess(contactCommandsParser, "contact list", null, new ListCommand());
    }

    @Test
    public void parse_unknownCommand_throwsParseException() throws ParseException {
        assertTrue(contactCommandsParser.parse("unknownCommand", "lol")
                instanceof UnknownCommand);
    }
}
