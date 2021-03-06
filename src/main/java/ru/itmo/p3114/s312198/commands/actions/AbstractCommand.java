package ru.itmo.p3114.s312198.commands.actions;

import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

abstract public class AbstractCommand implements Serializable {
    protected String command;
    protected String inputPattern;
    protected String description;
    protected ArrayList<String> arguments = new ArrayList<>();
    protected LinkedHashSet<StudyGroup> target;
    protected String owner;

    public AbstractCommand(String command, String inputPattern, String description) {
        this.command = command;
        this.inputPattern = inputPattern;
        this.description = description;
    }

    public void setArguments(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    public void setTarget(LinkedHashSet<StudyGroup> target) {
        this.target = target;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public LinkedHashSet<StudyGroup> getTarget() {
        return target;
    }

    public String getOwner() {
        return owner;
    }

    public String getCommandName() {
        return command;
    }

    public String getInputPattern() {
        return inputPattern;
    }

    public String getDescription() {
        return description;
    }

    abstract public CommandResult execute();

    abstract public AbstractCommand clone();
}
