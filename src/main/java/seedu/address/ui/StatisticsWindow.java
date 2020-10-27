package seedu.address.ui;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.dataset.Data;
import seedu.address.commons.dataset.DataSet;

/**
 * Controller for a statistics page
 */
public class StatisticsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(StatisticsWindow.class);

    private static final String FXML = "StatisticsWindow.fxml";

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    /**
     * Creates a new StatisticsWindow.
     *
     * @param root Stage to use as the root of the StatisticsWindow.
     */
    public StatisticsWindow(Stage root, DataSet<? extends Data> statisticResult) {
        super(FXML, root);
        this.populateStatisticsWindow(statisticResult);
    }

    /**
     * Creates a new StatisticsWindow.
     */
    public StatisticsWindow(DataSet<? extends Data> statisticResult) {
        this(new Stage(), statisticResult);
    }


    /**
     * Populates the statistics window with the statistics data from {@code statisticResult}.
     */
    private void populateStatisticsWindow(DataSet<? extends Data> statisticResult) {
        this.barChart.setTitle(statisticResult.getTitle());
        int maxValue = 0;
        this.y.setTickUnit(1);
        this.y.setAutoRanging(false);
        this.y.setMinorTickVisible(false);
        XYChart.Series<String, Integer> bars = new XYChart.Series<>();
        for (Data data : statisticResult.getDataList()) {
            XYChart.Data<String, Integer> data1 = new XYChart.Data<>(
                    data.getKeyAsStr(), data.getCount());
            bars.getData().add(data1);
            maxValue = Math.max(maxValue, data.getCount());
        }

        System.out.println(bars.getData().size());

        List<String> barColors = Arrays.asList("blue", "red", "purple", "yellow", "orange", "green");

        this.barChart.getData().add(bars);
        for (int i = 0; i < bars.getData().size(); i++) {
            bars.getData().get(i).getNode().setStyle("-fx-bar-fill: " + barColors.get(i));
        }

        this.barChart.setLegendVisible(false);
        this.barChart.setBarGap(-1);
        this.barChart.setCategoryGap(300 / bars.getData().size());
        this.y.setUpperBound(maxValue);
        this.barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        this.barChart.lookup(".chart-title").setStyle("-fx-text-fill: WHITE");
    }

    /**
     * Shows the statistics window.
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
        logger.fine("Showing statistics page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Hides the statistics window.
     */
    public void hide() {
        getRoot().hide();
    }

}
