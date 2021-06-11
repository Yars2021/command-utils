package ru.itmo.p3114.s312198.exceptions;

import java.io.IOException;

public class TransmissionException extends IOException {
    public TransmissionException() {
        super("Transmission failed");
    }

    public TransmissionException(String message) {
        super(message);
    }
}
