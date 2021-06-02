package ru.itmo.p3114.s312198.exceptions;

public class InvalidCommandTypeException extends RuntimeException {
    public InvalidCommandTypeException() {
        super("Invalid command type");
    }

    public InvalidCommandTypeException(String message) {
        super(message);
    }
}
