package seedu.address.commons;

import java.util.List;

public class SimilarCommandWords extends SimilarItems<String> {

    public SimilarCommandWords(String searchKeyword, double similarityThreshold) {
        super(searchKeyword, similarityThreshold);
    }

    public SimilarCommandWords(String searchKeyword) {
        super(searchKeyword);
    }

    @Override
    String getAttributeAsStr(String item) {
        return item;
    }
}
