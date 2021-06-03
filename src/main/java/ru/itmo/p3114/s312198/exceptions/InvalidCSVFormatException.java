package ru.itmo.p3114.s312198.exceptions;

public class InvalidCSVFormatException extends RuntimeException {
    public InvalidCSVFormatException() {
        super("Invalid CSV line for this type of structure");
    }

    public InvalidCSVFormatException(String message) {
        super(message);
    }
}
