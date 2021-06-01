package ru.itmo.p3114.s312198.exceptions;

public class InputInterruptedException extends RuntimeException {
    public InputInterruptedException() {
        super("Input interrupted");
    }

    public InputInterruptedException(String message) {
        super(message);
    }
}
