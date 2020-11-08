package seedu.address.testutil.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.sale.EditCommand.EditSaleDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Person;
import seedu.address.model.sale.ItemName;
import seedu.address.model.sale.Quantity;
import seedu.address.model.sale.Sale;
import seedu.address.model.sale.UnitPrice;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditSaleDescriptor objects.
 */
public class EditSaleDescriptorBuilder {

    private EditSaleDescriptor descriptor;

    public EditSaleDescriptorBuilder() {
        descriptor = new EditSaleDescriptor();
    }

    public EditSaleDescriptorBuilder(EditSaleDescriptor descriptor) {
        this.descriptor = new EditSaleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSaleDescriptor} with fields containing {@code sale}'s details
     */
    public EditSaleDescriptorBuilder(Sale sale) {
        descriptor = new EditSaleDescriptor();
        descriptor.setItemName(sale.getItemName());
        descriptor.setBuyer(sale.getBuyer());
        descriptor.setDatetimeOfPurchase(sale.getDatetimeOfPurchase());
        descriptor.setUnitPrice(sale.getUnitPrice());
        descriptor.setQuantity(sale.getQuantity());
        descriptor.setTags(sale.getTags());
    }

    /**
     * Sets the {@code itemName} of the {@code EditSaleDescriptor} that we are building.
     */
    public EditSaleDescriptorBuilder withItemName(String itemName) {
        descriptor.setItemName(new ItemName(itemName));
        return this;
    }

    /**
     * Sets the {@code buyerId} of the {@code EditSaleDescriptor} that we are building.
     */
    public EditSaleDescriptorBuilder withBuyer(Person buyer) {
        descriptor.setBuyer(buyer);
        return this;
    }

    /**
     * Sets the {@code localDateTime} of the {@code EditSaleDescriptor} that we are building.
     */
    public EditSaleDescriptorBuilder withDatetimeOfPurchase(String localDateTime) {
        descriptor.setDatetimeOfPurchase(LocalDateTime.parse(localDateTime, ParserUtil.DATE_TIME_FORMATTER));
        return this;
    }

    /**
     * Sets the {@code unitPrice} of the {@code EditSaleDescriptor} that we are building.
     */
    public EditSaleDescriptorBuilder withUnitPrice(String unitPrice) {
        descriptor.setUnitPrice(new UnitPrice(new BigDecimal(unitPrice)));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditSaleDescriptor} that we are building.
     */
    public EditSaleDescriptorBuilder withQuantity(Integer quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSaleDescriptor}
     * that we are building.
     */
    public EditSaleDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditSaleDescriptor build() {
        return descriptor;
    }
}
