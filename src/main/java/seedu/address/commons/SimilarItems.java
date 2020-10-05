package seedu.address.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.util.StringUtil;

public abstract class SimilarItems<T> {

    private static final double NOT_FOUND = -1;

    private static final double EXACT_MATCH = 2;

    private final Map<T, Double> similarityMapper;

    private final String exactSearchKeyword;
    private final String[] searchKeywordComponents;

    private final List<T> list;

    private double similarityThreshold = 0.5;

    public SimilarItems(String searchKeyword, List<T> list) {
        this.exactSearchKeyword = searchKeyword;
        this.searchKeywordComponents = this.exactSearchKeyword.split("\\s+");
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

            if (this.getAttribute(p).equalsIgnoreCase(this.exactSearchKeyword)) {
                this.similarityMapper.put(p, EXACT_MATCH);
                break;
            }

            String[] components = this.getAttribute(p).split("\\s+");

            for (String searchKeyword : this.searchKeywordComponents) {
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
