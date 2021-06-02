package ru.itmo.p3114.s312198.commands.results;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandResult implements Serializable {
    private Status status;
    private ArrayList<String> output;

    public CommandResult() {
    }

    public CommandResult(Status status, ArrayList<String> output) {
        this.status = status;
        this.output = output;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOutput(ArrayList<String> output) {
        this.output = output;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<String> getOutput() {
        return output;
    }
}
