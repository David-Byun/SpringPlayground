package com.mang.atdd.membership.assertj;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJ {

    @Test
    void filter_test() {

        List<Human> list = new ArrayList<>();

        Human kim = new Human("Kim", 22);
        Human park = new Human("Park", 25);

        list.add(kim);
        list.add(park);

        assertThat(list).filteredOn(human -> human.getName().contains("a")).containsOnly(park);
        assertThat(list).filteredOn("age", notIn(22)).containsOnly(park);
        assertThat(list).extracting("name").contains("Kim", "Park");
        assertThat(list).extracting("name", String.class).contains("Kim", "Park");
        assertThat(list).extracting("name", "age")
                .contains(tuple("Kim", 22),
                        tuple("Park", 25));

    }

    @Test
    void exception_test() {
        //WHEN
        Throwable thrown = catchThrowable(() -> {
            throw new Exception("boom!");
        });

        //THEN
        assertThat(thrown).isInstanceOf(Exception.class).hasMessageContaining("boom");
    }

}
