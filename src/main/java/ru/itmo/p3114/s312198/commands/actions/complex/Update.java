package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;

public class Update extends AbstractComplexCommand implements CollectionInteracting, DatabaseInteracting {
    public Update() {
        super("update", "<id> {studyGroup}", "Updates a record by it's id");
    }

    @Override
    public CommandResult execute() {
        return null;
    }

    @Override
    public AbstractCommand clone() {
        Update commandClone = new Update();
        commandClone.setComplexArgument(complexArgument);
        commandClone.setArguments(arguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
