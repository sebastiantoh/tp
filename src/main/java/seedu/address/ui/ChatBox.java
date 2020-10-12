package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ChatBox extends UiPart<Region> {

    private static final String FXML = "ChatBox.fxml";

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

    public ChatBox() {
        super(FXML);
    }

    private void scrollToBottom() {
        scrollPane.applyCss();
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }

    /**
     * Displays a dialog box containing StonksBot's response. (WIP)
     */
    public void displayInputAndResponse(String commandText, String response) {
        requireNonNull(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(commandText),
                DialogBox.getStonksBotDialog(response)
        );
        scrollToBottom();
    }

    /**
     * Clears all past interactions with the GUI in the chat box.
     */
    public void clear() {
        dialogContainer.getChildren().clear();
    }
}
