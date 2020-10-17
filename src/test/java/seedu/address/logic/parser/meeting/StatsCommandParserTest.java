package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MONTH;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_YEAR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meeting.StatsCommand;

public class StatsCommandParserTest {
    private final StatsCommandParser statsCommandParser = new StatsCommandParser();

    @Test
    public void parse_noArguments_success() {
        assertParseSuccess(statsCommandParser, "", new StatsCommand(
                LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth(), Year.now()));
    }

    @Test
    public void parse_validArguments_success() {
        assertParseSuccess(statsCommandParser, " m/10 y/2020", new StatsCommand(
                Month.of(10), Year.of(2020)));
    }

    @Test
    public void parse_monthOnly_failure() {
        assertParseFailure(statsCommandParser, " m/10",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_yearOnly_failure() {
        assertParseFailure(statsCommandParser, " y/10",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidMonth_failure() {
        assertParseFailure(statsCommandParser, " m/13 y/2020",
                MESSAGE_INVALID_MONTH);
    }

    @Test
    public void parse_invalidYear_failure() {
        assertParseFailure(statsCommandParser, " m/10 y/-1",
                MESSAGE_INVALID_YEAR);
    }

//    @Test
//    public void parse_emptyYear_failure() {
//        assertParseFailure(statsCommandParser, " m/10 y/ ",
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_emptyMonth_failure() {
//        assertParseFailure(statsCommandParser, " m/ y/2020",
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
//    }
//
//    @Test
//    public void parse_emptyYearAndMonth_failure() {
//        assertParseFailure(statsCommandParser, " m/ y/ ",
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
//    }
}