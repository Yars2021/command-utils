package ru.itmo.p3114.s312198.commands.actions;

import ru.itmo.p3114.s312198.commands.CommandResult;
import ru.itmo.p3114.s312198.commands.Status;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Info extends AbstractCommand implements CollectionInteracting {
    public Info() {
        super("info", "", "information about the collection");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("No collection found");
            return new CommandResult(Status.FAILED, output);
        } else {
            output.add("Collection type: LinkedHashSet of StudyGroup");
            output.add("Elements in CSV format:");
            if (target.size() == 0) {
                output.add("Collection is empty");
            } else {
                output.addAll(target.stream().map(StudyGroup::toCSV).collect(Collectors.toCollection(ArrayList::new)));
            }
            return new CommandResult(Status.OK, output);
        }
    }
}
