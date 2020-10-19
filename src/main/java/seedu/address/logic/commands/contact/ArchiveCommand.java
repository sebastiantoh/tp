package seedu.address.logic.commands.contact;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_PERSONS;

public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "contact archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Moves a contact to the archive.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    
    public static final String MESSAGE_ARCHIVE_PERSON_SUCCESS = "Archived person: %1$s.";

    private final Index targetIndex;
    
    public ArchiveCommand(Index targetIndex) {
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
            throw new CommandException(Messages.MESSAGE_ARCHIVE_INVALIID_LIST);
        }

        Person archivedPerson = new Person(
                personToArchive.getName(),
                personToArchive.getPhone(),
                personToArchive.getEmail(),
                personToArchive.getAddress(),
                personToArchive.getTags(),
                personToArchive.getRemark(),
                true,
                personToArchive.getSalesList()
        );
        model.setPerson(personToArchive, archivedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_UNARCHIVED_PERSONS);

        return new CommandResult(String.format(MESSAGE_ARCHIVE_PERSON_SUCCESS, archivedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof ArchiveCommand
                && this.targetIndex.equals(((ArchiveCommand) other).targetIndex);
    }

}