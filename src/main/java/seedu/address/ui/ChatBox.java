package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ChatBox extends UiPart<Region> {

    private static final String FXML = "ChatBox.fxml";

    private Image stonksBotImage = new Image(this.getClass().getResourceAsStream("/images/stonksbot.png"));

    @FXML
    private VBox dialogContainer;

    public ChatBox() {
        super(FXML);
    }

    /**
     * Displays a dialog box containing StonksBot's response. (WIP)
     */
    public void displayInputAndResponse(String response) {
        requireNonNull(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getStonksBotDialog(response, stonksBotImage)
        );
    }
}
