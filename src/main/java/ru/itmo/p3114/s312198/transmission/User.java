package ru.itmo.p3114.s312198.transmission;

import java.io.Serializable;

public class User implements Serializable {
    private final String username;
    private final String credentials;
    private final Long id;

    public User(String username, String credentials, Long id) {
        this.username = username;
        this.credentials = credentials;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getCredentials() {
        return credentials;
    }

    public Long getId() {
        return id;
    }
}
