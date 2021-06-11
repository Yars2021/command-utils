package ru.itmo.p3114.s312198.transmission;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private final Boolean allowed;
    private final String serverMessage;
    private final User user;

    public AuthenticationResponse(Boolean allowed, String serverMessage, User user) {
        this.allowed = allowed;
        this.serverMessage = serverMessage;
        this.user = user;
    }

    public Boolean allowed() {
        return allowed;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public User getUser() {
        return user;
    }
}
