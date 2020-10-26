package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.MonthlyCountDataSet;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** The chat box should be cleared. */
    private final boolean clear;

    /** The Tag GUI should be shown */
    private final boolean isTagGuiShown;

    /** The Sale GUI should be shown */
    private final boolean isSaleGuiShown;

    private final MonthlyCountDataSet statisticResult;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * {@code showHelp}, {@code exit}, {@code clear}
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean clear) {
        this(feedbackToUser, showHelp, exit, clear, null, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean clear,
                         MonthlyCountDataSet statisticResult, boolean isTagGuiShown, boolean isSaleGuiShown) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.clear = clear;
        this.statisticResult = statisticResult;
        this.isTagGuiShown = isTagGuiShown;
        this.isSaleGuiShown = isSaleGuiShown;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code statisticResult},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, MonthlyCountDataSet statisticResult) {
        this(feedbackToUser, false, false, false, statisticResult, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, null, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, {@code isTagGuiShown} and
     * {@code isSaleGuiShown} and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean showTagGui, boolean showSaleGui) {
        this(feedbackToUser, false, false, false, null, showTagGui, showSaleGui);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isClear() {
        return clear;
    }

    public boolean isTagGuiShown() {
        return isTagGuiShown;
    }

    public boolean isSaleGuiShown() {
        return isSaleGuiShown;
    }

    public boolean hasStatisticsResult() {
        return !Objects.isNull(this.statisticResult);
    }

    public MonthlyCountDataSet getStatisticResult() {
        return statisticResult;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && clear == otherCommandResult.clear
                && exit == otherCommandResult.exit
                && ((Objects.isNull(statisticResult)
                    && Objects.isNull(otherCommandResult.statisticResult))
                    || (!Objects.isNull(statisticResult)
                    && statisticResult.equals(otherCommandResult.statisticResult)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, clear, statisticResult);
    }

}
