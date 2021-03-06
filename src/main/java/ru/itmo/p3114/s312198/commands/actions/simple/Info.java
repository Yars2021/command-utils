package ru.itmo.p3114.s312198.commands.actions.simple;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.results.Status;
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
            if (target.size() == 0) {
                output.add("Collection is empty");
            } else {
                output.add("Collection size: " + target.size());
                output.add("Collection (in CSV format):");
                output.add("");
                output.add("ID,CreatorID,Owner: Name,CoordinatesX,CoordinatesY,CreationDate,StudentsCount,ShouldBeExpelled," +
                        "TransferredStudents,FormOfEducation,AdminID,AdminName,AdminHeight,AdminHairColor,AdminNationality,AdminLocationX," +
                        "AdminLocationY,AdminLocationZ,AdminLocationName");
                output.addAll(target.stream().map(StudyGroup::toInfoLine).collect(Collectors.toCollection(ArrayList::new)));
            }
            return new CommandResult(Status.OK, output);
        }
    }

    @Override
    public AbstractCommand clone() {
        ArrayList<String> cloneArguments = new ArrayList<>(arguments);
        Info commandClone = new Info();
        commandClone.setArguments(cloneArguments);
        commandClone.setOwner(owner);
        commandClone.setTarget(target);
        return commandClone;
    }
}
