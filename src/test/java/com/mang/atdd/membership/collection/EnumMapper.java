package com.mang.atdd.membership.collection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumMapper {

    private Map<String, List<EnumMapperValue>> factory = new LinkedHashMap<>();

    public void put(String key, Class<? extends EnumMapperType> e) {
        factory.put(key, toEnumValues(e));
    }

    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> e) {

        return null;
    }
}
