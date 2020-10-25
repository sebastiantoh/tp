//package seedu.address.commons.dataset.date;
//
//import java.util.List;
//import java.util.Objects;
//
///**
// * Stores MonthlyCountData objects and a title to describe the dataset
// */
//public class MonthlyCountDataSet {
//
//    private String title;
//    private final List<MonthlyCountData> monthlyCountDataList;
//
//    public MonthlyCountDataSet(List<MonthlyCountData> monthlyCountDataList) {
//        this.monthlyCountDataList = monthlyCountDataList;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public List<MonthlyCountData> getMonthlyCountDataList() {
//        return monthlyCountDataList;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this
//                || (other instanceof MonthlyCountDataSet // instanceof handles nulls
//                && this.monthlyCountDataList.equals(((MonthlyCountDataSet) other).monthlyCountDataList)
//                && ((Objects.isNull(this.title) && Objects.isNull(((MonthlyCountDataSet) other).title))
//                    || this.title.equals(((MonthlyCountDataSet) other).title)));
//    }
//}
