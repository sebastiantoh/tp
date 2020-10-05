package seedu.address.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a class to find similar items given a search keyword.
 * @param <T>
 */
public abstract class SimilarItems<T> {

    private static final double EXACT_MATCH = 2;

    private static final double NOT_FOUND = -1;

    private static final double DEFAULT_SIMILARITY_THRESHOLD = 0.5;

    private final String searchKeyword;

    private final Map<T, Double> similarityMapper;

    private final double similarityThreshold;

    public SimilarItems(String searchKeyword, double similarityThreshold) {
        this.searchKeyword = searchKeyword;
        this.similarityMapper = new HashMap<>();
        this.similarityThreshold = similarityThreshold;
    }

    public SimilarItems(String searchKeyword) {
        this(searchKeyword, DEFAULT_SIMILARITY_THRESHOLD);
    }

    /**
     * Returns true if {@code item} exists in the similarity mapper,
     * which means that {@code item} is similar to attribute searchKeyword.
     * @param item
     * @return boolean value indicating @{item}'s existence in the similarity mapper
     */
    public boolean isInSimilarityMapper(T item) {
        return this.similarityMapper.containsKey(item);
    }

    /**
     * Returns the value given the key {@code item} from the similarity matrix.
     * If {@code item} does not exist in similarity matrix,
     * the value NOT_FOUND is returned.
     *
     * @param item
     * @return the similarity ratio of {@code item} to the attribute search keyword
     */
    public Double getFromSimilarityMatrix(T item) {
        return this.similarityMapper.getOrDefault(item, NOT_FOUND);
    }

    /**
     * Considers every item in {@code list} and
     * fills the similarity mapper with entry <T, similarity ratio>
     * if T is an exact match for the attribute searchKeyword or
     * T is similar match for the attribute searchKeyword.
     *
     * @param list
     */
    public void fillSimilarityMapper(List<T> list) {
        String[] searchKeywordComponents = this.searchKeyword.split("\\s+");

        for (T item : list) {

            if (this.getAttribute(item).equalsIgnoreCase(this.searchKeyword)) {
                this.similarityMapper.put(item, EXACT_MATCH);
                break;
            }

            String[] components = this.getAttribute(item).split("\\s+");

            for (String searchKeyword : searchKeywordComponents) {
                for (String comp : components) {
                    fillSimilarityMapperIfSimilar(searchKeyword, comp, item);
                }
            }
        }
    }

    /**
     * Fills similarity matrix with entry <{@code item}, similarity ratio>
     * if the similarity ratio of {@code word} to {@code searchKeyword} is greater
     * than or equal to the attribute similarityThreshold.
     * If entry for {@code item} exists in the similarity matrix, the entry for {@code item} will
     * be updated with the larger similarity ratio.
     *
     * @param searchKeyword
     * @param word
     * @param item
     */
    private void fillSimilarityMapperIfSimilar(String searchKeyword, String word, T item) {
        double score = StringUtil.calculateSimilarityRatio(searchKeyword.toLowerCase(), word.toLowerCase());

        if (score >= this.similarityThreshold) {
            if (!this.similarityMapper.containsKey(item)) {
                this.similarityMapper.put(item, score);
            } else {
                double maxScore = Math.max(this.similarityMapper.get(item), score);
                this.similarityMapper.put(item, maxScore);
            }
        }
    }

    /**
     * Returns the attribute of T in String that
     * will be compared to attribute searchKeyword for similarity.
     *
     * @param item
     * @return attribute of T in String
     */
    abstract String getAttribute(T item);

}
