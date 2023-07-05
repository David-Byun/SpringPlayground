package com.mang.atdd.newmovie;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
