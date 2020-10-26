package seedu.address.commons.dataset.tag;

import java.time.Month;
import java.time.Year;
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
import seedu.address.commons.dataset.date.MonthAndYear;
import seedu.address.commons.dataset.date.MonthlyCountData;
import seedu.address.model.sale.Sale;
import seedu.address.model.tag.Tag;

/**
 * Stores a list of items of type T based on the key of < month, year >
 */
public class SaleTagListMap {

    private final Map<TagKey, List<Sale>> saleTagListMap;

    public SaleTagListMap() {
        this.saleTagListMap = new HashMap<>();
    }

    /**
     * Adds {@code sale} to an sale list based on the key of {@code tag}.
     *
     * @param sale a valid year sale.
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
     * @param sale a valid year sale
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
     * @param tag a valid month tag.
     * @return the number of sales with that tag.
     */
    public int getSaleCount(Tag tag) {
        TagKey key = new TagKey(tag);
        if (this.saleTagListMap.containsKey(key)) {
            return this.saleTagListMap.get(key).size();
        }
        return 0;
    }

    /**
     * Gets the list of sales with a specific {@code tag}.
     *
     * @param tag a valid month tag.
     * @return list of sales in its natural order
     */
    public List<Sale> getSales(Tag tag) {
        TagKey key = new TagKey(tag);
        return this.saleTagListMap.getOrDefault(key, Collections.emptyList());
    }

    /**
     * Removes all sales in the saleTagListMap.
     */
    public void clear() {
        this.saleTagListMap.clear();
    }

    /**
     * Gets the sale counts in the sale list for every {@code tag}.
     *
     * @return DataSet<SaleTagCountData> object, where the data is ordered by non-decreasing year and month
     */
    public DataSet<SaleTagCountData> getSaleTagCount() {
        List<SaleTagCountData> result = new ArrayList<>();

        saleTagListMap.forEach(((tagKey, sales) -> {
            result.add(new SaleTagCountData(tagKey, getSaleCount(tagKey.getTag())));
        }));

        result.sort(Comparator.comparingInt(Data::getCount));
        List<SaleTagCountData> topSaleTagCountData = result.stream().limit(5).collect(Collectors.toList());
        Collections.reverse(topSaleTagCountData);

        return new DataSet<>(topSaleTagCountData);
    }
}
