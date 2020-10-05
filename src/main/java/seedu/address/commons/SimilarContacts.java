package seedu.address.commons;

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
        return person.getName().fullName;
    }
}

