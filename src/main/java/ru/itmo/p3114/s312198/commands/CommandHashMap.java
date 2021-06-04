package ru.itmo.p3114.s312198.commands;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHashMap {
    private final HashMap<String, CommandRecord> commandMap = new HashMap<>();
    private final ArrayList<AbstractCommand> commands = new ArrayList<>();

    public CommandHashMap() {
    }

    public CommandHashMap(HashMap<String, CommandRecord> commandMap) {
        this.commandMap.putAll(commandMap);
    }

    public void addCommandRecord(String name, CommandRecord commandRecord) {
        commandMap.put(name, commandRecord);
    }

    public void addCommandRecord(String name, CommandTypes commandType, AbstractCommand command) {
        addCommandRecord(name, new CommandRecord(command, commandType));
    }

    public Boolean containsName(String name) {
        return commandMap.containsKey(name);
    }

    public CommandRecord getCommandRecord(String name) {
        return commandMap.get(name);
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public ArrayList<String> getDocumentation() {
        ArrayList<String> documentation = new ArrayList<>();
        for (String commandName : commandMap.keySet()) {
            documentation.add((commandMap.get(commandName).getCommand().getCommand() +
                    " " + commandMap.get(commandName).getCommand().getInputPattern()).trim() +
                    ": " + commandMap.get(commandName).getCommand().getDocumentation());
        }
        return documentation;
    }
}
