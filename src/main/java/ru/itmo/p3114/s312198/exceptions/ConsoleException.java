package ru.itmo.p3114.s312198.exceptions;

public class ConsoleException extends RuntimeException {
    public ConsoleException() {
        super("invalid console");
    }

    public ConsoleException(String message) {
        super(message);
    }
}
