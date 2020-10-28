package seedu.address.commons.dataset.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.dataset.Data;
import seedu.address.commons.dataset.DataSet;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;

/**
 * Stores a list of sales based on the key of < tag >
 */
public class SaleTagListMap {

    private final Map<TagKey, List<Sale>> saleTagListMap;

    public SaleTagListMap() {
        this.saleTagListMap = new HashMap<>();
    }

    /**
     * Adds {@code sale} to an sale list based on the key of {@code tag}.
     *
     * @param sale A valid sale to be added.
     */
    public void addSale(Sale sale) {
        Set<Tag> tags = sale.getTags();

        tags.forEach(tag -> {
            TagKey key = new TagKey(tag);
            if (this.saleTagListMap.containsKey(key)) {
                this.saleTagListMap.get(key).add(sale);
            } else {
                this.saleTagListMap.put(key, new ArrayList<>(Collections.singleton(sale)));
            }
        });
    }

    /**
     * Removes {@code sale} from an sale list based on the key of {@code key} if exists.
     *
     * @param sale The sale to be removed.
     */
    public void removeSale(Sale sale) {
        Set<Tag> tags = sale.getTags();

        tags.forEach(tag -> {
            TagKey key = new TagKey(tag);
            if (this.saleTagListMap.containsKey(key)) {
                if (this.saleTagListMap.get(key).size() == 1) {
                    this.saleTagListMap.remove(key);
                } else {
                    this.saleTagListMap.get(key).remove(sale);
                }
            }
        });
    }

    /**
     * Gets the number of items in an sale list based on the key of {@code key}.
     * If the key of {@code key} does not exist, the number is 0.
     *
     * @param tag A valid tag.
     * @return The number of sales with that tag.
     */
    public int getSaleCount(Tag tag) {
        TagKey key = new TagKey(tag);
        if (this.saleTagListMap.containsKey(key)) {
            return this.saleTagListMap.get(key).size();
        }
        return 0;
    }

    /**
     * Remove a tag from the saleTagListMap.
     *
     * @param tag The tag to be removed.
     */
    public void removeTag(Tag tag) {
        TagKey key = new TagKey(tag);
        saleTagListMap.remove(key);
    }

    /**
     * Edit a tagKey to contain the newly edited tag.
     *
     * @param previousTag The tag to be replaced.
     * @param newTag The newly edited tag to replace.
     */
    public void editTag(Tag previousTag, Tag newTag) {
        TagKey tagKeyToEdit = saleTagListMap.keySet().stream()
                .filter(tagKey -> tagKey.getTag().equals(previousTag))
                .findFirst().orElseThrow();
        tagKeyToEdit.setTag(newTag);
    }

    /**
     * Removes all sales in the saleTagListMap.
     */
    public void clear() {
        this.saleTagListMap.clear();
    }

    /**
     * Gets the sale counts in the sale list for the top 5 {@code tag}.
     * If tag does not have any sales assigned, it will not be included.
     *
     * @return A DataSet object containing SaleTagCountData.
     */
    public DataSet<SaleTagCountData> getSaleTagCount() {
        List<SaleTagCountData> result = new ArrayList<>();

        saleTagListMap.forEach(((tagKey, sales) -> {
            result.add(new SaleTagCountData(tagKey, getSaleCount(tagKey.getTag())));
        }));

        result.sort(Comparator.comparingInt(Data::getCount));
        List<SaleTagCountData> topSaleTagCountData = result.stream()
                .filter(data -> data.getCount() > 0).limit(5)
                .collect(Collectors.toList());
        Collections.reverse(topSaleTagCountData);

        return new DataSet<>(topSaleTagCountData);
    }
}
