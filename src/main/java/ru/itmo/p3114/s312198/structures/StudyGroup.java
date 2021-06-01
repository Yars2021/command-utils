package ru.itmo.p3114.s312198.structures;

import ru.itmo.p3114.s312198.structures.builders.PersonBuilder;

import java.io.Serializable;
import java.time.LocalDate;

public class StudyGroup implements Serializable {
    private Long id;
    private Long creatorId;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Integer studentsCount;
    private Integer shouldBeExpelled;
    private Integer transferredStudents;
    private FormOfEducation formOfEducation;
    private Person groupAdmin;

    public StudyGroup() {
    }

    public StudyGroup(Long id, Long creatorId, String name, Coordinates coordinates,
                      LocalDate creationDate, Integer studentsCount, Integer shouldBeExpelled,
                      Integer transferredStudents, FormOfEducation formOfEducation, Person groupAdmin) {
        this.id = id;
        this.creatorId = creatorId;
        this.name = name;
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

    public String toCSV() {
        Person emptyPerson = new PersonBuilder()
                .addId(null)
                .addName(null)
                .addHeight(null)
                .addHairColor(null)
                .addNationality(null)
                .addLocation(null)
                .toPerson();
        return    (id == null ? " " : id) +
            "," + (creatorId == null ? " " : creatorId) +
            "," + (name == null ? " " : name) +
            "," + (coordinates == null ? " " : coordinates) +
            "," + (creationDate == null ? " " : creationDate) +
            "," + (studentsCount == null ? " " : studentsCount) +
            "," + (shouldBeExpelled == null ? " " : shouldBeExpelled) +
            "," + (transferredStudents == null ? " " : transferredStudents) +
            "," + (formOfEducation == null ? " " : formOfEducation) +
            "," + (groupAdmin == null ? emptyPerson.toCSV() : groupAdmin.toCSV()) + ";";
    }

    public String toReadableString() {
        return  "ID: " + id + "\n" +
                "\tCreator ID: " + creatorId + "\n" +
                "\tName: " + name + "\n" +
                "\tCoordinates: " + (coordinates == null ? "-" : coordinates.toReadableString()) + "\n" +
                "\tCreation date: " + creationDate + "\n" +
                "\tStudents count: " + studentsCount + "\n" +
                "\tShould be expelled: " + shouldBeExpelled + "\n" +
                "\tTransferred students: " + transferredStudents + "\n" +
                "\tForm of education: " + formOfEducation + "\n" +
                "\tgroupAdmin:\n" + (groupAdmin == null ? "-" : groupAdmin.toReadableString());
    }
}
