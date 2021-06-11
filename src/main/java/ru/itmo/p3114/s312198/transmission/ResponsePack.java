package ru.itmo.p3114.s312198.transmission;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponsePack implements Serializable {
    private final ArrayList<String> output;

    public ResponsePack(ArrayList<String> output) {
        this.output = output;
    }

    public ArrayList<String> getOutput() {
        return output;
    }
}
