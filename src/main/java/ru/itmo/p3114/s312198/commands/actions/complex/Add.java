package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;

public class Add extends AbstractComplexCommand implements CollectionInteracting, DatabaseInteracting {
    public Add() {
        super("add", "{studyGroup}", "Adds an element to the collection");
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
                if (owner == null) {
                    output.add("No owner found");
                    return new CommandResult(Status.FAILED, output);
                } else {
                    complexArgument.setOwner(owner);
                    target.add(complexArgument);
                    output.add("Element added successfully");
                    return new CommandResult(Status.OK, output);
                }
            }
        }
    }

    @Override
    public AbstractCommand clone() {
        Add commandClone = new Add();
        commandClone.setComplexArgument(complexArgument);
        commandClone.setArguments(arguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
