package seedu.address.testutil.person;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final BigDecimal DEFAULT_TOTAL_SALES_AMOUNT = new BigDecimal("0");
    public static final int DEFAULT_ID = 1;
    public static final Name DEFAULT_NAME = new Name("Alice Pauline");
    public static final Phone DEFAULT_PHONE = new Phone("85355255");
    public static final Email DEFAULT_EMAIL = new Email("alice@gmail.com");
    public static final Address DEFAULT_ADDRESS = new Address("123, Jurong West Ave 6, #08-111");
    public static final Remark DEFAULT_REMARK = new Remark("");

    private int id;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Remark remark;
    private boolean archived;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        id = DEFAULT_ID;
        name = DEFAULT_NAME;
        phone = DEFAULT_PHONE;
        email = DEFAULT_EMAIL;
        address = DEFAULT_ADDRESS;
        tags = new HashSet<>();
        remark = DEFAULT_REMARK;
        archived = false;
    }


    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        id = personToCopy.getId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        remark = personToCopy.getRemark();
        archived = personToCopy.isArchived();
    }

    /**
     * Sets the {@code id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Archived} of the {@code Person} that we are building to {@code true}.
     */
    public PersonBuilder withArchived() {
        this.archived = true;
        return this;
    }

    public Person build() {
        return new Person(id, name, phone, email, address, tags, remark, archived);
    }

}
