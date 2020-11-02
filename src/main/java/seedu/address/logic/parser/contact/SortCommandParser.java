package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC_ORDER;

import seedu.address.logic.commands.contact.SortCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object for Contact.
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code args} in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL, PREFIX_DESC_ORDER);

        Prefix sortingAttribute = null;

        int presentSortingAttributeCounter = 0;
        if (argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()) {
            sortingAttribute = PREFIX_CONTACT_NAME;
            presentSortingAttributeCounter++;
        }
        if (argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent()) {
            sortingAttribute = PREFIX_CONTACT_EMAIL;
            presentSortingAttributeCounter++;
        }

        if (presentSortingAttributeCounter != 1 || !argMultimap.getPreamble().isEmpty()
            || this.hasArgValueForAnyPresentPrefixes(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        boolean isSecondArgumentDesc = argMultimap.getValue(PREFIX_DESC_ORDER).isPresent();

        return new SortCommand(sortingAttribute, isSecondArgumentDesc);
    }

    private boolean hasArgValueForAnyPresentPrefixes(ArgumentMultimap argMultimap) {
        boolean hasArgValueForName = argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_CONTACT_NAME).get().isBlank();

        boolean hasArgValueForEmail = argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent()
                && !argMultimap.getValue(PREFIX_CONTACT_EMAIL).get().isBlank();

        boolean hasArgValueForDesc = argMultimap.getValue(PREFIX_DESC_ORDER).isPresent()
                && !argMultimap.getValue(PREFIX_DESC_ORDER).get().isBlank();

        return hasArgValueForName || hasArgValueForEmail || hasArgValueForDesc;
    }
}
