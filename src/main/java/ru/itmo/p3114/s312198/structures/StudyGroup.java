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
import java.time.LocalDate;

public class StudyGroup implements Serializable, CSVConvertible {
    private Long id;
    private Long creatorId;
    private String name;
    private String owner;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Integer studentsCount;
    private Integer shouldBeExpelled;
    private Integer transferredStudents;
    private FormOfEducation formOfEducation;
    private Person groupAdmin;

    public StudyGroup() {
    }

    public StudyGroup(Long id, Long creatorId, String name, String owner, Coordinates coordinates,
                      LocalDate creationDate, Integer studentsCount, Integer shouldBeExpelled,
                      Integer transferredStudents, FormOfEducation formOfEducation, Person groupAdmin) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
        this.owner = owner;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setStudentsCount(Integer studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public void setTransferredStudents(Integer transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public Long getId() {
        return id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public Integer getTransferredStudents() {
        return transferredStudents;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public String toCSV() {
        return    (name == null ? "" : name) +
            "," + (coordinates == null || coordinates.getX() == null ? "" : coordinates.getX()) +
            "," + (coordinates == null || coordinates.getY() == null ? "" : coordinates.getY()) +
            "," + (creationDate == null ? "" : creationDate) +
            "," + (studentsCount == null ? "" : studentsCount) +
            "," + (shouldBeExpelled == null ? "" : shouldBeExpelled) +
            "," + (transferredStudents == null ? "" : transferredStudents) +
            "," + (formOfEducation == null ? "" : formOfEducation) +
            "," + (groupAdmin == null ? Person.emptyPerson.toCSV() : groupAdmin.toCSV());
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
            setName(fieldParser.parseName(values[0]));
            setCoordinates(new Coordinates(fieldParser.parseStudyGroupX(values[1]), fieldParser.parseStudyGroupY(values[2])));
            setCreationDate(fieldParser.parseDate(values[3]));
            setStudentsCount(fieldParser.parseNaturalNumber(values[4]));
            if (fieldParser.parseNaturalNumber(values[5]) > studentsCount) {
                throw new InvalidCSVFormatException("ShouldBeExpelled value cannot be greater than StudentsCount");
            }
            setShouldBeExpelled(fieldParser.parseNaturalNumber(values[5]));
            setTransferredStudents(fieldParser.parseNaturalNumber(values[6]));
            setFormOfEducation(fieldParser.parseFromOfEducation(values[7]));
            try {
                Person person = new PersonBuilder()
                        .addId(fieldParser.parseId(values[8]))
                        .addName(fieldParser.parseName(values[9]))
                        .addHeight(fieldParser.parseNaturalNumber(values[10]))
                        .addHairColor(fieldParser.parseOptionalHairColor(values[11]))
                        .addNationality(fieldParser.parseOptionalNationality(values[12]))
                        .toPerson();
                try {
                    person.setLocation(new LocationBuilder()
                            .addX(fieldParser.parseLocationCoordinate(values[13]))
                            .addY(fieldParser.parseLocationCoordinate(values[14]))
                            .addZ(fieldParser.parseLocationCoordinate(values[15]))
                            .addName(fieldParser.parseOptionalName(values[16]))
                            .toLocation());
                } catch (InvalidInputException invalidInputException) {
                    person.setLocation(null);
                }
                setGroupAdmin(person);
            } catch (InvalidInputException invalidInputException) {
                setGroupAdmin(null);
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

    public String toInfoLine() {
        return id + ", " + creatorId + ", " + owner + ": " + toCSV();
    }

    public String toReadableString() {
        return  "ID: " + (id == null ? "-" : id) + "\n" +
                "\tCreator ID: " + (creatorId == null ? "-" : creatorId) + "\n" +
                "\tName: " + (name == null ? "-" : name) + "\n" +
                "\tOwner: " + (owner == null ? "-" : owner) + "\n" +
                "\tCoordinates: " + (coordinates == null ? "-" : coordinates.toReadableString()) + "\n" +
                "\tCreation date: " + (creationDate == null ? "-" : creationDate) + "\n" +
                "\tStudents count: " + (studentsCount == null ? "-" : studentsCount) + "\n" +
                "\tShould be expelled: " + (shouldBeExpelled == null ? "-" : shouldBeExpelled) + "\n" +
                "\tTransferred students: " + (transferredStudents == null ? "-" : transferredStudents) + "\n" +
                "\tForm of education: " + (formOfEducation == null ? "-" : formOfEducation) + "\n" +
                "\tGroup admin: " + (groupAdmin == null ? "-" : "\n" + groupAdmin.toReadableShiftedString());
    }
}
