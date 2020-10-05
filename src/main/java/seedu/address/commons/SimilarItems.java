package seedu.address.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    /**
     * Initializes a SimilarItems with the given searchKeyword and similarityThreshold.
     *
     * @param searchKeyword
     * @param similarityThreshold
     */
    public SimilarItems(String searchKeyword, double similarityThreshold) {
        this.searchKeyword = searchKeyword;
        this.similarityMapper = new HashMap<>();
        this.similarityThreshold = similarityThreshold;
    }

    /**
     * Initializes a SimilarItems with the given searchKeyword.
     *
     * @param searchKeyword
     */
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
     * fills the similarity mapper with entry < T, similarity ratio >
     * if T is an exact / similar match for the attribute searchKeyword.
     *
     * @param list
     */
    public void fillSimilarityMapper(List<T> list) {
        String[] searchKeywordComponents = this.searchKeyword.split("\\s+");

        for (T item : list) {
            Map.Entry<T, Double> entry = this.getSimilarity(item, searchKeywordComponents);
            if (!Objects.isNull(entry)) {
                this.similarityMapper.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Returns entry <{@code item}, similarity ratio> if
     * there is an exact / similar match of {@code item} to the attribute searchKeyword.
     *
     * Entry for {@code item} will have the largest similarity ratio.
     *
     * If no exact / similar match of {@code item}, returns null to indicate dissimilarity.
     *
     * @param item
     * @param searchKeywordComponents
     * @return
     */
    private Map.Entry<T, Double> getSimilarity(T item, String[] searchKeywordComponents) {
        if (this.getAttribute(item).equalsIgnoreCase(this.searchKeyword)) {
            return Map.entry(item, EXACT_MATCH);
        }

        String[] components = this.getAttribute(item).split("\\s+");

        double maxScore = -1;

        for (String searchKeyword : searchKeywordComponents) {
            for (String word : components) {
                double score = this.getSimilarityRatio(searchKeyword, word);
                maxScore = Math.max(maxScore, score);
            }
        }

        if (maxScore == -1) {
            return null;
        }

        return Map.entry(item, maxScore);
    }


    /**
     * Returns similarity ratio for word and searchKeyword
     * if the similarity ratio of {@code word} and {@code searchKeyword} is greater
     * than or equal to the attribute similarityThreshold.
     * else, returns -1 to indicate dissimilarity.
     *
     * @param searchKeyword
     * @param word
     */
    private double getSimilarityRatio(String searchKeyword, String word) {
        double score = StringUtil.calculateSimilarityRatio(searchKeyword.toLowerCase(), word.toLowerCase());

        if (score >= this.similarityThreshold) {
            return score;
        } else {
            return -1;
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
