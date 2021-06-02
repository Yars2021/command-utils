package ru.itmo.p3114.s312198.structures.builders;

import ru.itmo.p3114.s312198.structures.Coordinates;
import ru.itmo.p3114.s312198.structures.FormOfEducation;
import ru.itmo.p3114.s312198.structures.Person;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.time.LocalDate;

public class StudyGroupBuilder {
    private final StudyGroup studyGroup = new StudyGroup();

    public StudyGroupBuilder addId(Long id) {
        studyGroup.setId(id);
        return this;
    }

    public StudyGroupBuilder addCreatorId(Long creatorId) {
        studyGroup.setCreatorId(creatorId);
        return this;
    }

    public StudyGroupBuilder addName(String name) {
        studyGroup.setName(name);
        return this;
    }

    public StudyGroupBuilder addOwner(String owner) {
        studyGroup.setOwner(owner);
        return this;
    }

    public StudyGroupBuilder addCoordinates(Coordinates coordinates) {
        studyGroup.setCoordinates(coordinates);
        return this;
    }

    public StudyGroupBuilder addCreationDate(LocalDate creationDate) {
        studyGroup.setCreationDate(creationDate);
        return this;
    }

    public StudyGroupBuilder addStudentsCount(Integer studentsCount) {
        studyGroup.setStudentsCount(studentsCount);
        return this;
    }

    public StudyGroupBuilder addShouldBeExpelled(Integer shouldBeExpelled) {
        studyGroup.setShouldBeExpelled(shouldBeExpelled);
        return this;
    }

    public StudyGroupBuilder addTransferredStudents(Integer transferredStudents) {
        studyGroup.setTransferredStudents(transferredStudents);
        return this;
    }

    public StudyGroupBuilder addFormOfEducation(FormOfEducation formOfEducation) {
        studyGroup.setFormOfEducation(formOfEducation);
        return this;
    }

    public StudyGroupBuilder addGroupAdmin(Person groupAdmin) {
        studyGroup.setGroupAdmin(groupAdmin);
        return this;
    }

    public StudyGroup toStudyGroup() {
        return studyGroup;
    }
}
