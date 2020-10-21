package seedu.address.logic.parser.sale;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALE_CONTACT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;

import java.time.Month;
import java.time.Year;
import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sale.AllListCommand;
import seedu.address.logic.commands.sale.ListCommand;
import seedu.address.logic.commands.sale.MonthlyListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object for Sale.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SALE_CONTACT_INDEX,
                PREFIX_MONTH, PREFIX_YEAR);

        if (!argMultimap.getPreamble().isEmpty()
                || (!isMonthlyListCommand(argMultimap)
                    && !isShowAllListCommand(argMultimap)
                    && !isShowSpecificContactListCommand(argMultimap))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        if (isMonthlyListCommand(argMultimap)) {
            Month month = ParserUtil.parseMonth(argMultimap.getValue(PREFIX_MONTH).get());
            Year year = ParserUtil.parseYear(argMultimap.getValue(PREFIX_YEAR).get());
            return new MonthlyListCommand(month, year);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), pe);
        } catch (NoSuchElementException ne) {
            return new AllListCommand(true, null);
        }

        return new AllListCommand(false, index);
    }

    private boolean isMonthlyListCommand(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_MONTH).isPresent()
                && argumentMultimap.getValue(PREFIX_YEAR).isPresent()
                && argumentMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).isEmpty();
    }

    private boolean isShowAllListCommand(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_MONTH).isEmpty()
                && argumentMultimap.getValue(PREFIX_YEAR).isEmpty()
                && argumentMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).isEmpty();
    }

    private boolean isShowSpecificContactListCommand(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_MONTH).isEmpty()
                && argumentMultimap.getValue(PREFIX_YEAR).isEmpty()
                && argumentMultimap.getValue(PREFIX_SALE_CONTACT_INDEX).isPresent();
    }
}
