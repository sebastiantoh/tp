package seedu.address.logic.parser.sale;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnknownCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SaleCommandsParserTest {

    private final SaleCommandsParser saleCommandsParser = new SaleCommandsParser();

    @Test
    public void parse_unknownCommand_throwsParseException() throws ParseException {
        assertTrue(saleCommandsParser.parse("unknownCommand", "lol")
                instanceof UnknownCommand);
    }
}
