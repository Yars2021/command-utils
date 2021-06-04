package ru.itmo.p3114.s312198.parsers.pairs;

import ru.itmo.p3114.s312198.structures.Person;

public class PersonPair {
    private final Person person;
    private final Integer skipped;

    public PersonPair(Person person, Integer skipped) {
        this.person = person;
        this.skipped = skipped;
    }

    public Person getPerson() {
        return person;
    }

    public Integer getSkipped() {
        return skipped;
    }
}
