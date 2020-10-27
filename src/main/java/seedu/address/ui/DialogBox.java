package seedu.address.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This control represents a dialog box consisting of a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static String botName = "StonksBot";

    private static String userName = "$";

    @FXML
    private Label dialog;

    private DialogBox(String text, ReadOnlyDoubleProperty parentWidthProperty) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.maxWidthProperty().bind(parentWidthProperty.subtract(15));
        dialog.setText(text);
    }

    /**
     * Flips the dialog box such that the text on the left.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Displays user dialog.
     */
    public static DialogBox getUserDialog(String text, ReadOnlyDoubleProperty parentWidthProperty) {
        var db = new DialogBox(userName + " " + text, parentWidthProperty);
        db.flip();
        return db;
    }

    /**
     * Displays StonksBot's response dialog.
     */
    public static DialogBox getStonksBotDialog(String text, ReadOnlyDoubleProperty parentWidthProperty) {
        var db = new DialogBox(botName + ": " + text, parentWidthProperty);
        db.flip();
        return db;
    }
}
