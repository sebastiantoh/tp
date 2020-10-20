package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.PARSED_VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PARSED_VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PARSED_VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PARSED_VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PARSED_VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PARSED_VALID_TAG_FRIEND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.person.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullFields_throwsNullPointerException() {
        Set<Tag> singleTagList = new HashSet<>();
        singleTagList.add(PARSED_VALID_TAG_FRIEND);

        assertThrows(NullPointerException.class, () -> new AddCommand(null, PARSED_VALID_PHONE_BOB,
                PARSED_VALID_EMAIL_BOB, PARSED_VALID_ADDRESS_BOB, singleTagList, PARSED_VALID_REMARK_BOB));
        assertThrows(NullPointerException.class, () -> new AddCommand(PARSED_VALID_NAME_BOB, null,
                PARSED_VALID_EMAIL_BOB, PARSED_VALID_ADDRESS_BOB, singleTagList, PARSED_VALID_REMARK_BOB));
        assertThrows(NullPointerException.class, () -> new AddCommand(PARSED_VALID_NAME_BOB, PARSED_VALID_PHONE_BOB,
                null, PARSED_VALID_ADDRESS_BOB, singleTagList, PARSED_VALID_REMARK_BOB));
        assertThrows(NullPointerException.class, () -> new AddCommand(PARSED_VALID_NAME_BOB, PARSED_VALID_PHONE_BOB,
                PARSED_VALID_EMAIL_BOB, null, singleTagList, PARSED_VALID_REMARK_BOB));
        assertThrows(NullPointerException.class, () -> new AddCommand(PARSED_VALID_NAME_BOB, PARSED_VALID_PHONE_BOB,
                PARSED_VALID_EMAIL_BOB, PARSED_VALID_ADDRESS_BOB, null, PARSED_VALID_REMARK_BOB));
        assertThrows(NullPointerException.class, () -> new AddCommand(PARSED_VALID_NAME_BOB, PARSED_VALID_PHONE_BOB,
                PARSED_VALID_EMAIL_BOB, PARSED_VALID_ADDRESS_BOB, singleTagList, null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().withId(1).build();

        AddCommand addCommand = new AddCommand(PersonBuilder.DEFAULT_NAME, PersonBuilder.DEFAULT_PHONE,
                PersonBuilder.DEFAULT_EMAIL, PersonBuilder.DEFAULT_ADDRESS,
                new HashSet<>(), PersonBuilder.DEFAULT_REMARK);

        CommandResult commandResult = addCommand.execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(PersonBuilder.DEFAULT_NAME, PersonBuilder.DEFAULT_PHONE,
                PersonBuilder.DEFAULT_EMAIL, PersonBuilder.DEFAULT_ADDRESS,
                new HashSet<>(), PersonBuilder.DEFAULT_REMARK);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddCommand addAliceCommand = new AddCommand(new Name("Alice"), PersonBuilder.DEFAULT_PHONE,
                PersonBuilder.DEFAULT_EMAIL, PersonBuilder.DEFAULT_ADDRESS,
                new HashSet<>(), PersonBuilder.DEFAULT_REMARK);
        AddCommand addBobCommand = new AddCommand(new Name("Bob"), PersonBuilder.DEFAULT_PHONE,
                PersonBuilder.DEFAULT_EMAIL, PersonBuilder.DEFAULT_ADDRESS,
                new HashSet<>(), PersonBuilder.DEFAULT_REMARK);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(new Name("Alice"), PersonBuilder.DEFAULT_PHONE,
                PersonBuilder.DEFAULT_EMAIL, PersonBuilder.DEFAULT_ADDRESS,
                new HashSet<>(), PersonBuilder.DEFAULT_REMARK);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
