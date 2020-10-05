package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.SimilarContacts;
import seedu.address.commons.SimilarItems;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "contact find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        List<Person> list = model.getFilteredPersonList();

        SimilarItems<Person> similarItems = new SimilarContacts(this.arguments.split("\\s+"), list);
        similarItems.fillSimilarityMapper();

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
                && this.arguments.equals(((FindCommand) other).arguments)); // state check
    }
}
