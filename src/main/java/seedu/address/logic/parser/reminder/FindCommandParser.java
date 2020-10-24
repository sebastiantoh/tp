package seedu.address.logic.parser.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_STATUS;

import seedu.address.logic.commands.reminder.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;

public class FindCommandParser implements Parser<FindCommand> {
    @Override
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMINDER_STATUS);

        try {
            Boolean completed;
            if (argMultimap.getValue(PREFIX_REMINDER_STATUS).isPresent()) {
                completed = ParserUtil.parseCompletionStatus(argMultimap.getValue(PREFIX_REMINDER_STATUS).get());
            } else {
                completed = false;
            }
            return new FindCommand(completed);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
    }
}
