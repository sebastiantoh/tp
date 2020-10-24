package seedu.address.logic.parser.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sale.DeleteCommand;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validSingleIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, " " + PREFIX_SALE_INDEX + "1",
                new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM))));
    }

    @Test
    public void parse_validMultipleIndexes_returnsDeleteCommand() {
        assertParseSuccess(parser, " " + PREFIX_SALE_INDEX + "1 " + PREFIX_SALE_INDEX + "3",
                new DeleteCommand(new ArrayList<>(Arrays.asList(INDEX_FIRST_ITEM, INDEX_THIRD_ITEM))));
    }

    @Test
    public void parse_invalidMultipleIndexes_returnsDeleteCommand() {
        assertParseFailure(parser, " " + PREFIX_SALE_INDEX + "1 " + PREFIX_SALE_INDEX + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
