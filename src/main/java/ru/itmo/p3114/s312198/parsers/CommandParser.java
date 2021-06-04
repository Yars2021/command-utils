package ru.itmo.p3114.s312198.parsers;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.CommandRecord;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.exceptions.NoSuchCommandException;

import java.util.Collections;

public class CommandParser {
    public String parseCommandName(String input, CommandHashMap validCommands) throws NoSuchCommandException {
        if (input == null) {
            return "exit";
        } else {
            if (input.trim().split("\\s+?").length == 0) {
                return "nop";
            } else {
                if ("?".equals(input.trim().split("\\s+?")[0])) {
                    return "help";
                } else {
                    if (validCommands.containsName(input.trim().split("\\s+?")[0])) {
                        return input.trim().split("\\s+?")[0];
                    } else {
                        throw new NoSuchCommandException("\"" + input.trim().split("\\s+?")[0] + "\" is not a valid command");
                    }
                }
            }
        }
    }

    public String getArgumentLine(String input, String commandName) {
        if (input.trim().length() < commandName.length()) {
            return "";
        } else {
            return input.trim().substring(commandName.length());
        }
    }

    public CommandRecord createCommandRecord(String commandName, String argumentLine, CommandHashMap validCommands) {
        AbstractCommand command = validCommands.getCommandRecord(commandName).getCommand().clone();
        Collections.addAll(command.getArguments(), argumentLine.trim().split("\\s+?"));
        return new CommandRecord(command, validCommands.getCommandRecord(commandName).getCommandType());
    }
}
