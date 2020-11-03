package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_QUANTITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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
        String args = " " + PREFIX_CONTACT_NAME.getPrefix() + " " + PREFIX_DESC_ORDER;
        SortCommand expectedSortCommand = new SortCommand(PREFIX_CONTACT_NAME, true);
        assertParseSuccess(sortCommandParser, args, expectedSortCommand);
    }

    @Test
    public void parse_emailAndDesc_success() {
        String args = " " + PREFIX_CONTACT_EMAIL.getPrefix() + " " + PREFIX_DESC_ORDER;
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
    public void parse_nonExistentContactAttribute_failure() {
        String args = " " + PREFIX_SALE_QUANTITY;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_descOnly_failure() {
        String args = " " + PREFIX_DESC_ORDER;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneAttribute_failure() {
        // email and name
        String args = " " + PREFIX_CONTACT_EMAIL + " " + PREFIX_CONTACT_NAME;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_twoAttributesAndDesc_failure() {
        String args = " " + PREFIX_CONTACT_EMAIL + " " + PREFIX_CONTACT_NAME
                + " " + PREFIX_DESC_ORDER;
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nameWithArgValue_failure() {
        String args = " " + PREFIX_CONTACT_NAME + " some arg value";
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emailWithArgValue_failure() {
        String args = " " + PREFIX_CONTACT_EMAIL + " some arg value";
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_descWithArgValue_failure() {
        String args = " " + PREFIX_CONTACT_NAME + " " + PREFIX_DESC_ORDER
                + " some arg value";
        assertParseFailure(sortCommandParser, args,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
