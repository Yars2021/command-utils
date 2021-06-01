package ru.itmo.p3114.s312198.structures;

import ru.itmo.p3114.s312198.structures.builders.LocationBuilder;

import java.io.Serializable;

public class Person implements Serializable {
    private Long id;
    private String name;
    private Integer height;
    private Color hairColor;
    private Country nationality;
    private Location location;

    public Person() {
    }

    public Person(Long id, String name, Integer height, Color hairColor,
                  Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getHeight() {
        return height;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public String toCSV() {
        Location emptyLocation = new LocationBuilder()
                .addX(null)
                .addY(null)
                .addZ(null)
                .addName(null)
                .toLocation();
        return    (id == null ? " " : id) +
            "," + (name == null ? " " : name) +
            "," + (height == null ? " " : height) +
            "," + (hairColor == null ? " " : hairColor) +
            "," + (nationality == null ? " " : nationality) +
            "," + (location == null ? emptyLocation.toCSV() : location.toCSV());
    }

    public String toReadableString() {
        return  "\t\tID: " + id + "\n" +
                "\t\tName: " + name + "\n" +
                "\t\tHeight: " + height + "\n" +
                "\t\tHair color: " + hairColor + "\n" +
                "\t\tNationality: " + nationality + "\n" +
                "\t\tLocation: " + (location == null ? "-" : location.toReadableString());
    }
}
