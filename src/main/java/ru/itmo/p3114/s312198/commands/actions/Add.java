package ru.itmo.p3114.s312198.commands.actions;

import ru.itmo.p3114.s312198.commands.CommandResult;
import ru.itmo.p3114.s312198.commands.Status;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;

public class Add extends AbstractComplexCommand {
    public Add() {
        super("add", "{arg}", "Adds an element to the collection");
    }

    @Override
    public void setComplexArgument(StudyGroup complexArgument) {
        super.setComplexArgument(complexArgument);
    }

    @Override
    public StudyGroup getComplexArgument() {
        return super.getComplexArgument();
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("No collection found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (complexArgument == null) {
                output.add("Invalid complex argument");
                return new CommandResult(Status.FAILED, output);
            } else {
                target.add(complexArgument);
                output.add("Element added successfully");
                return new CommandResult(Status.OK, output);
            }
        }
    }
}