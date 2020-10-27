package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {
    private static final String PAST_MEETING_STYLE_CLASS = "past-meeting";
    private static final String FXML = "MeetingListCard.fxml";

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label message;
    @FXML
    private Label date;
    @FXML
    private Label personName;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        message.setText(meeting.getMessage().message);
        date.setText(meeting.getFormattedStartEndDate());
        personName.setText(meeting.getPerson().getName().fullName);

        if (meeting.isOver()) {
            setStyleToIndicatePastMeeting();
        }
    }

    /**
     * Sets the meeting card style to indicate a past meeting.
     */
    private void setStyleToIndicatePastMeeting() {
        ObservableList<String> cardPaneStyleClass = cardPane.getStyleClass();
        if (!cardPaneStyleClass.contains(PAST_MEETING_STYLE_CLASS)) {
            cardPaneStyleClass.add(PAST_MEETING_STYLE_CLASS);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
