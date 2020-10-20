package seedu.address.model;

import static seedu.address.testutil.person.TypicalPersons.ALICE;
import static seedu.address.testutil.person.TypicalPersons.BOB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * A Model stub that has a sortedPersonList containing {@code TypicalPerson#BOB} at the first index (one-based), and
 * {@code TypicalPerson#ALICE} at the second index.
 */
public class ModelStubWithSortedPersonList extends ModelStub {
    @Override
    public ObservableList<Person> getSortedPersonList() {
        ObservableList<Person> sortedPersonList = FXCollections.observableArrayList();
        sortedPersonList.add(BOB);
        sortedPersonList.add(ALICE);
        return sortedPersonList;
    }
}
