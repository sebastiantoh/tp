package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ContactCommandsParserTest {

    private final ContactCommandsParser contactCommandsParser = new ContactCommandsParser();

    @Test
    public void parse_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                contactCommandsParser.parse("unknownCommand", null));
    }
}
