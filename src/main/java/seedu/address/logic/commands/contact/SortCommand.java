package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the contacts by a particular contact attribute
 * in non-ascending or non-descending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "contact sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the contacts."
            + "valid arguments: n/ for name, e/ for email address\n"
            + "Parameters: KEYWORD [ORDER]\n"
            + "Example: contact sort n/ desc";

    public static final String MESSAGE_SUCCESS = "sorted!";

    public static final String MESSAGE_SORTING_ATTRIBUTE_INVALID =
            "The given attribute to sort by is not valid.";

    private final Prefix sortingAttribute;

    private final boolean isDesc;

    private Comparator<Person> comparator;

    /**
     * Creates a SortCommand to sort the contacts by {@code sortingAttribute}
     * and the sort order is determined by {@code isDesc}.
     *
     */
    public SortCommand(Prefix sortingAttribute, boolean isDesc) {
        requireNonNull(sortingAttribute);
        this.sortingAttribute = sortingAttribute;
        this.isDesc = isDesc;
    }

    /**
     * Sorts the contacts based on the given attribute,
     * in non-ascending order if isDesc is true,
     * in non-descending order otherwise.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the success message of the SortCommand.
     * @throws CommandException if sorting attribute is not valid.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (sortingAttribute.equals(PREFIX_CONTACT_NAME)) {
            this.comparator = Comparator.comparing(person -> person.getName().fullName.toLowerCase());
        } else if (sortingAttribute.equals(PREFIX_CONTACT_EMAIL)) {
            this.comparator = Comparator.comparing(person -> person.getEmail().value.toLowerCase());
        } else {
            throw new CommandException(MESSAGE_SORTING_ATTRIBUTE_INVALID);
        }

        this.sortByAttribute(model);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void sortByAttribute(Model model) {
        requireNonNull(this.comparator);
        if (!this.isDesc) {
            model.updateSortedPersonList(this.comparator);
        } else {
            model.updateSortedPersonList(this.comparator.reversed());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortingAttribute.equals(((SortCommand) other).sortingAttribute)
                && isDesc == ((SortCommand) other).isDesc);
    }
}
