package com.mang.atdd.oop.order;

import com.mang.atdd.newmovie.Money;
import com.mang.atdd.oop.shop.Option;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderOption {

    private String name;
    private Money price;

    @Builder
    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }


    public Option convertToOption() {
        return new Option(name, price);
    }
}
