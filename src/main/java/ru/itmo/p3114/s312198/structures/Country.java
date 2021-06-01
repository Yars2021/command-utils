package ru.itmo.p3114.s312198.structures;

import java.io.Serializable;
import java.util.HashMap;

public enum Country implements Serializable {
    UNDEFINED(0L),
    AUSTRALIA(1L),
    BELARUS(2L),
    GABON(3L),
    KAZAKHSTAN(4L),
    KOREA_NORTH(5L),
    KOREA_SOUTH(6L),
    RUSSIA(7L),
    SENEGAL(8L),
    SERBIA(9L),
    VIETNAM(10L),
    ZIMBABWE(11L);

    private final Long key;
    private final static HashMap<Long, Country> values = new HashMap<>();

    static {
        for (Country country : Country.values()) {
            values.put(country.key, country);
        }
    }

    Country(Long key) {
        this.key = key;
    }

    public Country valueOf(Long key) {
        return values.get(key);
    }

    public static String getValString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Country country : Country.values()) {
            if (country != UNDEFINED) {
                stringBuilder.append(country.toString());
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString().trim();
    }
}
