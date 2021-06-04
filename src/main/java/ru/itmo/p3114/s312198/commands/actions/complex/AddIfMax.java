package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;

public class AddIfMax extends AbstractComplexCommand implements CollectionInteracting, DatabaseInteracting {
    public AddIfMax() {
        super("add_if_max", "{studyGroup}", "Adds an element to the collection if it's greater than the others");
    }

    @Override
    public CommandResult execute() {
        return null;
    }

    @Override
    public AbstractCommand clone() {
        AddIfMax commandClone = new AddIfMax();
        commandClone.setComplexArgument(complexArgument);
        commandClone.setArguments(arguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
