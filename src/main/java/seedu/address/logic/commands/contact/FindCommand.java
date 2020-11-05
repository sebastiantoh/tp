package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.similarityhandler.SimilarContacts;
import seedu.address.logic.similarityhandler.SimilarItems;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name exactly matches the argument or
 * partially matches some space-delimited word in the argument.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "contact find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names exactly"
            + " matches the argument or partially matches some space-delimited word in the argument.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final String argument;

    public FindCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Finds all contacts whose names exactly matches the argument or
     * partially matches some space-delimited word in the argument.
     *
     * The filtered contact list is sorted by non-ascending similarity.
     * Contacts whose names exactly match the argument appear at the top of the list.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        List<Person> list = model.getFilteredPersonList();

        SimilarItems<Person> similarItems = new SimilarContacts(this.argument);
        similarItems.fillSimilarityMapper(list);

        model.updateFilteredPersonList(similarItems::isInSimilarityMapper);
        model.updateSortedPersonList((x, y) -> similarItems.getFromSimilarityMatrix(y)
                .compareTo(similarItems.getFromSimilarityMatrix(x)));

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && this.argument.equals(((FindCommand) other).argument)); // state check
    }
}
