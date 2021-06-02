package ru.itmo.p3114.s312198.commands.types;

public enum ComplexCommands {
    ADD("add"),
    UPDATE("update"),
    ADD_IF_MAX("add_if_max"),
    REMOVE_GREATER("remove_greater");

    private final String command;

    ComplexCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
