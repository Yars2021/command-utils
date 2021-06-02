package ru.itmo.p3114.s312198.commands.raw;

import ru.itmo.p3114.s312198.commands.types.CommandTypes;

public class RawCommandBuilder {
    private final RawCommand rawCommand = new RawCommand();

    public RawCommandBuilder addCommand(String command) {
        rawCommand.setCommand(command);
        return this;
    }

    public RawCommandBuilder addCommandType(CommandTypes commandType) {
        rawCommand.setCommandType(commandType);
        return this;
    }

    public RawCommandBuilder addArgumentLine(String argumentLine) {
        rawCommand.setArgumentLine(argumentLine);
        return this;
    }

    public RawCommand toRawCommand() {
        return rawCommand;
    }
}
