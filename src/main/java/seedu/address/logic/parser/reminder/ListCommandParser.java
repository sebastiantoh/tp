package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_STATUS;

import seedu.address.logic.commands.reminder.ListCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindCommand object for Reminder
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String args) throws ParseException {
        if (args == null) {
            return new ListCommand(null);
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMINDER_STATUS);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        try {
            Boolean completed;
            if (argMultimap.getValue(PREFIX_REMINDER_STATUS).isPresent()) {
                completed = ParserUtil.parseCompletionStatus(argMultimap.getValue(PREFIX_REMINDER_STATUS).get());
            } else {
                completed = null;
            }

            return new ListCommand(completed);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
