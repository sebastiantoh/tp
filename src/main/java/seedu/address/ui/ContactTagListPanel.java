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
 * Panel containing the list of contact tags.
 */
public class ContactTagListPanel extends UiPart<Region> {
    private static final String FXML = "ContactTagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SalesTagListPanel.class);

    @FXML
    private ListView<Tag> contactTagListView;

    /**
     * Creates a {@code TagListViewPanel} with the given {@code ObservableList}.
     */
    public ContactTagListPanel(ObservableList<Tag> tagList) {
        super(FXML);
        contactTagListView.setItems(tagList);
        contactTagListView.setCellFactory(listView -> new ContactTagListViewCell());
    }

    /**
     * Refreshes (redraws) the SalesTagListPanel. Use when you want to force an update of the UI.
     */
    public void refresh() {
        this.contactTagListView.refresh();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Tag} using a {@code TagCard}.
     */
    class ContactTagListViewCell extends ListCell<Tag> {
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
