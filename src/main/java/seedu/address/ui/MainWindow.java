package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.dataset.Data;
import seedu.address.model.dataset.DataSet;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private static final String[] DARK_THEME = new String[] {"view/DarkTheme.css", "view/Extensions.css"};
    private static final String[] LIGHT_THEME = new String[] {"view/LightTheme.css", "view/LightExtensions.css"};
    private boolean isDarkTheme;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private MeetingListPanel meetingListPanel;
    private ReminderListPanel reminderListPanel;
    private SaleListPanel saleListPanel;
    private ContactTagListPanel contactTagListPanel;
    private SalesTagListPanel salesTagListPanel;
    private ChatBox chatBox;
    private HelpWindow helpWindow;
    private StatisticsWindow statisticsWindow;

    private List<StatisticsWindow> openStatisticsWindows;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane meetingListPanelPlaceholder;

    @FXML
    private StackPane reminderListPanelPlaceholder;

    @FXML
    private StackPane adHocPanelPlaceholder;

    @FXML
    private StackPane adHocSecondaryPanelPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane chatBoxPlaceholder;

    @FXML
    private Scene scene;

    @FXML
    private VBox secondColumn;

    private HostServices hostServices;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic} and {@code HostServices}.
     */
    public MainWindow(Stage primaryStage, Logic logic, HostServices hostServices) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
        if (logic.getGuiSettings().getTheme() == null || logic.getGuiSettings().getTheme().equals("dark")) {
            setTheme(true);
            isDarkTheme = true;
        } else {
            setTheme(false);
            isDarkTheme = false;
        }

        setAccelerators();

        HBox.setHgrow(secondColumn, Priority.ALWAYS);

        helpWindow = new HelpWindow(hostServices);
        openStatisticsWindows = new ArrayList<>();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getSortedPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        meetingListPanel = new MeetingListPanel(logic.getSortedMeetingList());
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());

        reminderListPanel = new ReminderListPanel(logic.getSortedReminderList());
        reminderListPanelPlaceholder.getChildren().add(reminderListPanel.getRoot());

        saleListPanel = new SaleListPanel(logic.getSortedSaleList());
        adHocPanelPlaceholder.getChildren().add(saleListPanel.getRoot());

        contactTagListPanel = new ContactTagListPanel(logic.getContactTagList());
        salesTagListPanel = new SalesTagListPanel(logic.getSalesTagList());

        chatBox = new ChatBox();
        chatBoxPlaceholder.getChildren().add(chatBox.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    private void setTheme(boolean isDarkTheme) {
        scene.getStylesheets().clear();
        if (isDarkTheme) {
            scene.getStylesheets().addAll(DARK_THEME);
        } else {
            scene.getStylesheets().addAll(LIGHT_THEME);
        }
        this.isDarkTheme = isDarkTheme;
    }
    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), (isDarkTheme ? "dark" : "light"));
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
        this.openStatisticsWindows.forEach(StatisticsWindow::hide);
    }


    /**
     * Opens a statistics window.
     */
    @FXML
    public void handleStatisticsResult(DataSet<? extends Data> statisticResult) {
        this.statisticsWindow = new StatisticsWindow(statisticResult);
        this.statisticsWindow.getRoot()
                .setOnCloseRequest(x -> this.openStatisticsWindows.remove(this.statisticsWindow));

        this.openStatisticsWindows.add(this.statisticsWindow);
        this.statisticsWindow.show();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            chatBox.displayInputAndResponse(commandText, commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isClear()) {
                chatBox.clear();
            }

            if (commandResult.hasStatisticsResult()) {
                handleStatisticsResult(commandResult.getStatisticResult());
            }

            if (commandResult.getTheme() != null) {
                if (commandResult.getTheme() == 0) {
                    setTheme(true);
                } else {
                    setTheme(false);
                }
            }

            // Force refresh of the following UI components which are time sensitive
            // Overdue reminders should be displayed differently
            this.reminderListPanel.refresh();
            // Past meetings should be filtered out
            this.meetingListPanel.refresh();

            if (commandResult.isSaleGuiShown()) {
                adHocPanelPlaceholder.getChildren().setAll(saleListPanel.getRoot());
                adHocSecondaryPanelPlaceholder.getChildren().clear();
            }

            if (commandResult.isTagGuiShown()) {
                adHocPanelPlaceholder.getChildren().setAll(contactTagListPanel.getRoot());
                adHocSecondaryPanelPlaceholder.getChildren().setAll(salesTagListPanel.getRoot());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            chatBox.displayInputAndResponse(commandText, e.getMessage());
            throw e;
        }
    }
}
