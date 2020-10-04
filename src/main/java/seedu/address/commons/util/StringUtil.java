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
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Calculates the difference between {@code str1} and {@code str2}.
     *
     * @param str1
     * @param str2
     * @return the difference count.
     */
    public static int calculateDifferenceCount(String str1, String str2) {
        return calculateLevenshteinDistance(str1, str2);
    }

    private static int calculateLevenshteinDistance(String str1, String str2) {
        int[][] DP = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0 ; i < str2.length() + 1 ; i++) {
            DP[0][i] = i;
        }
        for (int i = 0 ; i < str1.length() + 1 ; i++) {
            DP[i][0] = i;
        }

        for (int i = 1 ; i < str1.length() + 1 ; i++) {
            for (int k = 1 ; k < str2.length() + 1 ; k++) {
                DP[i][k] = Math.min(Math.min(DP[i - 1][k] + 1, DP[i][k - 1] + 1), DP[i - 1][k - 1] +
                        (str1.charAt(i - 1) == str2.charAt(k - 1) ? 0 : 1));
            }
        }
        return DP[str1.length()][str2.length()];
    }
}
