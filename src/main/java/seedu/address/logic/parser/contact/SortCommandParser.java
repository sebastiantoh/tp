package seedu.address.logic.parser.contact;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;

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
            ArgumentTokenizer.tokenize(args, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL);

        boolean isNameInputPresent = argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent();
        boolean isEmailInputPresent = argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent();

        String[] argComponents = args.trim().split("\\s+" , 2);

        boolean isSecondArgumentPresent = (argComponents.length == 2);

        boolean isSecondArgumentDesc = false;

        if (isSecondArgumentPresent) {
            isSecondArgumentDesc = argComponents[1].trim().equals(ORDER_KEYWORD);
        }

        if ((isEmailInputPresent && isNameInputPresent)
                || (!isEmailInputPresent && !isNameInputPresent)
                || !argMultimap.getPreamble().isEmpty()
                || (isSecondArgumentPresent && !isSecondArgumentDesc)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Prefix sortingAttribute;

        if (isNameInputPresent) {
            sortingAttribute = PREFIX_CONTACT_NAME;
        } else {
            sortingAttribute = PREFIX_CONTACT_EMAIL;
        }

        return new SortCommand(sortingAttribute, isSecondArgumentDesc);
    }
}
