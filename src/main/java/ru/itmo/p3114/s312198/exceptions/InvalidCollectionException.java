package ru.itmo.p3114.s312198.exceptions;

public class InvalidCollectionException extends RuntimeException {
    public InvalidCollectionException() {
        super("Invalid collection");
    }

    public InvalidCollectionException(String message) {
        super(message);
    }
}
