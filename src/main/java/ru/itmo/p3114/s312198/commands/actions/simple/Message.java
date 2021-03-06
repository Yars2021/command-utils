package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Message extends AbstractCommand {
    public Message() {
        super("msg", "<message>", "Prints a message");
    }

    public void setArgument(String argument) {
        arguments.add(argument);
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (arguments != null) {
            output.add(arguments.stream().map(arg -> arg + " ").collect(Collectors.joining()));
        }
        return new CommandResult(Status.OK, output);
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        Message commandClone = new Message();
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
