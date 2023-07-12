package com.mang.atdd.membership.collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.assertj.core.api.Assertions.*;

public enum PayGroup {

    PAY("페이", Arrays.asList(PayType.KAKAO_PAY, PayType.BAEMIN_PAY)),

    CASH("현금", Arrays.asList(PayType.TOSS, PayType.REMITTANCE)),
    EMPTY("없음", Collections.emptyList()),
    ;

    private String title;
    private List<PayType> payList;

    public String getTitle() {
        return title;
    }

    PayGroup(String title, List<PayType> payList) {
        this.title = title;
        this.payList = payList;
    }

    public static PayGroup findByPayCode(PayType code) {
        return Arrays.stream(com.mang.atdd.membership.collection.PayGroup.values())
                .filter(payGroup -> payGroup.hasPayCode(code))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasPayCode(PayType code) {
        return payList.stream()
                .anyMatch(pay -> pay.equals(code));
    }

}
