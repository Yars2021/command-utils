package ru.itmo.p3114.s312198.transmission;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponsePack implements Serializable {
    private final ArrayList<String> output;
    private Boolean allowed;

    public ResponsePack(ArrayList<String> output) {
        this.output = output;
    }

    public ResponsePack(ArrayList<String> output, Boolean allowed) {
        this.output = output;
        this.allowed = allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    public Boolean getAllowed() {
        return allowed;
    }
}
