package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MOST_SIMILAR_COMMAND = MESSAGE_UNKNOWN_COMMAND + "\nDo you mean: %1$s?";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_SALES_LISTED_OVERVIEW = "%1$d sale items listed!";
    public static final String MESSAGE_INVALID_DATETIME =
            "Dates should be specified in the format 'yyyy-MM-dd HH:mm'";
    public static final String MESSAGE_INVALID_DURATION =
            "Durations should be specified as a positive integer denoting the number of minutes.";
    public static final String MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX = "The reminder index provided is invalid";
    public static final String MESSAGE_INVALID_TAG_DISPLAYED_INDEX = "The tag index provided is invalid";
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid";
    public static final String MESSAGE_INVALID_SALE_DISPLAYED_INDEX = "The sale index provided is invalid";
    public static final String MESSAGE_INVALID_MONTH =
            "Month must be an integer value in between 1 and 12 inclusive.";
    public static final String MESSAGE_INVALID_YEAR = "Year must be an non negative integer.";
    public static final String MESSAGE_INVALID_NUMBER_OF_MONTHS =
            "The number of months must be an integer value in between 2 and 6 inclusive.";

}
