package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tag.FindCommand;

class FindCommandParserTest {

    public static final Index INDEX_FIRST_TAG = Index.fromOneBased(1);

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validIndex_returnsFindCommand() {
        assertParseSuccess(parser, "1 c/", new FindCommand(INDEX_FIRST_TAG, true));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "one c/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingModel_throwsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
