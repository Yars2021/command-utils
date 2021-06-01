package ru.itmo.p3114.s312198.commands.actions;

import ru.itmo.p3114.s312198.commands.CommandResult;
import ru.itmo.p3114.s312198.commands.Status;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Show extends AbstractCommand {
    public Show() {
        super("show", "", "shows collection elements");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("No collection found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (target.size() == 0) {
                output.add("Collection is empty");
            } else {
                output.add("Collection: ");
                output.addAll(target.stream().map(StudyGroup::toReadableString).collect(Collectors.toCollection(ArrayList::new)));
            }
            return new CommandResult(Status.OK, output);
        }
    }
}