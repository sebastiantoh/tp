package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.Month;
import java.time.Year;

import seedu.address.logic.commands.meeting.MultipleMeetingStatsCommand;
import seedu.address.logic.commands.meeting.SingleMeetingStatsCommand;
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

        if (!isCustomSingleMeetingCommand(argMultimap)
                && !isDefaultSingleMeetingCommand(argMultimap)
                && !isMultipleMeetingCommand(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StatsCommand.MESSAGE_USAGE));
        }

        if (isMultipleMeetingCommand(argMultimap)) {
            int numberOfMonths = ParserUtil.parseNumberOfMonths(argMultimap.getPreamble());
            return new MultipleMeetingStatsCommand(numberOfMonths);
        }

        if (isCustomSingleMeetingCommand(argMultimap)) {
            Month month = ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get());
            Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
            return new SingleMeetingStatsCommand(month, year);
        }

        if (isDefaultSingleMeetingCommand(argMultimap)) {
            return new SingleMeetingStatsCommand();
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
    }

    private boolean isCustomSingleMeetingCommand(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_MONTH).isPresent()
                && argMultimap.getValue(PREFIX_YEAR).isPresent()
                && argMultimap.getPreamble().isEmpty();
    }

    private boolean isDefaultSingleMeetingCommand(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_MONTH).isEmpty()
                && argMultimap.getValue(PREFIX_YEAR).isEmpty()
                && argMultimap.getPreamble().isEmpty();
    }

    private boolean isMultipleMeetingCommand(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_MONTH).isEmpty()
                && argMultimap.getValue(PREFIX_YEAR).isEmpty()
                && !argMultimap.getPreamble().isEmpty();
    }
}
