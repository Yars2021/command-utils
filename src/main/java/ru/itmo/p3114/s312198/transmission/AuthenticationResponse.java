package ru.itmo.p3114.s312198.transmission;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private final Boolean allowed;
    private final String serverMessage;

    public AuthenticationResponse(Boolean allowed, String serverMessage) {
        this.allowed = allowed;
        this.serverMessage = serverMessage;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public String getServerMessage() {
        return serverMessage;
    }
}
