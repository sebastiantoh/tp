package seedu.address.logic.parser.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.EditCommand;
import seedu.address.logic.commands.meeting.EditCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object for Meeting
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand and returns an EditCommand
     * object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CONTACT,
                PREFIX_MESSAGE,
                PREFIX_DATETIME,
                PREFIX_DURATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            editMeetingDescriptor.setContactIndex(ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT).get()));
        }
        if (argMultimap.getValue(PREFIX_MESSAGE).isPresent()) {
            editMeetingDescriptor.setMessage(ParserUtil.parseMessage(argMultimap.getValue(PREFIX_MESSAGE).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editMeetingDescriptor
                    .setStartDate(ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editMeetingDescriptor.setDuration(ParserUtil
                    .parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editMeetingDescriptor);
    }
}
