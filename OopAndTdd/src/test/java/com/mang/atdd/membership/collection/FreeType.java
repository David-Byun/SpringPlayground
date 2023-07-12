package com.mang.atdd.membership.collection;

public enum FreeType implements EnumMapperType{

    PERCENT("정율"),
    MONEY("정액"),
    ;

    private String title;

    FreeType(String title) {
        this.title = title;
    }

    //getCode() 메서드 내부의 return name() 열거형 상수 이름을 'String'으로 반환
    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTitle() {
        return title;
    }
}
