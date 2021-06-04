package ru.itmo.p3114.s312198.parsers;

import ru.itmo.p3114.s312198.exceptions.InvalidCollectionException;
import ru.itmo.p3114.s312198.exceptions.InvalidFileFormatException;
import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.parsers.pairs.LocationPair;
import ru.itmo.p3114.s312198.parsers.pairs.PersonPair;
import ru.itmo.p3114.s312198.parsers.pairs.StudyGroupPair;
import ru.itmo.p3114.s312198.structures.Location;
import ru.itmo.p3114.s312198.structures.Person;
import ru.itmo.p3114.s312198.structures.builders.LocationBuilder;
import ru.itmo.p3114.s312198.structures.builders.PersonBuilder;
import ru.itmo.p3114.s312198.structures.builders.StudyGroupBuilder;

import java.util.ArrayList;

public class ComplexArgumentParser {
    public LocationPair parseLinesIntoLocation(ArrayList<String> lines, Integer startingPoint) throws InvalidFileFormatException, InvalidInputException {
        if (lines == null) {
            throw new InvalidCollectionException();
        } else {
            try {
                FieldParser fieldParser = new FieldParser();
                return new LocationPair(new LocationBuilder()
                        .addLocationCoordinates(fieldParser.parseLocationCoordinates(lines.get(startingPoint)))
                        .addName(fieldParser.parseOptionalName(lines.get(startingPoint + 1)))
                        .toLocation(), 2);

            } catch (IndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new InvalidFileFormatException("Not enough lines, unable to read");
            }
        }
    }

    public PersonPair parseLinesIntoPerson(ArrayList<String> lines, Integer startingPoint) throws InvalidFileFormatException, InvalidInputException {
        if (lines == null) {
            throw new InvalidCollectionException();
        } else {
            LocationPair locationPair = null;
            Location location;
            try {
                locationPair = parseLinesIntoLocation(lines, startingPoint + 4);
                location = locationPair.getLocation();
            } catch (InvalidInputException | InvalidFileFormatException exception) {
                location = null;
            }
            try {
                FieldParser fieldParser = new FieldParser();
                return new PersonPair(new PersonBuilder()
                        .addName(fieldParser.parseName(lines.get(startingPoint)))
                        .addHeight(fieldParser.parseNaturalNumber(lines.get(startingPoint + 1)))
                        .addHairColor(fieldParser.parseOptionalHairColor(lines.get(startingPoint + 2)))
                        .addNationality(fieldParser.parseOptionalNationality(lines.get(startingPoint + 3)))
                        .addLocation(location)
                        .toPerson(), (locationPair == null ? 0 : locationPair.getSkipped()) + 4);
            } catch (IndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new InvalidFileFormatException("Not enough lines, unable to read");
            }
        }
    }

    public StudyGroupPair parseLinesIntoStudyGroup(ArrayList<String> lines, Integer startingPoint) throws InvalidFileFormatException, InvalidInputException {
        if (lines == null) {
            throw new InvalidCollectionException();
        } else {
            PersonPair personPair = null;
            Person person;
            try {
                FieldParser fieldParser = new FieldParser();
                try {
                    personPair = parseLinesIntoPerson(lines, startingPoint + 6);
                    person = personPair.getPerson();
                } catch (InvalidInputException | InvalidFileFormatException exception) {
                    person = null;
                }
                return new StudyGroupPair(new StudyGroupBuilder()
                        .addName(fieldParser.parseName(lines.get(startingPoint)))
                        .addCoordinates(fieldParser.parseCoordinates(lines.get(startingPoint + 1)))
                        .addStudentsCount(fieldParser.parseNaturalNumber(lines.get(startingPoint + 2)))
                        .addShouldBeExpelled(fieldParser.parseCappedNaturalNumber(lines.get(startingPoint + 3),
                                fieldParser.parseNaturalNumber(lines.get(startingPoint + 2))))
                        .addTransferredStudents(fieldParser.parseNaturalNumber(lines.get(startingPoint + 4)))
                        .addFormOfEducation(fieldParser.parseFromOfEducation(lines.get(startingPoint + 5)))
                        .addGroupAdmin(person)
                        .toStudyGroup(), (personPair == null ? 0 : personPair.getSkipped()) + 6);
            } catch (IndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                throw new InvalidFileFormatException("Not enough lines, unable to read");
            }
        }
    }
}
