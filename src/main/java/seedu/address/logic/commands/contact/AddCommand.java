package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

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
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_CONTACT_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTACT_NAME + "John Doe "
            + PREFIX_CONTACT_PHONE + "98765432 "
            + PREFIX_CONTACT_EMAIL + "johnd@example.com "
            + PREFIX_CONTACT_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_CONTACT_REMARK + "Owes me $10";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Tag> tagList;
    private final Remark remark;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Name name, Phone phone, Email email, Address address, Set<Tag> tagList, Remark remark) {
        requireAllNonNull(name, phone, email, address, tagList, remark);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tagList = tagList;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int id = model.getLatestContactId() + 1;

        Person toAdd = new Person(id, name, phone, email, address, tagList, remark, false);

        if (!model.contactTagsExist(toAdd)) {
            throw new CommandException(Messages.MESSAGE_CONTACT_TAGS_NOT_FOUND);
        }

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
                && name.equals(((AddCommand) other).name)
                && phone.equals(((AddCommand) other).phone)
                && email.equals(((AddCommand) other).email)
                && address.equals(((AddCommand) other).address)
                && tagList.equals(((AddCommand) other).tagList)
                && remark.equals(((AddCommand) other).remark));
    }
}
