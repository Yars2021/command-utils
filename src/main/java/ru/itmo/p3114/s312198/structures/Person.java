package ru.itmo.p3114.s312198.structures;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.exceptions.InvalidCSVFormatException;
import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.parsers.FieldParser;
import ru.itmo.p3114.s312198.structures.builders.LocationBuilder;
import ru.itmo.p3114.s312198.structures.builders.PersonBuilder;
import ru.itmo.p3114.s312198.structures.csv.CSVConvertible;

import java.io.IOException;
import java.io.Serializable;

public class Person implements Serializable, CSVConvertible {
    private Long id;
    private String name;
    private Integer height;
    private Color hairColor;
    private Country nationality;
    private Location location;
    public static final Person emptyPerson = new PersonBuilder()
            .addId(null)
            .addName(null)
            .addHeight(null)
            .addHairColor(null)
            .addNationality(null)
            .addLocation(Location.emptyLocation)
            .toPerson();

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

    @Override
    public String toCSV() {
        return    (id == null ? "" : id) +
            "," + (name == null ? "" : name) +
            "," + (height == null ? "" : height) +
            "," + (hairColor == null ? "" : hairColor) +
            "," + (nationality == null ? "" : nationality) +
            "," + (location == null ? Location.emptyLocation.toCSV() : location.toCSV());
    }

    @Override
    public CSVConvertible fromCSV(String csv) throws InvalidCSVFormatException {
        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();
        try {
            FieldParser fieldParser = new FieldParser();
            String[] values = csvParser.parseLine(csv);
            setId(fieldParser.parseId(values[0]));
            setName(fieldParser.parseName(values[1]));
            setHeight(fieldParser.parseNaturalNumber(values[2]));
            setHairColor(fieldParser.parseOptionalHairColor(values[3]));
            setNationality(fieldParser.parseOptionalNationality(values[4]));
            try {
                setLocation(new LocationBuilder()
                        .addX(fieldParser.parseLocationCoordinate(values[5]))
                        .addY(fieldParser.parseLocationCoordinate(values[6]))
                        .addZ(fieldParser.parseLocationCoordinate(values[7]))
                        .addName(fieldParser.parseOptionalName(values[8]))
                        .toLocation());
            } catch (InvalidInputException invalidInputException) {
                setLocation(null);
            }
        } catch (InvalidInputException invalidInputException) {
            throw new InvalidCSVFormatException(invalidInputException.getMessage());
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new InvalidCSVFormatException("Not enough fields in the CSV line");
        } catch (IOException | InputInterruptedException exception) {
            return null;
        }
        return this;
    }

    public String toReadableShiftedString() {
        return  "\t\tID: " + (id == null ? "-" : id) + "\n" +
                "\t\tName: " + (name == null ? "-" : name) + "\n" +
                "\t\tHeight: " + (height == null ? "-" : height) + "\n" +
                "\t\tHair color: " + (hairColor == null ? "-" : hairColor) + "\n" +
                "\t\tNationality: " + (nationality == null ? "-" : nationality) + "\n" +
                "\t\tLocation: " + (location == null ? "-" : "\n" + location.toReadableShiftedString());
    }

    public String toReadableString() {
        return  "ID: " + (id == null ? "-" : id) + "\n" +
                "\tName: " + (name == null ? "-" : name) + "\n" +
                "\tHeight: " + (height == null ? "-" : height) + "\n" +
                "\tHair color: " + (hairColor == null ? "-" : hairColor) + "\n" +
                "\tNationality: " + (nationality == null ? "-" : nationality) + "\n" +
                "\tLocation: " + (location == null ? "-" : "\n" + location.toReadableShiftedString());
    }
}
