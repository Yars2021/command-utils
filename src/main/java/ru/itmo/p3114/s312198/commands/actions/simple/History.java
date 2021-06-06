package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.HistoryRecord;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
import ru.itmo.p3114.s312198.managers.HistoryManager;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class History extends AbstractCommand {
    private HistoryManager historyManager;

    public History() {
        super("history", "", "Shows 5 last executed commands");
    }

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (historyManager == null) {
            output.add("No history manager found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (historyManager.getHistory().isEmpty()) {
                output.add("History is empty");
            } else {
                output = historyManager.getHistory().stream().map(HistoryRecord::getLine).collect(Collectors.toCollection(ArrayList::new));
            }
            return new CommandResult(Status.OK, output);
        }
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        History commandClone = new History();
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
