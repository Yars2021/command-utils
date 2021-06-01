package ru.itmo.p3114.s312198.structures;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x;
    private Double y; //Максимальное значение поля: 426, Поле не может быть null

    public Coordinates() {
    }

    public Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public String toReadableString() {
        return "{" + x + "; " + y + "}";
    }
}
