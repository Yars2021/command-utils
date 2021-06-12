package ru.itmo.p3114.s312198.structures;

import java.io.Serializable;
import java.util.HashMap;

public enum Color implements Serializable {
    BLACK(1L),
    WHITE(2L),
    RED(3L),
    BROWN(4L);

    private final Long key;
    private final static HashMap<Long, Color> values = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            values.put(color.key, color);
        }
    }

    Color(Long key) {
        this.key = key;
    }

    public static Color valueOf(Long key) {
        return values.get(key);
    }

    public Long getKey() {
        return key;
    }

    public static String getValString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Color color : Color.values()) {
            stringBuilder.append(color.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
