package ru.itmo.p3114.s312198.parsers;

import ru.itmo.p3114.s312198.exceptions.ConsoleException;
import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.io.ConsoleReader;
import ru.itmo.p3114.s312198.structures.Color;
import ru.itmo.p3114.s312198.structures.Coordinates;
import ru.itmo.p3114.s312198.structures.Country;
import ru.itmo.p3114.s312198.structures.FormOfEducation;
import ru.itmo.p3114.s312198.structures.Location;
import ru.itmo.p3114.s312198.structures.Person;
import ru.itmo.p3114.s312198.structures.StudyGroup;
import ru.itmo.p3114.s312198.structures.builders.PersonBuilder;
import ru.itmo.p3114.s312198.structures.builders.StudyGroupBuilder;

import java.io.IOException;

public class RequestParser {
    public String requestUserInput(String message, ConsoleReader consoleReader) throws IOException {
        System.out.println(message);
        try {
            return consoleReader.readConsoleLine();
        } catch (ConsoleException consoleException) {
            return consoleReader.readLine();
        }
    }

    public Boolean requestExtension(String message, ConsoleReader consoleReader) throws IOException {
        return "Y".equalsIgnoreCase(requestUserInput(message, consoleReader));
    }

    public String requestName(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseName(requestUserInput(message, consoleReader));
    }

    public Coordinates requestCoordinates(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseCoordinates(requestUserInput(message, consoleReader));
    }

    public Integer requestNaturalNumber(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseNaturalNumber(requestUserInput(message, consoleReader));
    }

    public FormOfEducation requestFormOfEducation(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseFromOfEducation(requestUserInput(message, consoleReader));
    }

    public Color requestHairColor(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseHairColor(requestUserInput(message, consoleReader));
    }

    public Country requestNationality(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseNationality(requestUserInput(message, consoleReader));
    }

    public Location requestLocationCoordinates(String message, ConsoleReader consoleReader) throws IOException, InvalidInputException, InputInterruptedException {
        return new FieldParser().parseLocationCoordinates(requestUserInput(message, consoleReader));
    }

    public Location requestLocation(ConsoleReader consoleReader) throws InputInterruptedException {
        Location location = null;
        String name = null;
        try {
            while (location == null) {
                try {
                    location = requestLocationCoordinates("Enter location coordinates (3 float values divided by \" \"):", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            if (requestExtension("Does the location have a name (Y/N)?", consoleReader)) {
                while (name == null) {
                    try {
                        name = requestName("Enter location name:", consoleReader);
                    } catch (InvalidInputException invalidInputException) {
                        System.out.println(invalidInputException.getMessage());
                    }
                }
                location.setName(name);
            }
        } catch (IOException ioException) {
            System.out.println("Unable to read");
        }
        return location;
    }

    public Person requestPerson(ConsoleReader consoleReader) throws InputInterruptedException {
        String name = null;
        Integer height = null;
        Color hairColor = null;
        Country nationality = null;
        Location location = null;
        try {
            while (name == null) {
                try {
                    name = requestName("Enter person's name:", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (height == null) {
                try {
                    height = requestNaturalNumber("Enter person's height (a natural number):", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (hairColor == null) {
                try {
                    hairColor = requestHairColor("Enter person's hair color (" + Color.getValString() + "):", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (nationality == null) {
                try {
                    nationality = requestNationality("Enter person's nationality (" + Country.getValString() + "):", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            if (requestExtension("Does the person have a location (Y/N)", consoleReader)) {
                location = requestLocation(consoleReader);
            }
        } catch (IOException ioException) {
            System.out.println("Unable to read");
        }
        return new PersonBuilder()
                .addName(name)
                .addHeight(height)
                .addHairColor(hairColor)
                .addNationality(nationality)
                .addLocation(location)
                .toPerson();
    }

    public StudyGroup requestStudyGroup(ConsoleReader consoleReader) throws InputInterruptedException {
        String name = null;
        Coordinates coordinates = null;
        Integer studentsCount = null;
        Integer shouldBeExpelled = null;
        Integer transferredStudents = null;
        FormOfEducation formOfEducation = null;
        Person groupAdmin = null;
        try {
            while (name == null) {
                try {
                    name = requestName("Enter study group name:", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (coordinates == null) {
                try {
                    coordinates = requestCoordinates("Enter study group coordinates (an Integer number and a Double number between 0 and 426 divided by \" \"):", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (studentsCount == null) {
                try {
                    studentsCount = requestNaturalNumber("Enter study group students count:", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (shouldBeExpelled == null || shouldBeExpelled > studentsCount) {
                try {
                    shouldBeExpelled = requestNaturalNumber("Enter the number of students, who should be expelled:", consoleReader);
                    if (shouldBeExpelled > studentsCount) {
                        System.out.println("Invalid input, this number cannot be greater than students count");
                    }
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (transferredStudents == null) {
                try {
                    transferredStudents = requestNaturalNumber("Enter the number of transferred students:", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            while (formOfEducation == null) {
                try {
                    formOfEducation = requestFormOfEducation("Enter study group form of education (" +
                            FormOfEducation.getValString() + "):", consoleReader);
                } catch (InvalidInputException invalidInputException) {
                    System.out.println(invalidInputException.getMessage());
                }
            }
            if (requestExtension("Does the group have an admin (Y/N)", consoleReader)) {
                groupAdmin = requestPerson(consoleReader);
            }
        } catch (IOException ioException) {
            System.out.println("Unable to read");
        }
        return new StudyGroupBuilder()
                .addName(name)
                .addCoordinates(coordinates)
                .addStudentsCount(studentsCount)
                .addShouldBeExpelled(shouldBeExpelled)
                .addTransferredStudents(transferredStudents)
                .addFormOfEducation(formOfEducation)
                .addGroupAdmin(groupAdmin)
                .toStudyGroup();
    }
}
