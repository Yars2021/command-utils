package ru.itmo.p3114.s312198.structures;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.exceptions.InvalidCSVFormatException;
import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.parsers.FieldParser;
import ru.itmo.p3114.s312198.structures.builders.LocationBuilder;
import ru.itmo.p3114.s312198.structures.csv.CSVConvertible;

import java.io.IOException;
import java.io.Serializable;

public class Location implements Serializable, CSVConvertible {
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

    @Override
    public String toCSV() {
        return (x == null ? "" : x) +
                "," + (y == null ? "" : y) +
                "," + (z == null ? "" : z) +
                "," + (name == null ? "" : name);
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
            setX(fieldParser.parseLocationCoordinate(values[0]));
            setY(fieldParser.parseLocationCoordinate(values[1]));
            setZ(fieldParser.parseLocationCoordinate(values[2]));
            setName(fieldParser.parseOptionalName(values[3]));
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
        return "\t\t\tCoordinates: {" + (x == null ? "-" : x) + "; " + (y == null ? "-" : y) + "; " + (z == null ? "-" : z) + "}\n" +
                "\t\t\tName: " + (name == null ? "-" : name);
    }

    public String toReadableString() {
        return "Coordinates: {" + (x == null ? "-" : x) + "; " + (y == null ? "-" : y) + "; " + (z == null ? "-" : z) + "}\n" +
                "Name: " + (name == null ? "-" : name);
    }
}
