package seedu.address.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.util.StringUtil;

public abstract class SimilarItems<T> {

    private static final double NOT_FOUND = -1;

    private final Map<T, Double> similarityMapper;

    private final String[] searchKeywords;

    private final List<T> list;

    private double similarityThreshold = 0.5;

    public SimilarItems(String[] searchKeywords, List<T> list) {
        this.searchKeywords = searchKeywords;
        this.list = list;
        this.similarityMapper = new HashMap<>();
    }

    public boolean isInSimilarityMapper(T item) {
        return this.similarityMapper.containsKey(item);
    }

    public Double getFromSimilarityMatrix(T item) {
        return this.similarityMapper.getOrDefault(item, NOT_FOUND);
    }


    public void fillSimilarityMapper() {
        for (T p : list) {
            String[] components = getAttribute(p).split("\\s+");

            for (String searchKeyword : searchKeywords) {
                for (String comp : components) {
                    double score = StringUtil.calculateSimilarityRatio(searchKeyword.toLowerCase(), comp.toLowerCase());
                    if (score >= this.similarityThreshold) {
                        this.similarityMapper.put(p, score);
                        break;
                    }
                }
            }
        }
    }

    public void setSimilarityThreshold(double similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }

    abstract String getAttribute(T p);

}
