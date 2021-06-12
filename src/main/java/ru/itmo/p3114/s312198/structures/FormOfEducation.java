package ru.itmo.p3114.s312198.structures;

import java.io.Serializable;
import java.util.HashMap;

public enum FormOfEducation implements Serializable {
    FULL_TIME_EDUCATION(1L),
    DISTANCE_EDUCATION(2L),
    EVENING_CLASSES(3L);

    private final Long key;
    private final static HashMap<Long, FormOfEducation> values = new HashMap<>();

    static {
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            values.put(formOfEducation.key, formOfEducation);
        }
    }

    FormOfEducation(Long key) {
        this.key = key;
    }

    public static FormOfEducation valueOf(Long key) {
        return values.get(key);
    }

    public Long getKey() {
        return key;
    }

    public static String getValString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (FormOfEducation formOfEducation : FormOfEducation.values()) {
            stringBuilder.append(formOfEducation.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
