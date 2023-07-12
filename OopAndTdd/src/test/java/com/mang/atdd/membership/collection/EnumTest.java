package com.mang.atdd.membership.collection;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    void enumTest() {
        PayGroup payGroup = PayGroup.findByPayCode("ACCOUNT");
        Assertions.assertThat(payGroup.name()).isEqualTo("CASH");

    }
}
