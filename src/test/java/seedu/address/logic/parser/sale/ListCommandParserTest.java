package seedu.address.logic.parser.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import java.time.Month;
import java.time.Year;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sale.AllListCommand;
import seedu.address.logic.commands.sale.ListCommand;
import seedu.address.logic.commands.sale.MonthlyListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_listAllSales_success() {
        String userInput = "";

        ListCommand expectedCommand = new AllListCommand(true, null);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_filterByContact_success() {
        String userInput = CONTACT_INDEX_SECOND;

        ListCommand expectedCommand = new AllListCommand(false, INDEX_SECOND_ITEM);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

        // missing contact prefix
        assertParseFailure(parser, Integer.toString(INDEX_SECOND_ITEM.getOneBased()), expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CONTACT_INDEX_SECOND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMonthlyList_failure() {
        assertParseFailure(parser, ListCommand.COMMAND_WORD + "m/10",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "y/2020",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validMonthlyAndYear_success() {
        assertParseSuccess(parser, " m/10 y/2020",
                new MonthlyListCommand(Month.OCTOBER, Year.of(2020)));
    }
}
