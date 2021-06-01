package ru.itmo.p3114.s312198.commands.actions;

import ru.itmo.p3114.s312198.commands.CommandResult;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

abstract public class AbstractCommand implements Serializable {
    protected String command;
    protected String inputPattern;
    protected String documentation;
    protected ArrayList<String> arguments;
    protected LinkedHashSet<StudyGroup> target;

    public AbstractCommand(String command, String inputPattern, String documentation) {
        this.command = command;
        this.inputPattern = inputPattern;
        this.documentation = documentation;
    }

    public void setArguments(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    public void setTarget(LinkedHashSet<StudyGroup> target) {
        this.target = target;
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public LinkedHashSet<StudyGroup> getTarget() {
        return target;
    }

    public String getCommand() {
        return command;
    }

    public String getInputPattern() {
        return inputPattern;
    }

    public String getDocumentation() {
        return documentation;
    }

    abstract public CommandResult execute();
}
