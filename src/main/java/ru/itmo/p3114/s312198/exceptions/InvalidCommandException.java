package ru.itmo.p3114.s312198.exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("Invalid command");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
