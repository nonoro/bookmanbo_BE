package toyproject1.bookmanbo.aladin.dto;

public enum Sort {
    ACCURACY("Accuracy"),
    PUBLISH_TIME("PublishTime"),
    TITLE("Title"),
//    판매량순
    SALES_POINT("SalesPoint"),
//    고객평점순
    CUSTOMER_RATIND("CustomerRating"),
//    마이리뷰갯수
    MYREVIEW_COUNT("MyReviewCount");

    public final String sort;

    Sort(String sort) {
        this.sort = sort;
    }
}
