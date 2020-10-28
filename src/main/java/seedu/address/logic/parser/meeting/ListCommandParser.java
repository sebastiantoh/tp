package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHOW_ALL;

import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.commands.meeting.ListCommand.ListMeetingDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object for Meeting.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CONTACT, PREFIX_SHOW_ALL);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        ListMeetingDescriptor listMeetingDescriptor = new ListMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            listMeetingDescriptor.setContactIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_SHOW_ALL).isPresent()) {
            boolean isValidFormat = argMultimap.getValue(PREFIX_SHOW_ALL).get().length() == 0;

            // To account for the case where user types in something like a/xxx
            if (!isValidFormat) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
            }

            listMeetingDescriptor.setShouldShowAll(true);
        }

        return new ListCommand(listMeetingDescriptor);
    }
}
