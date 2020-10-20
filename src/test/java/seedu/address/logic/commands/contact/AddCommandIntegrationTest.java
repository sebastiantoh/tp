package seedu.address.logic.commands.contact;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.person.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().withId(10).build();
        AddCommand expectedCommand = new AddCommand(PersonBuilder.DEFAULT_NAME, PersonBuilder.DEFAULT_PHONE,
                PersonBuilder.DEFAULT_EMAIL, PersonBuilder.DEFAULT_ADDRESS,
                new HashSet<>(), PersonBuilder.DEFAULT_REMARK);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(expectedCommand, model,
            String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        AddCommand expectedCommand = new AddCommand(personInList.getName(), personInList.getPhone(),
                personInList.getEmail(), personInList.getAddress(),
                personInList.getTags(), personInList.getRemark());
        assertCommandFailure(expectedCommand, model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
