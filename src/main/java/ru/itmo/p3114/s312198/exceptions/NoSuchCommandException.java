package ru.itmo.p3114.s312198.exceptions;

public class NoSuchCommandException extends RuntimeException {
    public NoSuchCommandException() {
        super("No such command");
    }

    public NoSuchCommandException(String message) {
        super(message);
    }
}
