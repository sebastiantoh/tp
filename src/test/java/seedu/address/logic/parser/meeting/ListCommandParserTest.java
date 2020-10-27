package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_INDEX_THIRD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHOW_ALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.commands.meeting.ListCommand.ListMeetingDescriptor;
import seedu.address.testutil.meeting.ListMeetingDescriptorBuilder;

public class ListCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // contact prefix present, but no index specified
        assertParseFailure(parser, PREFIX_CONTACT.getPrefix(), MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        // index passed as preamble
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_CONTACT_INDEX, MESSAGE_INVALID_INDEX); // invalid contact index
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = CONTACT_INDEX_SECOND + " " + PREFIX_SHOW_ALL;

        ListMeetingDescriptor descriptor =
                new ListMeetingDescriptorBuilder()
                        .withContactIndex(INDEX_SECOND_ITEM)
                        .withShouldShowAll(true)
                        .build();

        ListCommand expectedCommand = new ListCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String onlyContactIndex = CONTACT_INDEX_SECOND;

        ListMeetingDescriptor descriptorWithOnlyContact =
                new ListMeetingDescriptorBuilder()
                        .withContactIndex(INDEX_SECOND_ITEM)
                        .build();

        ListCommand expectedCommandOnlyContactIndex = new ListCommand(descriptorWithOnlyContact);

        assertParseSuccess(parser, onlyContactIndex, expectedCommandOnlyContactIndex);

        String onlyShowAll = " " + PREFIX_SHOW_ALL.getPrefix();

        ListMeetingDescriptor descriptorWithOnlyShowAll =
                new ListMeetingDescriptorBuilder()
                        .withShouldShowAll(true)
                        .build();

        ListCommand expectedCommandOnlyShowAll = new ListCommand(descriptorWithOnlyShowAll);

        assertParseSuccess(parser, onlyShowAll, expectedCommandOnlyShowAll);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = CONTACT_INDEX_SECOND + " " + PREFIX_SHOW_ALL + CONTACT_INDEX_THIRD;

        ListMeetingDescriptor descriptor = new ListMeetingDescriptorBuilder()
                .withContactIndex(INDEX_THIRD_ITEM)
                .withShouldShowAll(true)
                .build();
        ListCommand expectedCommand = new ListCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        String userInput = INVALID_CONTACT_INDEX + " " + PREFIX_SHOW_ALL + CONTACT_INDEX_THIRD;

        ListMeetingDescriptor descriptor = new ListMeetingDescriptorBuilder()
                .withContactIndex(INDEX_THIRD_ITEM)
                .withShouldShowAll(true)
                .build();
        ListCommand expectedCommand = new ListCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
