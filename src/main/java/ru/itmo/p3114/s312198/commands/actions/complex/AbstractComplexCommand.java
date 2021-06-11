package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.structures.StudyGroup;

abstract public class AbstractComplexCommand extends AbstractCommand {
    protected StudyGroup complexArgument;

    public AbstractComplexCommand(String command, String inputPattern, String description) {
        super(command, inputPattern, description);
    }

    public void setComplexArgument(StudyGroup complexArgument) {
        this.complexArgument = complexArgument;
    }

    public StudyGroup getComplexArgument() {
        return complexArgument;
    }

    abstract public Boolean canExecute();
}
