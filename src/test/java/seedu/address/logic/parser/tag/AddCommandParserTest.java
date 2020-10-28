package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALES_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tag.AddCommand;
import seedu.address.model.tag.Tag;

class AddCommandParserTest {

    private static final String BANANAS = "bananas";
    private static final String MINIONS = "minions";

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_validInput_returnsAddCommand() {
        Tag contactTagToAdd = new Tag(MINIONS);
        Tag salesTagToAdd = new Tag(BANANAS);
        // Parses input to add a contact tag
        assertParseSuccess(parser, String.format(" %s%s", PREFIX_CONTACT_TAG, MINIONS),
                new AddCommand(contactTagToAdd, true));
        // Parses input to add a sales tag
        assertParseSuccess(parser, String.format(" %s%s", PREFIX_SALES_TAG, BANANAS),
                new AddCommand(salesTagToAdd, false));
    }

    @Test
    public void parse_missingTagName_throwsParseException() {
        assertParseFailure(parser, String.format(" %s", PREFIX_CONTACT_TAG),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingType_throwsParseException() {
        assertParseFailure(parser, " " + MINIONS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
