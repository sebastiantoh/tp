package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_THIRD;
import static seedu.address.logic.commands.CommandTestUtil.DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.MESSAGE_CALL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MESSAGE_CALL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MESSAGE_CALL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MESSAGE_CALL_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.reminder.EditCommand;
import seedu.address.logic.commands.reminder.EditCommand.EditReminderDescriptor;
import seedu.address.model.Message;
import seedu.address.testutil.TypicalDates;
import seedu.address.testutil.reminder.EditReminderDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, DATE_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DATE_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DATE_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_CONTACT_INDEX, MESSAGE_INVALID_INDEX); // invalid contact index
        assertParseFailure(parser, "1" + " " + PREFIX_MESSAGE + "",
                Message.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DATE, MESSAGE_INVALID_DATETIME); // invalid date
        assertParseFailure(parser, "1" + CONTACT_INDEX_SECOND + " " + PREFIX_DATETIME + "", MESSAGE_INVALID_DATETIME);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_CONTACT_INDEX + INVALID_DATE + MESSAGE_CALL_AMY,
                MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput =
                targetIndex.getOneBased() + CONTACT_INDEX_SECOND + DATE_1 + MESSAGE_CALL_AMY;

        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder()
                        .withContactIndex(INDEX_SECOND_ITEM)
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_1)
                        .withMessage(VALID_MESSAGE_CALL_AMY)
                        .build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_ITEM;
        String userInput =
                targetIndex.getOneBased() + DATE_1 + MESSAGE_CALL_AMY;

        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder()
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_1)
                        .withMessage(VALID_MESSAGE_CALL_AMY)
                        .build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_ITEM;

        // contact index
        String userInput = targetIndex.getOneBased() + CONTACT_INDEX_SECOND;
        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder()
                        .withContactIndex(INDEX_SECOND_ITEM)
                        .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // message
        userInput = targetIndex.getOneBased() + MESSAGE_CALL_AMY;
        descriptor =
                new EditReminderDescriptorBuilder()
                        .withMessage(VALID_MESSAGE_CALL_AMY)
                        .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // scheduledDate
        userInput = targetIndex.getOneBased() + DATE_1;
        descriptor =
                new EditReminderDescriptorBuilder()
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_1)
                        .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + MESSAGE_CALL_AMY + CONTACT_INDEX_SECOND + MESSAGE_CALL_BOB
                + CONTACT_INDEX_THIRD + DATE_1 + DATE_2;

        EditReminderDescriptor descriptor = new EditReminderDescriptorBuilder()
                .withContactIndex(INDEX_THIRD_ITEM)
                .withMessage(VALID_MESSAGE_CALL_BOB)
                .withScheduledDate(TypicalDates.TYPICAL_DATE_2)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_ITEM;
        String userInput = targetIndex.getOneBased() + INVALID_DATE + DATE_1;
        EditReminderDescriptor descriptor =
                new EditReminderDescriptorBuilder()
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_1)
                        .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_DATE + DATE_1 + MESSAGE_CALL_AMY;
        descriptor =
                new EditReminderDescriptorBuilder()
                        .withScheduledDate(TypicalDates.TYPICAL_DATE_1)
                        .withMessage(VALID_MESSAGE_CALL_AMY)
                        .build();

        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
