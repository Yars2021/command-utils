package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.Permission;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.markers.DatabaseInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.ArrayList;

public class Update extends AbstractComplexCommand implements CollectionInteracting, DatabaseInteracting {
    public Update() {
        super("update", "<id> {studyGroup}", "Updates a record by it's id");
    }

    @Override
    public CommandResult execute() {
        ArrayList<String> output = new ArrayList<>();
        if (target == null) {
            output.add("No collection found");
            return new CommandResult(Status.FAILED, output);
        } else {
            if (complexArgument == null) {
                output.add("Invalid complex argument");
                return new CommandResult(Status.FAILED, output);
            } else {
                if (owner == null) {
                    output.add("No owner found");
                    return new CommandResult(Status.FAILED, output);
                } else {
                    if (arguments == null) {
                        output.add("No arguments found");
                        return new CommandResult(Status.INCORRECT_ARGUMENTS, output);
                    } else {
                        for (StudyGroup studyGroup : target) {
                            if (studyGroup.getId().toString().equals(arguments.get(0))) {
                                studyGroup.setAllAccessibleFields(complexArgument);
                                output.add("Element updated successfully");
                                return new CommandResult(Status.OK, output);
                            }
                        }
                        output.add("No element with this id found");
                        return new CommandResult(Status.FAILED, output);
                    }
                }
            }
        }
    }

    @Override
    public Boolean canExecute() {
        if (owner == null || target == null || arguments == null || arguments.size() < 1) {
            return Boolean.FALSE;
        } else {
            Boolean exists = Boolean.FALSE;
            Long id;
            try {
                id = Long.parseLong(arguments.get(0));
            } catch (NumberFormatException numberFormatException) {
                return Boolean.FALSE;
            }
            for (StudyGroup studyGroup : target) {
                if (studyGroup.getId().equals(id)) {
                    exists = Boolean.TRUE;
                    break;
                }
            }
            if (exists) {
                ArrayList<String> permissionArgs = new ArrayList<>();
                permissionArgs.add(arguments.get(0));
                Permission permission = new Permission();
                permission.setOwner(owner);
                permission.setTarget(target);
                permission.setArguments(permissionArgs);
                return "Allowed".equals(permission.execute().getOutput().get(0));
            } else {
                return Boolean.FALSE;
            }
        }
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        Update commandClone = new Update();
        commandClone.setComplexArgument(complexArgument);
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
