package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOTAL_SALES;

import seedu.address.logic.commands.contact.SortCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object for Contact
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String ORDER_KEYWORD = "desc";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL, PREFIX_TOTAL_SALES);

        Prefix sortingAttribute = null;

        int presentAttributeCounter = 0;

        if (argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()) {
            sortingAttribute = PREFIX_CONTACT_NAME;
            presentAttributeCounter++;
        }
        if (argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent()) {
            sortingAttribute = PREFIX_CONTACT_EMAIL;
            presentAttributeCounter++;
        }
        if (argMultimap.getValue(PREFIX_TOTAL_SALES).isPresent()) {
            sortingAttribute = PREFIX_TOTAL_SALES;
            presentAttributeCounter++;
        }

        String[] argComponents = args.trim().split("\\s+" , 2);

        boolean isSecondArgumentPresent = (argComponents.length == 2);

        boolean isSecondArgumentDesc = false;

        if (isSecondArgumentPresent) {
            isSecondArgumentDesc = argComponents[1].trim().equals(ORDER_KEYWORD);
        }

        if (presentAttributeCounter != 1 || !argMultimap.getPreamble().isEmpty()
                || (isSecondArgumentPresent && !isSecondArgumentDesc)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(sortingAttribute, isSecondArgumentDesc);
    }
}
