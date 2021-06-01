package ru.itmo.p3114.s312198.commands;

import ru.itmo.p3114.s312198.commands.actions.*;

import java.util.ArrayList;

public class CommandList {
    private final ArrayList<AbstractCommand> commands = new ArrayList<>();

    public CommandList() {
        commands.add(new Help());
        commands.add(new Info());
        commands.add(new Show());
        commands.add(new Add());
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public ArrayList<String> getDocumentation() {
        ArrayList<String> documentation = new ArrayList<>();
        for (AbstractCommand command : commands) {
            documentation.add(command.getCommand() + ": " +
                    command.getDocumentation() + ". Pattern: " + command.getInputPattern());
        }
        return documentation;
    }
}
