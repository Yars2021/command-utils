package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

import java.util.ArrayList;

public class Clear extends AbstractCommand implements CollectionInteracting, DatabaseInteracting {
    public Clear() {
        super("clear", "", "Deletes accessible elements");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("Collection not found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (owner == null) {
                output.add("No owner found, unable to remove anything");
                return new CommandResult(Status.FAILED, output);
            } else {
                Integer lastSize = target.size();
                target.removeIf(studyGroup -> studyGroup != null && owner.equals(studyGroup.getOwner()));
                output.add("Successfully removed " + (lastSize - target.size()) + " elements");
                return new CommandResult(Status.OK, output);
            }
        }
    }
}
