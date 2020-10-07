package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "contact add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_CONTACT_NAME + "NAME "
            + PREFIX_CONTACT_PHONE + "PHONE "
            + PREFIX_CONTACT_EMAIL + "EMAIL "
            + PREFIX_CONTACT_ADDRESS + "ADDRESS "
            + "[" + PREFIX_CONTACT_TAG + "TAG]... "
            + "[" + PREFIX_CONTACT_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT_NAME + "John Doe "
            + PREFIX_CONTACT_PHONE + "98765432 "
            + PREFIX_CONTACT_EMAIL + "johnd@example.com "
            + PREFIX_CONTACT_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_CONTACT_TAG + "friends "
            + PREFIX_CONTACT_TAG + "owesMoney "
            + PREFIX_CONTACT_REMARK + "Owes me $10";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}