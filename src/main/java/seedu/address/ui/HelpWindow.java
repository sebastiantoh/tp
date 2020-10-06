package seedu.address.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-t11-1.github.io/tp/UserGuide.html";
    public static final String HELP_MESSAGE = "Refer to the user guide: ";

    public static final String COMMAND_HELP_LEGEND = "\nThe information below is formatted"
            + " as (COMMAND NAME, COMMAND DESCRIPTION, COMMAND USAGE).";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Text helpMessage;

    @FXML
    private Hyperlink helpLink;

    @FXML
    private Label commandHelpLegend;

    @FXML
    private GridPane table;

    private HostServices hostServices;

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
    public HelpWindow(HostServices hostServices) {
        this(new Stage());
        this.hostServices = hostServices;
    }

    /**
     * Populates the help window with all StonksBook command description.
     *
     * @throws IOException
     */
    public void populateHelpWindow() throws IOException {
        helpMessage.setText(HELP_MESSAGE);
        helpLink.setText(USERGUIDE_URL);

        commandHelpLegend.setText(COMMAND_HELP_LEGEND);

        List<String> list = Files.readAllLines(
                Paths.get("./src/main/resources/text/helpForAllCommands.txt"));

        CommandTable commandTable = new CommandTable();
        int startingRowIndex = 2;
        for (int i = 0; i < list.size(); i++) {
            String[] components = list.get(i).split("\\s{4,}");

            if (components.length == 1 && !components[0].isBlank()) {
                commandTable.addHeaders(i + startingRowIndex, components[0]);
            } else {
                commandTable.addCommandDescription(i + startingRowIndex, components);
            }
        }
    }

    private class CommandTable {

        private final List<String> colors = Arrays.asList("#d72c2c", "#08aeae", "#11cf11", "#c307c3", "#f3a517", "#dbbc11");

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
                descriptionPart.setMinWidth(Region.USE_PREF_SIZE);
                descriptionPart.setStyle("-fx-label-padding: 0 4em 0 0; -fx-text-fill: white");
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
     * Opens the webpage directed by the URL.
     * Sets events on URL hyperlinks for customised colors.
     */
    @FXML
    private void openLink() {
        this.hostServices.showDocument(USERGUIDE_URL);
        this.helpLink.setStyle("-fx-text-fill: grey");
        this.helpLink.setOnMouseMoved((v) -> this.helpLink.setStyle("-fx-text-fill: #0b6df3"));
        this.helpLink.setOnMouseExited((v) -> this.helpLink.setStyle("-fx-text-fill: grey"));
    }
}
