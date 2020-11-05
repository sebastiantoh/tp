package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.testAllCommandWords;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.archive.ArchiveCommandsParser;
import seedu.address.logic.parser.contact.ContactCommandsParser;
import seedu.address.logic.parser.meeting.MeetingCommandsParser;
import seedu.address.logic.parser.reminder.ReminderCommandsParser;
import seedu.address.logic.parser.sale.SaleCommandsParser;
import seedu.address.logic.parser.tag.TagCommandsParser;

public class UnknownCommandTest {

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnknownCommand(null));
    }

    @Test
    public void execute_noSimilar_success() {
        //No similar command word to single word input
        UnknownCommand unknownCommand = new UnknownCommand("asciajsocaisj");
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNKNOWN_COMMAND);
        assertEquals(expectedCommandResult, unknownCommand.execute(null));

        //No similar command word to double word input
        unknownCommand = new UnknownCommand("cont ljoaisjcoiacjk");
        expectedCommandResult = new CommandResult(MESSAGE_UNKNOWN_COMMAND);
        assertEquals(expectedCommandResult, unknownCommand.execute(null));
    }

    @Test
    public void execute_similarContactCommandWords_success() {
        testAllCommandWords(ContactCommandsParser.ALL_CONTACT_COMMAND_WORDS);
    }

    @Test
    public void execute_similarArchiveCommandWords_success() {
        testAllCommandWords(ArchiveCommandsParser.ALL_ARCHIVE_COMMAND_WORDS);
    }

    @Test
    public void execute_similarTagCommandWords_success() {
        testAllCommandWords(TagCommandsParser.ALL_TAG_COMMAND_WORDS);
    }

    @Test
    public void execute_similarMeetingCommandWords_success() {
        testAllCommandWords(MeetingCommandsParser.ALL_MEETING_COMMAND_WORDS);
    }

    @Test
    public void execute_similarReminderCommandWords_success() {
        testAllCommandWords(ReminderCommandsParser.ALL_REMINDER_COMMAND_WORDS);
    }

    @Test
    public void execute_similarSaleCommandWords_success() {
        testAllCommandWords(SaleCommandsParser.ALL_SALE_COMMAND_WORDS);
    }

    @Test
    public void execute_similarMiscellaneousCommandWords_success() {
        testAllCommandWords(Arrays.asList(ClearCommand.COMMAND_WORD,
                ExitCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, PurgeCommand.COMMAND_WORD));
    }
}
