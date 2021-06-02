package ru.itmo.p3114.s312198.parsers;

import ru.itmo.p3114.s312198.commands.raw.RawCommand;
import ru.itmo.p3114.s312198.commands.raw.RawCommandBuilder;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;
import ru.itmo.p3114.s312198.commands.types.Commands;
import ru.itmo.p3114.s312198.commands.types.ComplexCommands;
import ru.itmo.p3114.s312198.commands.types.SimpleCommands;

import java.util.Locale;

public class CommandParser {
    public RawCommand parseUserInput(String input) {
        if (input == null) {
            return new RawCommandBuilder()
                    .addCommand(Commands.EXIT.getCommand())
                    .addCommandType(CommandTypes.SIMPLE_COMMAND)
                    .addArgumentLine("")
                    .toRawCommand();
        } else {
            if (input.trim().split("\\s").length == 0) {
                return new RawCommandBuilder()
                        .addCommand(Commands.NOP.getCommand())
                        .addCommandType(CommandTypes.SIMPLE_COMMAND)
                        .addArgumentLine("")
                        .toRawCommand();
            } else {
                Commands validCommand;
                SimpleCommands simpleCommand;
                ComplexCommands complexCommand;
                String argumentLine;
                try {
                    validCommand = Commands.valueOf(input.trim().split("\\s")[0].toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException illegalArgumentException) {
                    return new RawCommandBuilder()
                            .addCommand(Commands.NOP.getCommand())
                            .addCommandType(CommandTypes.SIMPLE_COMMAND)
                            .addArgumentLine("")
                            .toRawCommand();
                }
                try {
                    simpleCommand = SimpleCommands.valueOf(validCommand.getCommand().toUpperCase(Locale.ROOT));
                    try {
                        argumentLine = input.trim().substring(simpleCommand.getCommand().length());
                    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                        argumentLine = "";
                    }
                    return new RawCommandBuilder()
                            .addCommand(simpleCommand.getCommand())
                            .addCommandType(CommandTypes.SIMPLE_COMMAND)
                            .addArgumentLine(argumentLine)
                            .toRawCommand();
                } catch (IllegalArgumentException illegalArgumentException) {
                    try {
                        complexCommand = ComplexCommands.valueOf(validCommand.getCommand().toUpperCase(Locale.ROOT));
                        try {
                            argumentLine = input.trim().substring(complexCommand.getCommand().length());
                        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            argumentLine = "";
                        }
                        return new RawCommandBuilder()
                                .addCommand(complexCommand.getCommand())
                                .addCommandType(CommandTypes.COMPLEX_COMMAND)
                                .addArgumentLine(argumentLine)
                                .toRawCommand();
                    } catch (IllegalArgumentException illegalArgumentException1) {
                        return new RawCommandBuilder()
                                .addCommand(Commands.NOP.getCommand())
                                .addCommandType(CommandTypes.SIMPLE_COMMAND)
                                .addArgumentLine("")
                                .toRawCommand();
                    }
                }
            }
        }
    }
}
