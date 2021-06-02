package ru.itmo.p3114.s312198.structures;

import java.io.Serializable;

public class Location implements Serializable {
    private Float x;
    private Float y;
    private Float z;
    private String name;

    public Location() {
    }

    public Location(Float x, Float y, Float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public String toCSV() {
        return    (x == null ? "" : x) +
            "," + (y == null ? "" : y) +
            "," + (z == null ? "" : z) +
            "," + (name == null ? "" : name);
    }

    public String toReadableString() {
        return  "\t\t\tCoordinates: {" + (x == null ? "-" : x) + "; " + (y == null ? "-" : y) + "; " + (z == null ? "-" : z) + "}\n" +
                "\t\t\tName: " + (name == null ? "-" : name);
    }
}
