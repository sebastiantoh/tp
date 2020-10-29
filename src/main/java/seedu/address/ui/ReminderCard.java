package seedu.address.ui;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import seedu.address.model.reminder.Reminder;

/**
 * An UI component that displays information of a {@code Reminder}.
 */
public class ReminderCard extends UiPart<Region> {

    // The shade of green that is used to indicate completed reminders
    private static final String GREEN = "#2EA44E";
    // The shade of red that is used to indicate overdue reminders
    private static final String RED = "#ff0266";
    private static final String COMPLETED_DATE_STYLE_CLASS = "completed";
    private static final String OVERDUE_DATE_STYLE_CLASS = "overdue";
    private static final String FXML = "ReminderListCard.fxml";

    public final Reminder reminder;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label message;
    @FXML
    private Label scheduledDate;
    @FXML
    private Label personName;
    @FXML
    private FontIcon calendarIcon;

    /**
     * Creates a {@code ReminderCard} with the given {@code Reminder} and index to display.
     */
    public ReminderCard(Reminder reminder, int displayedIndex) {
        super(FXML);
        this.reminder = reminder;
        id.setText(displayedIndex + ". ");
        message.setText(reminder.getMessage().message);
        scheduledDate.setText(reminder.getFormattedScheduledDate());
        personName.setText(reminder.getPerson().getName().fullName);

        if (reminder.isOverdue()) {
            setStyleToIndicateOverdue();
        } else if (reminder.isCompleted()) {
            setStyleToIndicateCompleted();
        }
    }

    /**
     * Sets the reminder card style to indicate an overdue reminder.
     */
    private void setStyleToIndicateOverdue() {
        calendarIcon.getStyleClass().clear();
        calendarIcon.setIconSize(16);
        calendarIcon.setIconColor(Paint.valueOf(RED));

        ObservableList<String> scheduledDateStyleClass = scheduledDate.getStyleClass();
        if (!scheduledDateStyleClass.contains(OVERDUE_DATE_STYLE_CLASS)) {
            scheduledDateStyleClass.add(OVERDUE_DATE_STYLE_CLASS);
        }
    }

    /**
     * Sets the reminder card style to indicate a completed reminder.
     */
    private void setStyleToIndicateCompleted() {
        calendarIcon.getStyleClass().clear();
        calendarIcon.setIconSize(16);
        calendarIcon.setIconColor(Paint.valueOf(GREEN));

        ObservableList<String> scheduledDateStyleClass = scheduledDate.getStyleClass();
        if (!scheduledDateStyleClass.contains(COMPLETED_DATE_STYLE_CLASS)) {
            scheduledDateStyleClass.clear();
            scheduledDateStyleClass.add(COMPLETED_DATE_STYLE_CLASS);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return id.getText().equals(card.id.getText())
                && reminder.equals(card.reminder);
    }
}
