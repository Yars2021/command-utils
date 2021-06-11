package ru.itmo.p3114.s312198.transmission;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private final String type;
    private final User user;

    public AuthenticationRequest(String type, User user) {
        this.type = type;
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public User getUser() {
        return user;
    }
}
