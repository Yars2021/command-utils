package ru.itmo.p3114.s312198.structures.builders;

import ru.itmo.p3114.s312198.structures.Location;

public class LocationBuilder {
    private final Location location = new Location();

    public LocationBuilder addX(Float x) {
        location.setX(x);
        return this;
    }

    public LocationBuilder addY(Float y) {
        location.setY(y);
        return this;
    }

    public LocationBuilder addZ(Float z) {
        location.setZ(z);
        return this;
    }

    public LocationBuilder addLocationCoordinates(Location location) {
        this.location.setX(location.getX());
        this.location.setY(location.getY());
        this.location.setZ(location.getZ());
        return this;
    }

    public LocationBuilder addName(String name) {
        location.setName(name);
        return this;
    }

    public Location toLocation() {
        return location;
    }
}
