package ru.itmo.p3114.s312198.commands.types;

public enum SimpleCommands {
    HELP("help"),
    INFO("info"),
    SHOW("show"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    HISTORY("history"),
    REMOVE_ALL_BY_SHOULD_BE_EXPELLED("remove_all_by_should_be_expelled"),
    REMOVE_ANY_BY_TRANSFERRED_STUDENTS("remove_any_by_transferred_students"),
    PRINT_FIELD_ASCENDING_GROUP_ADMIN("print_field_ascending_group_admin"),
    NOP("nop"),
    MESSAGE("msg"),
    PERMISSION("permission");

    private final String command;

    SimpleCommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
