package ru.itmo.p3114.s312198.parsers;

import ru.itmo.p3114.s312198.commands.Commands;

public class CommandParser {
    private Boolean running = Boolean.TRUE;

    public Boolean isRunning() {
        return running;
    }

    public String parseCommandName(String input) {
        if (input == null) {
            running = Boolean.FALSE;
            return "exit";
        } else {
            if (input.trim().split("\\s").length == 0) {
                return "nop";
            } else {
                Commands commands;
                try {
                    commands = Commands.valueOf(input.trim().split("\\s")[0]);
                } catch (IllegalArgumentException illegalArgumentException) {
                    return "nop";
                }
                return commands.getCommand();
            }
        }
    }
}
