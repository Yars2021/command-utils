package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

import java.util.ArrayList;

public class Help extends AbstractCommand {
    private CommandHashMap commandHashMap;

    public Help() {
        super("help", "", "shows the command reference page");
    }

    public void setCommandHashMap(CommandHashMap commandHashMap) {
        this.commandHashMap = commandHashMap;
    }

    @Override
    public CommandResult execute() {
        if (commandHashMap == null) {
            ArrayList<String> output = new ArrayList<>();
            output.add("No command map found");
            return new CommandResult(Status.FAILED, output);
        } else {
            return new CommandResult(Status.OK, commandHashMap.getDescriptionList());
        }
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        Help commandClone = new Help();
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
