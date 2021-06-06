package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;

public class RemoveGreater extends AbstractComplexCommand implements CollectionInteracting, DatabaseInteracting {
    public RemoveGreater() {
        super("remove_greater", "{studyGroup}", "Removes all the elements that are greater than the argument");
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
                    Integer lastSize = target.size();
                    target.removeIf(studyGroup -> studyGroup != null && owner.equals(studyGroup.getOwner()) &&
                            studyGroup.compareTo(complexArgument) > 0);
                    output.add("Successfully removed " + (lastSize - target.size()) + " elements");
                    return new CommandResult(Status.OK, output);
                }
            }
        }
    }

    @Override
    public Boolean canExecute() {
        return Boolean.TRUE;
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        RemoveGreater commandClone = new RemoveGreater();
        commandClone.setComplexArgument(complexArgument);
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
