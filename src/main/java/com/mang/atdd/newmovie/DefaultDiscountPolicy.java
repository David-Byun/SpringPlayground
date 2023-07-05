package com.mang.atdd.newmovie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//실제 애플리케이션에서는 DiscountPolicy 인스턴스를 생성할 필요가 없기 때문에 추상 클래스로 구현
public abstract class DefaultDiscountPolicy implements DiscountPolicy {

    private List<DiscountCondition> conditions = new ArrayList<>();

    public DefaultDiscountPolicy(DiscountCondition ... conditions) {
        this.conditions = Arrays.asList(conditions);
    }


    @Override
    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : conditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);

}
