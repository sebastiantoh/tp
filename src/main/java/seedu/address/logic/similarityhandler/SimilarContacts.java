package seedu.address.logic.similarityhandler;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.Person;

/**
 * Represents a class to find similar contacts.
 */
public class SimilarContacts extends SimilarItems<Person> {

    public SimilarContacts(String searchKeyword, double similarityThreshold) {
        super(searchKeyword, similarityThreshold);
    }

    public SimilarContacts(String searchKeyword) {
        super(searchKeyword);
    }

    @Override
    String getAttributeAsStr(Person person) {
        requireNonNull(person);
        return person.getName().fullName;
    }
}

