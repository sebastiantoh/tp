package seedu.address.logic.similarityhandler;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a class to find similar items given a search keyword.
 */
public abstract class SimilarItems<T> {

    private static final double EXACT_MATCH = 2;

    private static final double NOT_FOUND = -1;

    private static final double DEFAULT_SIMILARITY_THRESHOLD = 0.5;

    private final String searchKeyword;

    private final Map<T, Double> similarityMapper;

    private final double similarityThreshold;

    /**
     * Initializes a SimilarItems object with the given @{code searchKeyword} and {@code similarityThreshold}.
     *
     * @param searchKeyword string to be compared to for similarity.
     * @param similarityThreshold minimum ratio to be considered similar.
     */
    public SimilarItems(String searchKeyword, double similarityThreshold) {
        requireNonNull(searchKeyword);

        this.searchKeyword = searchKeyword;
        this.similarityMapper = new HashMap<>();
        this.similarityThreshold = similarityThreshold;
    }

    /**
     * Initializes a SimilarItems object with the given @{code searchKeyword}.
     * The default similarity threshold is applied.
     *
     * @param searchKeyword string to be compared to for similarity.
     */
    public SimilarItems(String searchKeyword) {
        this(searchKeyword, DEFAULT_SIMILARITY_THRESHOLD);
    }

    /**
     * Returns true if {@code item} exists in the similarity mapper,
     * which means that {@code item} is similar to the attribute searchKeyword.
     *
     * @param item an object to be checked if it is similar.
     * @return boolean value indicating @{item}'s existence in the similarity mapper.
     */
    public final boolean isInSimilarityMapper(T item) {
        return this.similarityMapper.containsKey(item);
    }

    /**
     * Returns the value given the key {@code item} from the similarity matrix.
     * If {@code item} does not exist in similarity matrix,
     * the value NOT_FOUND is returned.
     *
     * @param item  an object that is of type T.
     * @return the similarity ratio of {@code item} to the attribute search keyword.
     */
    public final Double getFromSimilarityMatrix(T item) {
        return this.similarityMapper.getOrDefault(item, NOT_FOUND);
    }

    /**
     * Considers every item in {@code list} and
     * adds entry < T, similarity ratio > to the similarity mapper if
     * if T is an exact / similar match for the attribute searchKeyword.
     *
     * @param itemList list of T objects.
     */
    public final void fillSimilarityMapper(List<T> itemList) {
        String[] searchKeywordComponents = this.searchKeyword.split("\\s+");

        for (T item : itemList) {
            Map.Entry<T, Double> entry = this.calculateHighestSimilarity(item, searchKeywordComponents);
            if (!Objects.isNull(entry)) {
                this.similarityMapper.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Returns entry <{@code item}, similarity ratio> if
     * there is an exact/similar match of {@code item} to the attribute searchKeyword.
     *
     * Entry for {@code item} will have the largest similarity ratio for {@code item}.
     *
     * If no exact/similar match of {@code item}, returns null to indicate dissimilarity.
     *
     * @param item object of type T to be checked for similarity.
     * @param searchKeywordComponents strings to be checked against for similarity.
     * @return entry <{@code item}, similarity ratio>.
     */
    private Map.Entry<T, Double> calculateHighestSimilarity(T item, String[] searchKeywordComponents) {
        if (this.getAttributeAsStr(item).equalsIgnoreCase(this.searchKeyword)) {
            return Map.entry(item, EXACT_MATCH);
        }

        String[] components = this.getAttributeAsStr(item).split("\\s+");

        double maxScore = -1;

        for (String searchKeyword : searchKeywordComponents) {
            for (String word : components) {
                double score = this.calculateSimilarityRatio(searchKeyword, word);
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
     * Else, returns -1 to indicate dissimilarity.
     *
     * @param searchKeyword string that is searched.
     * @param word word compared to searchKeyWord.
     */
    private double calculateSimilarityRatio(String searchKeyword, String word) {
        double score = StringUtil.calculateSimilarityRatio(searchKeyword.toLowerCase(), word.toLowerCase());

        if (score >= this.similarityThreshold) {
            return score;
        } else {
            return -1;
        }
    }

    /**
     * Returns the attribute of {@code item} in String that
     * will be compared to attribute searchKeyword for similarity.
     *
     * @param item object of type T.
     * @return attribute of {@code item} in String.
     */
    abstract String getAttributeAsStr(T item);

    /**
     * Returns the similarity mapper.
     *
     * @return map object.
     */
    public Map<T, Double> getSimilarityMapper() {
        return similarityMapper;
    }
}
