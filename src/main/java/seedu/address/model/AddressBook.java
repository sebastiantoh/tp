package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueContactTagList;
import seedu.address.model.tag.UniqueSaleTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueContactTagList contactTags;
    private final UniqueSaleTagList saleTags;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        contactTags = new UniqueContactTagList();
        saleTags = new UniqueSaleTagList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the  contents of the tag list with {@code contactTags}.
     * {@code contactTags} must not contain duplicate contactTags.
     */
    public void setTags(List<Tag> contactTags) {
        this.contactTags.setTags(contactTags);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTags(newData.getContactTagList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        for (Tag t : p.getTags()) {
            contactTags.add(t);
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        for (Tag t : editedPerson.getTags()) {
            contactTags.add(t);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// tag-level operations

    /**
     * Returns true if a contact tag with the same identity as {@code tag} exists in StonksBook.
     */
    public boolean hasContactTag(Tag tag) {
        requireNonNull(tag);
        return contactTags.contains(tag);
    }

    /**
     * Returns true if a sale tag with the same identity as {@code tag} exists in StonksBOok.
     */
    public boolean hasSaleTag(Tag tag) {
        requireNonNull(tag);
        return saleTags.contains(tag);
    }

    /**
     * Adds the specified tag to StonksBook.
     * If the tag already exists in StonksBook, no action will be performed.
     */
    public void addContactTag(Tag tag) {
        contactTags.add(tag);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     */
    public void removeContactTag(Tag key) {
        contactTags.remove(key);
        persons.removeContactTag(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     */
    public void removeSaleTag(Tag key) {
        saleTags.remove(key);
        persons.removeSaleTag(key);
    }

    /**
     * Replaces the given {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the contact tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in StonksBook.
     */
    public void editContactTag(Tag target, Tag editedTag) {
        contactTags.setTag(target, editedTag);
        persons.setContactTag(target, editedTag);
    }

    /**
     * Replaces the given {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the sale tag list.
     * The tag identity of {@code editedTag} must not be the same as another existing tag in StonksBook.
     */
    public void editSaleTag(Tag target, Tag editedTag) {
        saleTags.setTag(target, editedTag);
        // TODO: edit sale tag once sale model is implemented
    }

    /**
     * List all the existing tags in StonksBook.
     */
    public String listTags() {
        // TODO: append sale tags once sale model is implemented
        return contactTags.asUnmodifiableObservableList().toString();
    }

    /**
     * Re-order all the existing tags in StonksBook.
     */
    public void sortTags() {
        contactTags.sort();
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getContactTagList() {
        return contactTags.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Tag> getSaleTagList() {
        return saleTags.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons))
                && contactTags.equals(((AddressBook) other).contactTags);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
