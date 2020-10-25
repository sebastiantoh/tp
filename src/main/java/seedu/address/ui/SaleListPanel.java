package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.sale.Sale;

/**
 * Panel containing the list of sale.
 */
public class SaleListPanel extends UiPart<Region> {
    private static final String FXML = "SaleListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SaleListPanel.class);

    @FXML
    private ListView<Sale> saleListView;

    /**
     * Creates a {@code SaleListViewCell} with the given {@code ObservableList}.
     */
    public SaleListPanel(ObservableList<Sale> saleList) {
        super(FXML);
        saleListView.setItems(saleList);
        saleListView.setCellFactory(listView -> new SaleListViewCell());
    }

    /**
     * Refreshes (redraws) the SaleListPanel. Use when you want to force an update of the UI.
     */
    public void refresh() {
        this.saleListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Meeting} using a {@code MeetingCard}.
     */
    class SaleListViewCell extends ListCell<Sale> {
        @Override
        protected void updateItem(Sale sale, boolean empty) {
            super.updateItem(sale, empty);

            if (empty || sale == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SaleCard(sale, getIndex() + 1).getRoot());
            }
        }
    }

}
