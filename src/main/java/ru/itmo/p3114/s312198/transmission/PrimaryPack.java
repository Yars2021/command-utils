package ru.itmo.p3114.s312198.transmission;

import ru.itmo.p3114.s312198.commands.CommandRecord;

import java.io.Serializable;
import java.util.ArrayList;

public class PrimaryPack implements Serializable {
    private final ArrayList<CommandRecord> commandRecords;
    private final User owner;

    public PrimaryPack(User owner) {
        commandRecords = new ArrayList<>();
        this.owner = owner;
    }

    public PrimaryPack(ArrayList<CommandRecord> commandRecords, User owner) {
        this.commandRecords = commandRecords;
        this.owner = owner;
    }

    public void addCommand(CommandRecord commandRecord) {
        commandRecords.add(commandRecord);
    }

    public void clear() {
        commandRecords.clear();
    }

    public Integer size() {
        return commandRecords.size();
    }

    public ArrayList<CommandRecord> getCommandRecords() {
        return commandRecords;
    }

    public User getOwner() {
        return owner;
    }
}
