package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;

public class Permission extends AbstractCommand implements CollectionInteracting, DatabaseInteracting {
    public Permission() {
        super("permission", "<id>", "Checks if a user has enough access rights to interact with an element by touching it");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("No collection found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (owner == null) {
                output.add("No owner found, unable to execute");
                return new CommandResult(Status.FAILED, output);
            } else {
                if (arguments == null || arguments.size() == 0) {
                    output.add("Invalid command arguments");
                    return new CommandResult(Status.INCORRECT_ARGUMENTS, output);
                } else {
                    String id = arguments.get(0);
                    for (StudyGroup studyGroup : target) {
                        if (id != null && id.equals(studyGroup.getId().toString())) {
                            if (owner.equals(studyGroup.getOwner())) {
                                output.add("Allowed");
                            } else {
                                output.add("Not allowed");
                            }
                            return new CommandResult(Status.OK, output);
                        }
                    }
                    output.add("No element with id \"" + id + "\" found");
                    return new CommandResult(Status.FAILED, output);
                }
            }
        }
    }
}
