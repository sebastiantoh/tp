package seedu.address.logic.parser.tag;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
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
        assertParseSuccess(parser, String.format(" %s %s%s", PREFIX_CONTACT, PREFIX_TAG, MINIONS),
                new AddCommand(contactTagToAdd, true));
        // Parses input to add a sales tag
        assertParseSuccess(parser, String.format(" %s %s%s", PREFIX_SALE, PREFIX_TAG, BANANAS),
                new AddCommand(salesTagToAdd, false));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, String.format(" %s%s", PREFIX_TAG, MINIONS),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
