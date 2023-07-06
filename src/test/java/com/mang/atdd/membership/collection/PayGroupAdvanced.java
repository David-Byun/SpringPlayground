package com.mang.atdd.membership.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mang.atdd.membership.collection.PayType.*;
import static java.util.Arrays.asList;

public enum PayGroupAdvanced {

    CASH("현금", asList(TOSS, ACCOUNT_TRANSFER)),
    EMPTY("없음", Collections.emptyList()),
    ;


    private String title;
    private List<PayType> payList;

    public String getTitle() {
        return title;
    }

    PayGroupAdvanced(String title, List<PayType> payList) {
        this.title = title;
        this.payList = payList;
    }

    public static PayGroupAdvanced findByPayCode(String code) {
        return Arrays.stream(PayGroupAdvanced.values())
                .filter(payGroup -> payGroup.hasPayCode(code))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasPayCode(String code) {
        return payList.stream()
                .anyMatch(pay -> pay.equals(code));
    }

}
