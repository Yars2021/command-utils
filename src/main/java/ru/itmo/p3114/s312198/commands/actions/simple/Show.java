package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Show extends AbstractCommand implements CollectionInteracting {
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
