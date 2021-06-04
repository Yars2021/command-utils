package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

import java.util.ArrayList;

public class Exit extends AbstractCommand {
    public Exit() {
        super("exit", "", "Exits the application");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        return new CommandResult(Status.OK, output);
    }

    @Override
    public AbstractCommand clone() {
        Exit commandClone = new Exit();
        commandClone.setArguments(arguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
