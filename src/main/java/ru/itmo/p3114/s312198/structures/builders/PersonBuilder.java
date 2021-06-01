package ru.itmo.p3114.s312198.structures.builders;

import ru.itmo.p3114.s312198.structures.Color;
import ru.itmo.p3114.s312198.structures.Country;
import ru.itmo.p3114.s312198.structures.Location;
import ru.itmo.p3114.s312198.structures.Person;

public class PersonBuilder {
    private final Person person = new Person();

    public PersonBuilder addId(Long id) {
        person.setId(id);
        return this;
    }

    public PersonBuilder addName(String name) {
        person.setName(name);
        return this;
    }
    public PersonBuilder addHeight(Integer height) {
        person.setHeight(height);
        return this;
    }
    public PersonBuilder addHairColor(Color color) {
        person.setHairColor(color);
        return this;
    }
    public PersonBuilder addNationality(Country country) {
        person.setNationality(country);
        return this;
    }
    public PersonBuilder addLocation(Location location) {
        person.setLocation(location);
        return this;
    }

    public Person toPerson() {
        return person;
    }
}
