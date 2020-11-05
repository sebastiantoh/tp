package seedu.address.model.dataset;

import java.util.List;
import java.util.Objects;

/**
 * Stores Data objects and a title to describe the DataSet.
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

    public boolean isEmpty() {
        return dataList.isEmpty();
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

        boolean isTitleNullOrEqual = (Objects.isNull(this.title) && Objects.isNull(dataSet.title))
                || title.equals(dataSet.title);

        return isTitleNullOrEqual && dataList.equals(dataSet.dataList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, dataList);
    }
}

