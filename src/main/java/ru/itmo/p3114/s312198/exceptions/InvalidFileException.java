package ru.itmo.p3114.s312198.exceptions;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException() {
        super("Invalid file");
    }

    public InvalidFileException(String message) {
        super(message);
    }
}
