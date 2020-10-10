package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.contact.SortCommandParser.ORDER_KEYWORD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser sortCommandParser = new SortCommandParser();

    @Test
    public void parse_nameOnly_success() {
        String args = " " + PREFIX_CONTACT_NAME.getPrefix();
        SortCommand expectedSortCommand = new SortCommand(PREFIX_CONTACT_NAME, false);
        assertParseSuccess(sortCommandParser, args, expectedSortCommand);
    }

    @Test
    public void parse_emailOnly_success() {
        String args = " " + PREFIX_CONTACT_EMAIL.getPrefix();
        SortCommand expectedSortCommand = new SortCommand(PREFIX_CONTACT_EMAIL, false);
        assertParseSuccess(sortCommandParser, args, expectedSortCommand);
    }

    @Test
    public void parse_nameAndDesc_success() {
        String args = " " + PREFIX_CONTACT_NAME.getPrefix() + " " + ORDER_KEYWORD;
        SortCommand expectedSortCommand = new SortCommand(PREFIX_CONTACT_NAME, true);
        assertParseSuccess(sortCommandParser, args, expectedSortCommand);
    }

    @Test
    public void parse_emailAndDesc_success() {
        String args = " " + PREFIX_CONTACT_EMAIL.getPrefix() + " " + ORDER_KEYWORD;
        SortCommand expectedSortCommand = new SortCommand(PREFIX_CONTACT_EMAIL, true);
        assertParseSuccess(sortCommandParser, args, expectedSortCommand);
    }

    @Test
    public void parse_noInputs_failure() {
        String args = " ";
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidAttribute_failure() {
        String args = " " + PREFIX_CONTACT_ADDRESS;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonExistentAttribute_failure() {
        String args = " " + PREFIX_SALE_QUANTITY;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noAttributeAndDesc_failure() {
        String args = " " + ORDER_KEYWORD;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_twoAttributes_failure() {
        String args = " " + PREFIX_CONTACT_EMAIL + " " + PREFIX_CONTACT_NAME;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_twoAttributesAndDesc_failure() {
        String args = " " + PREFIX_CONTACT_EMAIL + " " + PREFIX_CONTACT_NAME
                + " " + ORDER_KEYWORD;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAttributeAndNotDesc_failure() {
        String args = " " + PREFIX_CONTACT_EMAIL + " " + "lol";
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validAttributeAndDESC_failure() {
        String args = " " + PREFIX_CONTACT_EMAIL + " " + ORDER_KEYWORD.toUpperCase();
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
