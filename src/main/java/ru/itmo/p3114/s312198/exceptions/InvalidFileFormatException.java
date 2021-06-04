package ru.itmo.p3114.s312198.exceptions;

public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException() {
        super("Invalid file format");
    }

    public InvalidFileFormatException(String message) {
        super(message);
    }
}
