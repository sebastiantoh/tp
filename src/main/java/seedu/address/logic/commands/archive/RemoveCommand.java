package seedu.address.logic.commands.archive;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "archive remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a person from the archive without deleting the person.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_SUCCESS = "Removed Person from archive: %1$s. "
            + "This Person will appear on your contact list again.";

    private final Index targetIndex;

    public RemoveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getSortedPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToUnarchive = lastShownList.get(targetIndex.getZeroBased());

        if (!personToUnarchive.isArchived()) {
            throw new CommandException(Messages.MESSAGE_UNARCHIVE_INVALID_LIST);
        }

        assert personToUnarchive.isArchived();

        Person unarchivedPerson = new Person(
                personToUnarchive.getId(),
                personToUnarchive.getName(),
                personToUnarchive.getPhone(),
                personToUnarchive.getEmail(),
                personToUnarchive.getAddress(),
                personToUnarchive.getTags(),
                personToUnarchive.getRemark(),
                false
        );
        model.setPerson(personToUnarchive, unarchivedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ARCHIVED_PERSONS);

        return new CommandResult(String.format(MESSAGE_REMOVE_SUCCESS, unarchivedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveCommand // instanceof handles nulls
                && targetIndex.equals(((RemoveCommand) other).targetIndex)); // state check
    }
}
