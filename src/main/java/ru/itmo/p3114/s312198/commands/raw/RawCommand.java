package ru.itmo.p3114.s312198.commands.raw;

import ru.itmo.p3114.s312198.commands.types.CommandTypes;

public class RawCommand {
    private String command;
    private CommandTypes commandType;
    private String argumentLine;

    public RawCommand() {
    }

    public RawCommand(String command, CommandTypes commandType, String argumentLine) {
        this.command = command;
        this.commandType = commandType;
        this.argumentLine = argumentLine;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setCommandType(CommandTypes commandType) {
        this.commandType = commandType;
    }

    public void setArgumentLine(String argumentLine) {
        this.argumentLine = argumentLine;
    }

    public String getCommand() {
        return command;
    }

    public CommandTypes getCommandType() {
        return commandType;
    }

    public String getArgumentLine() {
        return argumentLine;
    }
}
