package seedu.address.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
 * Controller for a help page.
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2021s1-cs2103t-t11-1.github.io/tp/UserGuide.html";

    public static final String HELP_MESSAGE = "Refer to the user guide: ";

    public static final String MINIMUM_FOUR_WHITESPACE = "\\s{4,}";

    public static final String COMMAND_HELP_LEGEND = "\nThe information below is formatted"
            + " as (COMMAND NAME, COMMAND DESCRIPTION, COMMAND USAGE).";

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);

    private static final String FXML = "HelpWindow.fxml";

    private final CommandTable commandTable = new CommandTable();

    private boolean isUserGuideLinkOpened = false;

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
    private void populateHelpWindow() throws IOException {
        helpMessage.setText(HELP_MESSAGE);
        helpLink.setText(USER_GUIDE_URL);

        commandHelpLegend.setText(COMMAND_HELP_LEGEND);

        try (InputStream resource = this.getClass().getClassLoader()
                .getResourceAsStream("text/helpForAllCommands.txt")) {

            new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8))
                    .lines().forEachOrdered(this::addToCommandTable);
        }
    }

    private void addToCommandTable(String line) {
        String[] components = line.split(MINIMUM_FOUR_WHITESPACE);

        if (this.commandTable.isHeader(components)) {
            this.commandTable.addHeaders(components[0]);
        } else {
            this.commandTable.addCommandDescription(components);
        }
    }

    private class CommandTable {

        private static final int STARTING_ROW_INDEX = 2;

        private final List<String> colors = Arrays.asList("#d72c2c", "ffcccb", "#08aeae",
                "#11cf11", "#c307c3", "#f3a517", "#dbbc11");

        private int headerCounter = 0;

        private int currentRowIdx = STARTING_ROW_INDEX;

        private boolean isHeader(String[] components) {
            return components.length == 1 && !components[0].isBlank();
        }

        /**
         * Adds colored command headers.
         *
         * @param headerText header text to be added.
         */
        private void addHeaders(String headerText) {
            Label header = new Label(headerText);
            header.setStyle("-fx-text-fill: " + this.colors.get(this.headerCounter / 2));
            table.addRow(this.currentRowIdx, header);
            this.currentRowIdx++;
            this.headerCounter++;
        }

        /**
         * Adds command description.
         *
         * @param descriptionParts parts of the command description to be added.
         */
        private void addCommandDescription(String[] descriptionParts) {
            List<Label> commandDescParts = new ArrayList<>();

            for (String descriptionPartText : descriptionParts) {
                Label descriptionPart = new Label(descriptionPartText);
                descriptionPart.setMinWidth(Region.USE_PREF_SIZE);
                descriptionPart.setStyle("-fx-label-padding: 0 1em 0 0; -fx-text-fill: white;");
                commandDescParts.add(descriptionPart);
            }

            table.addRow(this.currentRowIdx, commandDescParts.toArray(new Label[0]));
            this.currentRowIdx++;
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
        if (!this.isUserGuideLinkOpened) {
            this.isUserGuideLinkOpened = true;
            this.setActionsOnLink();
        }

        this.hostServices.showDocument(USER_GUIDE_URL);
    }

    private void setActionsOnLink() {
        this.helpLink.setStyle("-fx-text-fill: grey");
        this.helpLink.setOnMouseMoved((v) -> this.helpLink.setStyle("-fx-text-fill: #0b6df3"));
        this.helpLink.setOnMouseExited((v) -> this.helpLink.setStyle("-fx-text-fill: grey"));
    }
}
