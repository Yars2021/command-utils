package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PrintFieldAscendingGroupAdmin extends AbstractCommand implements CollectionInteracting {
    public PrintFieldAscendingGroupAdmin() {
        super("print_filed_ascending_group_admin", "", "Prints all group admins");
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
                output = target.stream().filter(studyGroup -> studyGroup.getGroupAdmin() != null)
                        .map(studyGroup -> studyGroup.getGroupAdmin().toCSV()).collect(Collectors.toCollection(ArrayList::new));
            }
            return new CommandResult(Status.OK, output);
        }
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        PrintFieldAscendingGroupAdmin commandClone = new PrintFieldAscendingGroupAdmin();
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
