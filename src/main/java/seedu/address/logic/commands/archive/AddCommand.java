package seedu.address.logic.commands.archive;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "archive add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Moves a contact to the archive. "
            + "Note that the sales, meetings or reminders linked to this contact will not be deleted.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_PERSON_SUCCESS = "Archived person: %1$s.";

    private final Index targetIndex;

    public AddCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getSortedPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToArchive = lastShownList.get(targetIndex.getZeroBased());

        if (personToArchive.isArchived()) {
            throw new CommandException(Messages.MESSAGE_ARCHIVE_INVALID_LIST);
        }

        assert !personToArchive.isArchived();

        Person archivedPerson = new Person(
                personToArchive.getId(),
                personToArchive.getName(),
                personToArchive.getPhone(),
                personToArchive.getEmail(),
                personToArchive.getAddress(),
                personToArchive.getTags(),
                personToArchive.getRemark(),
                !personToArchive.isArchived()
        );
        model.setPerson(personToArchive, archivedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_UNARCHIVED_PERSONS);

        return new CommandResult(String.format(MESSAGE_ARCHIVE_PERSON_SUCCESS, archivedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof AddCommand
                && this.targetIndex.equals(((AddCommand) other).targetIndex);
    }

}
