package ru.itmo.p3114.s312198.commands.actions.complex;

import ru.itmo.p3114.s312198.commands.actions.simple.AbstractCommand;
import ru.itmo.p3114.s312198.structures.StudyGroup;

abstract public class AbstractComplexCommand extends AbstractCommand {
    protected StudyGroup complexArgument;

    public AbstractComplexCommand(String command, String inputPattern, String documentation) {
        super(command, inputPattern, documentation);
    }

    public void setComplexArgument(StudyGroup complexArgument) {
        this.complexArgument = complexArgument;
    }

    public StudyGroup getComplexArgument() {
        return complexArgument;
    }
}
