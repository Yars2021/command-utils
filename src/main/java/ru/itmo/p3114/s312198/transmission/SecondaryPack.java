package ru.itmo.p3114.s312198.transmission;

import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.Serializable;

public class SecondaryPack implements Serializable {
    private StudyGroup complexArgument;
    private final User owner;

    public SecondaryPack(User owner) {
        complexArgument = null;
        this.owner = owner;
    }

    public SecondaryPack(StudyGroup complexArgument, User owner) {
        this.complexArgument = complexArgument;
        this.owner = owner;
    }

    public void setComplexArgument(StudyGroup complexArgument) {
        this.complexArgument = complexArgument;
    }

    public StudyGroup getComplexArgument() {
        return complexArgument;
    }

    public User getOwner() {
        return owner;
    }
}
