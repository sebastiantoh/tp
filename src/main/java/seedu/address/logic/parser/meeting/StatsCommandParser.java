package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;

import seedu.address.logic.commands.meeting.StatsCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StatsCommand object for Meeting.
 */
public class StatsCommandParser implements Parser<StatsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns a StatsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public StatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MONTH, PREFIX_YEAR);

        if (argMultimap.getValue(PREFIX_MONTH).isPresent() && argMultimap.getValue(PREFIX_YEAR).isEmpty()
                || (argMultimap.getValue(PREFIX_MONTH).isEmpty() && argMultimap.getValue(PREFIX_YEAR).isPresent())
                || (!argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_MONTH).isPresent()
                    && argMultimap.getValue(PREFIX_YEAR).isPresent())) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StatsCommand.MESSAGE_USAGE));
        }
        Month month = null;
        Year year = null;
        Integer numberOfMonths = null;
        if (argMultimap.getValue(PREFIX_MONTH).isPresent() && argMultimap.getValue(PREFIX_YEAR).isPresent()) {
            month = ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get());
            year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
        } else if (argMultimap.getPreamble().isEmpty()) {
            month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
            year = Year.now();
        } else {
            month = LocalDate.now(ZoneId.of("Asia/Singapore")).getMonth();
            year = Year.now();
            numberOfMonths = ParserUtil.parseNumberOfMonths(argMultimap.getPreamble());
        }
        return new StatsCommand(month, year, numberOfMonths);
    }
}
