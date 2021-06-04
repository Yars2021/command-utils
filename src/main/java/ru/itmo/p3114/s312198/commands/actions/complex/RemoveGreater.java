package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;

public class RemoveGreater extends AbstractComplexCommand implements CollectionInteracting, DatabaseInteracting {
    public RemoveGreater() {
        super("remove_greater", "{studyGroup}", "Removes all the elements that are greater than the argument");
    }

    @Override
    public CommandResult execute() {
        return null;
    }

    @Override
    public AbstractCommand clone() {
        RemoveGreater commandClone = new RemoveGreater();
        commandClone.setComplexArgument(complexArgument);
        commandClone.setArguments(arguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
