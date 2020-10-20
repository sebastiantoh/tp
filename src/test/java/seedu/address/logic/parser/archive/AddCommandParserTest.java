package seedu.address.logic.parser.archive;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.archive.AddCommand;

/**
 * Contains user input validation tests for {@code ArchiveCommandParser}.
 */
public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_validArgs_returnsArchiveCommand() {
        assertParseSuccess(parser, "1", new AddCommand(INDEX_FIRST_ITEM));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
