package ru.itmo.p3114.s312198.commands.actions;

import ru.itmo.p3114.s312198.commands.CommandList;
import ru.itmo.p3114.s312198.commands.CommandResult;
import ru.itmo.p3114.s312198.commands.Status;

public class Help extends AbstractCommand {
    public Help() {
        super("help", "", "shows the command reference page");
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(Status.OK, new CommandList().getDocumentation());
    }
}
