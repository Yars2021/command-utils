package ru.itmo.p3114.s312198.commands;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;

public class HistoryRecord {
    private final AbstractCommand command;
    private final CommandResult commandResult;

    public HistoryRecord(AbstractCommand command, CommandResult commandResult) {
        this.command = command;
        this.commandResult = commandResult;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public CommandResult getCommandResult() {
        return commandResult;
    }

    public String getLine() {
        return command.getCommandName() + " [" + commandResult.getStatus() + "]";
    }
}
