package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tag.DeleteCommand;

class DeleteCommandParserTest {

    public static final Index INDEX_FIRST_TAG = Index.fromOneBased(1);
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " ct/1", new DeleteCommand(INDEX_FIRST_TAG, true));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ct/one", MESSAGE_INVALID_INDEX);
    }

    @Test
    void parse_missingModel_throwsParseException() {
        assertParseFailure(parser, " 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
