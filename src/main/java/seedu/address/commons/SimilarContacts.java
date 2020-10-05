package seedu.address.commons;

import seedu.address.model.person.Person;

public class SimilarContacts extends SimilarItems<Person> {

    public SimilarContacts(String searchKeyword, double similarityThreshold) {
        super(searchKeyword, similarityThreshold);
    }

    public SimilarContacts(String searchKeyword) {
        super(searchKeyword);
    }

    @Override
    String getAttribute(Person person) {
        return person.getName().fullName;
    }
}

