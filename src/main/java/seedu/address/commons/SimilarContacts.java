package seedu.address.commons;

import java.util.List;

import seedu.address.model.person.Person;

public class SimilarContacts extends SimilarItems<Person> {

    public SimilarContacts(String[] searchKeywords, List<Person> list) {
        super(searchKeywords, list);
    }

    @Override
    String getAttribute(Person person) {
        return person.getName().fullName;
    }
}

