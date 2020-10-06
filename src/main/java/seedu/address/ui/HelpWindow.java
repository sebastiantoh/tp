package seedu.address.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-t11-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL + ".";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private GridPane table;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        try {
            populateHelpWindow();
        } catch (IOException e) {
            logger.warning(e::getMessage);
        }

    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Populates the help window with all StonksBook command description.
     *
     * @throws IOException
     */
    public void populateHelpWindow() throws IOException {
        helpMessage.setText(HELP_MESSAGE);

        List<String> list = Files.readAllLines(
                Paths.get("./src/main/resources/text/helpForAllCommands.txt"));

        CommandTable commandTable = new CommandTable();

        for (int rowIndex = 1; rowIndex <= list.size(); rowIndex++) {
            String[] components = list.get(rowIndex - 1).split("\\s{4,}");

            if (components.length == 1 && !components[0].isBlank()) {
                commandTable.addHeaders(rowIndex, components[0]);
            } else {
                commandTable.addCommandDescription(rowIndex, components);
            }
        }
    }

    private class CommandTable {

        private final List<String> colors = Arrays.asList("red", "blue", "green", "purple", "orange", "brown");

        private int headerCounter = 0;

        /**
         * Adds colored command headers.
         *
         * @param rowIndex grid pane row to insert to
         * @param headerText header text to be added
         * @return
         */
        private void addHeaders(int rowIndex, String headerText) {
            Label header = new Label(headerText);
            header.setStyle("-fx-text-fill: " + this.colors.get(this.headerCounter / 2));
            table.addRow(rowIndex, header);
            this.headerCounter++;
        }

        /**
         * Adds command description.
         *
         * @param rowIndex grid pane row to insert to
         * @param descriptionParts parts of the command description to be added
         */
        private void addCommandDescription(int rowIndex, String[] descriptionParts) {
            List<Label> commandDescParts = new ArrayList<>();

            for (String descriptionPartText : descriptionParts) {
                Label descriptionPart = new Label(descriptionPartText);
                descriptionPart.setStyle("-fx-label-padding: 0 4em 0 0");
                commandDescParts.add(descriptionPart);
            }

            table.addRow(rowIndex, commandDescParts.toArray(new Label[0]));
        }
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
