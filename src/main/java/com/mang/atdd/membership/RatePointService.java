package com.mang.atdd.membership;

import com.mang.atdd.membership.PointService;

public class RatePointService implements PointService {

    private static final int POINT_RATE = 1;

    @Override
    public int calculateAmount(int price) {
        return price * POINT_RATE / 100;
    }
}
