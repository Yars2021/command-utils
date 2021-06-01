package ru.itmo.p3114.s312198.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Invalid input, unable to parse");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
