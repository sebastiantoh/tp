package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of sales tags.
 */
public class SalesTagListPanel extends UiPart<Region> {
    private static final String FXML = "SalesTagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SalesTagListPanel.class);

    @FXML
    private ListView<Tag> salesTagListView;

    /**
     * Creates a {@code SalesTagListViewPanel} with the given {@code ObservableList}.
     */
    public SalesTagListPanel(ObservableList<Tag> tagList) {
        super(FXML);
        salesTagListView.setItems(tagList);
        salesTagListView.setCellFactory(listView -> new SalesTagListViewCell());
    }

    /**
     * Refreshes (redraws) the SalesTagListPanel. Use when you want to force an update of the UI.
     */
    public void refresh() {
        this.salesTagListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tag} using a {@code TagCard}.
     */
    class SalesTagListViewCell extends ListCell<Tag> {
        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagCard(tag, getIndex() + 1).getRoot());
            }
        }
    }

}
