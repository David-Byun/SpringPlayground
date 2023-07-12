package airbnb.back.util;

import lombok.Getter;

@Getter
public enum SortBy {

    ID_DESC("id", "DESC"),
    PRICE_DESC("price", "DESC"),
    REVIEW_COUNT_DESC("review_count", "DESC"),
    ;


    private final String sortType;
    private final String orderType;


    SortBy(String sortType, String orderType) {
        this.sortType = sortType;
        this.orderType = orderType;
    }
}
