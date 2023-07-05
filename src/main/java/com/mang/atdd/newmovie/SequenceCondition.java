package com.mang.atdd.newmovie;

public class SequenceCondition implements DiscountCondition{
    //순번에 따른 할인정책
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return false;
    }
}
