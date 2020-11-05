package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.sale.Sale;

/**
 * An UI component that displays information of a {@code Sale}.
 */
public class SaleCard extends UiPart<Region> {

    private static final String FXML = "SaleListCard.fxml";

    public final Sale sale;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label itemName;
    @FXML
    private FlowPane tags;
    @FXML
    private Label datetimeOfPurchase;
    @FXML
    private Label price;
    @FXML
    private HBox tagsBox;

    /**
     * Creates a {@code SaleCard} with the given {@code Sale} and index to display.
     */
    public SaleCard(Sale sale, int displayedIndex) {
        super(FXML);
        this.sale = sale;
        id.setText(displayedIndex + ". ");
        itemName.setText(setTitleText(sale));
        if (!sale.getTags().isEmpty()) {
            //@@author hakujitsu
            sale.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } else {
            tagsBox.setVisible(false);
            tagsBox.setManaged(false);
        }

        datetimeOfPurchase.setText(sale.getFormattedDatetimeOfPurchase());
        price.setText(setPriceText(sale));
    }

    private String setTitleText(Sale sale) {
        return sale.getItemName().name + " (Client: " + sale.getBuyer().getName().fullName + ")";
    }

    private String setPriceText(Sale sale) {
        return sale.getTotalCostString() + " (" + sale.getUnitPriceString() + " x " + sale.getQuantity().quantity + ")";
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SaleCard)) {
            return false;
        }

        // state check
        SaleCard card = (SaleCard) other;
        return id.getText().equals(card.id.getText())
                && sale.equals(card.sale);
    }
}
