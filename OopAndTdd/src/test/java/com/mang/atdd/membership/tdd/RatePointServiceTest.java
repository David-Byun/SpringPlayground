package com.mang.atdd.membership.tdd;

import com.mang.atdd.membership.RatePointService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RatePointServiceTest {

    @InjectMocks
    private RatePointService ratePointService;

    @Test
    void _10000원의적립은100원() {
        // given
        final int price = 10000;

        // when
        final int result = ratePointService.calculateAmount(price);

        // then
        Assertions.assertThat(result).isEqualTo(100);
    }

    @Test
    void _20000원의적립은200원() {
        // given
        final int price = 20000;

        // when
        final int result = ratePointService.calculateAmount(price);

        // then
        Assertions.assertThat(result).isEqualTo(200);
    }
}
