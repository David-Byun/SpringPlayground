package com.mang.atdd.oop.shop;

import com.mang.atdd.newmovie.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class Option {
    private String name;
    private Money price;

    @Builder
    public Option(String name, Money price) {
        this.name = name;
        this.price = price;
    }
}
