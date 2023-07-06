package com.mang.atdd.membership.collection;

public enum PayType {

    ACCOUNT_TRANSFER("계좌이체"),
    REMITTANCE("무통장입금"),
    EMPTY("없음"),
    TOSS("토스"),
    KAKAO_PAY("카카오페이"),
    BAEMIN_PAY("배민페이"),
    ;

    private String title;

    PayType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
