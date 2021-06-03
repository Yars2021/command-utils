package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.DocumentationList;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

public class Help extends AbstractCommand {
    public Help() {
        super("help", "", "shows the command reference page");
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(Status.OK, new DocumentationList().getDocumentation());
    }
}
