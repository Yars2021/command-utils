package ru.itmo.p3114.s312198.commands;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;

public class CommandRecord {
    private final AbstractCommand command;
    private final CommandTypes commandType;

    public CommandRecord(AbstractCommand command, CommandTypes commandType) {
        this.command = command;
        this.commandType = commandType;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public CommandTypes getCommandType() {
        return commandType;
    }
}
