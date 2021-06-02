package ru.itmo.p3114.s312198.commands;

import ru.itmo.p3114.s312198.commands.actions.simple.AbstractCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.Clear;
import ru.itmo.p3114.s312198.commands.actions.simple.ExecuteScript;
import ru.itmo.p3114.s312198.commands.actions.simple.Exit;
import ru.itmo.p3114.s312198.commands.actions.simple.Help;
import ru.itmo.p3114.s312198.commands.actions.simple.History;
import ru.itmo.p3114.s312198.commands.actions.simple.Info;
import ru.itmo.p3114.s312198.commands.actions.simple.Message;
import ru.itmo.p3114.s312198.commands.actions.simple.Nop;
import ru.itmo.p3114.s312198.commands.actions.simple.Permission;
import ru.itmo.p3114.s312198.commands.actions.simple.PrintFieldAscendingGroupAdmin;
import ru.itmo.p3114.s312198.commands.actions.simple.RemoveAllByShouldBeExpelled;
import ru.itmo.p3114.s312198.commands.actions.simple.RemoveAnyByTransferredStudents;
import ru.itmo.p3114.s312198.commands.actions.simple.RemoveById;
import ru.itmo.p3114.s312198.commands.actions.simple.Show;

import java.util.ArrayList;

public class DocumentationList {
    private final ArrayList<AbstractCommand> commands = new ArrayList<>();

    public DocumentationList() {
        commands.add(new Clear());
        commands.add(new ExecuteScript());
        commands.add(new Exit());
        commands.add(new Help());
        commands.add(new History());
        commands.add(new Info());
        commands.add(new Message());
        commands.add(new Nop());
        commands.add(new Permission());
        commands.add(new PrintFieldAscendingGroupAdmin());
        commands.add(new RemoveAllByShouldBeExpelled());
        commands.add(new RemoveAnyByTransferredStudents());
        commands.add(new RemoveById());
        commands.add(new Show());
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public ArrayList<String> getDocumentation() {
        ArrayList<String> documentation = new ArrayList<>();
        for (AbstractCommand command : commands) {
            documentation.add((command.getCommand() + " " + command.getInputPattern()).trim() +
                    ": " + command.getDocumentation());
        }
        return documentation;
    }
}
