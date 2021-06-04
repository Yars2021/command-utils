package ru.itmo.p3114.s312198.parsers.pairs;

import ru.itmo.p3114.s312198.structures.Location;

public class LocationPair {
    private final Location location;
    private final Integer skipped;

    public LocationPair(Location location, Integer skipped) {
        this.location = location;
        this.skipped = skipped;
    }

    public Location getLocation() {
        return location;
    }

    public Integer getSkipped() {
        return skipped;
    }
}
