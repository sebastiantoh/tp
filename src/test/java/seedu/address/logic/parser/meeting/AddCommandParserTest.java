package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_ONE_HOUR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION;
import static seedu.address.logic.commands.CommandTestUtil.MESSAGE_CALL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_ONE_HOUR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MESSAGE_CALL_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalDates.TYPICAL_DATE_1;
import static seedu.address.testutil.TypicalDurations.TYPICAL_DURATION_ONE_HOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.AddCommand;
import seedu.address.model.Message;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + DATE_1 + DURATION_ONE_HOUR;

        AddCommand expectedCommand =
                new AddCommand(INDEX_SECOND_ITEM, new Message(VALID_MESSAGE_CALL_AMY), TYPICAL_DATE_1,
                        TYPICAL_DURATION_ONE_HOUR);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing contact prefix
        assertParseFailure(parser, INDEX_SECOND_ITEM.getOneBased() + MESSAGE_CALL_AMY + DATE_1, expectedMessage);

        // missing message prefix
        assertParseFailure(parser,
                CONTACT_INDEX_SECOND + VALID_MESSAGE_CALL_AMY + DATE_1 + DURATION_ONE_HOUR,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser,
                CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + VALID_DATE_1 + DURATION_ONE_HOUR,
                expectedMessage);

        // missing duration prefix
        assertParseFailure(parser,
                CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + DATE_1 + VALID_DURATION_ONE_HOUR,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                INDEX_SECOND_ITEM.getOneBased() + VALID_MESSAGE_CALL_AMY + VALID_DATE_1
                        + VALID_DURATION_ONE_HOUR,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser,
                INVALID_CONTACT_INDEX + MESSAGE_CALL_AMY + DATE_1 + DURATION_ONE_HOUR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // invalid message
        assertParseFailure(parser, INVALID_CONTACT_INDEX + " " + PREFIX_MESSAGE + "" + DATE_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // invalid date
        assertParseFailure(parser,
                CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + INVALID_DATE + DURATION_ONE_HOUR,
                MESSAGE_INVALID_DATETIME);
        assertParseFailure(parser,
                CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + " " + PREFIX_DATETIME + "" + DURATION_ONE_HOUR,
                MESSAGE_INVALID_DATETIME);

        // invalid duration
        assertParseFailure(parser,
                CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + DATE_1 + INVALID_DURATION,
                MESSAGE_INVALID_DURATION);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + CONTACT_INDEX_SECOND + MESSAGE_CALL_AMY + DATE_1 + DURATION_ONE_HOUR,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
