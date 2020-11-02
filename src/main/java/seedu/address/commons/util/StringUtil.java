package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            if (s.length() == 0) {
                return false;
            }
            if (s.length() == 1 && s.charAt(0) == '0') {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                Integer.parseInt(String.valueOf(s.charAt(i)));
            }
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Calculates the similarity ratio between {@code str1} and {@code str2}.
     * The similarity ratio ranges from 0.0 to 1.0.
     * The higher the ratio, the greater the similarity between {@code str1} and {@code str2}.
     *
     * @param str1 first string
     * @param str2 second string
     * @return similarity result as a ratio
     */
    public static double calculateSimilarityRatio(String str1, String str2) {
        requireNonNull(str1);
        requireNonNull(str2);
        return 1 - 1.0 * calculateLevenshteinDistance(str1, str2) / Math.max(str1.length(), str2.length());
    }


    /**
     * Calculates the levenshtein distance between {@code str1} and {@code str2}.
     * Returns 0 if {@code str1} and {@code str2} are identical.
     *
     *<br>examples:<pre>
     *   calculateLevenshteinDistance("ell", "hell") == 1 // add h to ell
     *   calculateLevenshteinDistance("hello", "hell") == 1 // remove o from hello
     *   calculateLevenshteinDistance("hello", "hullu") == 2 // substitute e with u and o with u in hello
     *   calculateLevenshteinDistance("Hello", "hello") == 1 // substitute H with h in Hello
     *   </pre>
     *
     * @param str1 first string
     * @param str2 second string
     * @return
     */
    private static int calculateLevenshteinDistance(String str1, String str2) {
        requireNonNull(str1);
        requireNonNull(str2);

        int rowSize = str1.length() + 1;
        int colSize = str2.length() + 1;

        int[][] memoizedArray = new int[rowSize][colSize];

        for (int str1Index = 0; str1Index < rowSize; str1Index++) {
            memoizedArray[str1Index][0] = str1Index;
        }

        for (int str2Index = 0; str2Index < colSize; str2Index++) {
            memoizedArray[0][str2Index] = str2Index;
        }

        for (int str1Index = 1; str1Index < rowSize; str1Index++) {
            for (int str2Index = 1; str2Index < colSize; str2Index++) {

                int insertionAtStr1 = memoizedArray[str1Index][str2Index - 1] + 1;
                int deletionAtStr1 = memoizedArray[str1Index - 1][str2Index] + 1;
                int matchOrReplacement = memoizedArray[str1Index - 1][str2Index - 1]
                        + (str1.charAt(str1Index - 1) == str2.charAt(str2Index - 1) ? 0 : 1);

                int minBetweenInsertionAndDeletion = Math.min(deletionAtStr1, insertionAtStr1);
                int minBetweenAllThree = Math.min(minBetweenInsertionAndDeletion, matchOrReplacement);

                memoizedArray[str1Index][str2Index] = minBetweenAllThree;
            }
        }

        return memoizedArray[str1.length()][str2.length()];
    }
}
