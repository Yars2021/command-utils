package ru.itmo.p3114.s312198.exceptions;

public class InitializationException extends RuntimeException {
    public InitializationException() {
        super("Initialization exception");
    }

    public InitializationException(String message) {
        super(message);
    }
}
