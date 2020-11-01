package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Integer id;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;
    private final boolean archived;

    /**
     * Creates a person object with specified details.
     * Every field must be present and not null.
     * @param name Name of the person.
     * @param phone Phone number of the person.
     * @param email Email Address of the person.
     * @param address Address of the person.
     * @param tags Set of tags associated with the person.
     * @param remark Remark associated with the person.
     */
    public Person(Integer id, Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Remark remark, boolean archived) {
        requireAllNonNull(name, phone, email, address, tags, remark);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
        this.archived = archived;
    }

    public Integer getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Remark getRemark() {
        return remark;
    }

    public boolean isArchived() {
        return archived;
    }

    /**
     * Removes a tag from the tag set, no operation will be performed if the tag does not exist in the tag set.
     */
    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public boolean hasSameId(Person otherPerson) {
        return this.id.equals(otherPerson.id);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person) || other == null) {
            return false;
        }

        Person otherPerson = (Person) other;

        return otherPerson.getId().equals(getId())
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getRemark().equals(getRemark());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, remark);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());

        if (getTags().size() > 0) {
            builder.append(" Tags: ");
            getTags().forEach(builder::append);
        }

        if (!getRemark().isEmpty()) {
            builder.append(" Remark: ").append(getRemark());
        }

        return builder.toString();
    }

}
