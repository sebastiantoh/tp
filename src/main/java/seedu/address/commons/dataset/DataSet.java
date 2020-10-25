package seedu.address.commons.dataset;

import java.util.List;
import java.util.Objects;

/**
 * Stores Data objects and a title to describe the DataSet
 */
public class DataSet<T extends Data> {

    private String title;
    private final List<T> dataList;

    public DataSet(List<T> dataList) {
        this.dataList = dataList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<T> getDataList() {
        return dataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataSet<?> dataSet = (DataSet<?>) o;
        return title.equals(dataSet.title) &&
                dataList.equals(dataSet.dataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dataList);
    }
}

