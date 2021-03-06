package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;

import java.util.ArrayList;

public class RemoveAllByShouldBeExpelled extends AbstractCommand implements CollectionInteracting, DatabaseInteracting {
    public RemoveAllByShouldBeExpelled() {
        super("remove_all_by_should_be_expelled", "<shouldBeExpelled>", "Removes all the structures with this shouldBeExpelled value");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("No collection found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (owner == null) {
                output.add("No owner found, unable to remove anything");
                return new CommandResult(Status.FAILED, output);
            } else {
                if (target.size() == 0) {
                    output.add("The collection is empty");
                    return new CommandResult(Status.OK, output);
                } else {
                    if (arguments == null || arguments.size() == 0) {
                        output.add("Invalid command arguments");
                        return new CommandResult(Status.INCORRECT_ARGUMENTS, output);
                    } else {
                        try {
                            Integer lastSize = target.size();
                            Integer shouldBeExpelled = Integer.parseInt(arguments.get(0));
                            target.removeIf(studyGroup -> studyGroup != null && owner.equals(studyGroup.getOwner()) &&
                                    shouldBeExpelled.equals(studyGroup.getShouldBeExpelled()));
                            output.add("Successfully removed " + (lastSize - target.size()) + " elements");
                            return new CommandResult(Status.OK, output);
                        } catch (NumberFormatException numberFormatException) {
                            output.add("Invalid argument format");
                            return new CommandResult(Status.INCORRECT_ARGUMENTS, output);
                        }
                    }
                }
            }
        }
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        RemoveAllByShouldBeExpelled commandClone = new RemoveAllByShouldBeExpelled();
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
