package ru.itmo.p3114.s312198.commands;

import ru.itmo.p3114.s312198.commands.actions.complex.AbstractComplexCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.AbstractCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.Clear;
import ru.itmo.p3114.s312198.commands.actions.simple.ExecuteScript;
import ru.itmo.p3114.s312198.commands.actions.simple.Exit;
import ru.itmo.p3114.s312198.commands.actions.simple.Help;
import ru.itmo.p3114.s312198.commands.actions.simple.History;
import ru.itmo.p3114.s312198.commands.actions.simple.Info;
import ru.itmo.p3114.s312198.commands.actions.simple.Message;
import ru.itmo.p3114.s312198.commands.actions.simple.Nop;
import ru.itmo.p3114.s312198.commands.actions.simple.Permission;
import ru.itmo.p3114.s312198.commands.actions.simple.PrintFieldAscendingGroupAdmin;
import ru.itmo.p3114.s312198.commands.actions.simple.RemoveAllByShouldBeExpelled;
import ru.itmo.p3114.s312198.commands.actions.simple.RemoveAnyByTransferredStudents;
import ru.itmo.p3114.s312198.commands.actions.simple.RemoveById;
import ru.itmo.p3114.s312198.commands.raw.RawCommand;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;
import ru.itmo.p3114.s312198.commands.types.ComplexCommands;
import ru.itmo.p3114.s312198.commands.types.SimpleCommands;
import ru.itmo.p3114.s312198.exceptions.InvalidCommandTypeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CommandConverter {
    public AbstractCommand convertRawIntoSimple(RawCommand rawCommand) throws InvalidCommandTypeException {
        if (rawCommand.getCommandType() == CommandTypes.COMPLEX_COMMAND) {
            throw new InvalidCommandTypeException();
        } else {
            ArrayList<String> arguments = new ArrayList<>();
            AbstractCommand currentCommand;
            switch (SimpleCommands.valueOf(rawCommand.getCommand().toUpperCase(Locale.ROOT))) {
                case CLEAR:
                    currentCommand = new Clear();
                    break;
                case EXECUTE_SCRIPT:
                    currentCommand = new ExecuteScript();
                    break;
                case EXIT:
                    currentCommand = new Exit();
                    break;
                case HELP:
                    currentCommand = new Help();
                    break;
                case HISTORY:
                    currentCommand = new History();
                    break;
                case INFO:
                    currentCommand = new Info();
                    break;
                case MESSAGE:
                    currentCommand = new Message();
                    break;
                case PERMISSION:
                    currentCommand = new Permission();
                    break;
                case PRINT_FIELD_ASCENDING_GROUP_ADMIN:
                    currentCommand = new PrintFieldAscendingGroupAdmin();
                    break;
                case REMOVE_ALL_BY_SHOULD_BE_EXPELLED:
                    currentCommand = new RemoveAllByShouldBeExpelled();
                    break;
                case REMOVE_ANY_BY_TRANSFERRED_STUDENTS:
                    currentCommand = new RemoveAnyByTransferredStudents();
                    break;
                case REMOVE_BY_ID:
                    currentCommand = new RemoveById();
                    break;
                default:
                    currentCommand = new Nop();
                    break;
            }
            if (rawCommand.getArgumentLine().trim().length() > 0) {
                Collections.addAll(arguments, rawCommand.getArgumentLine().trim().split("\\s"));
            } else {
                arguments = null;
            }
            currentCommand.setArguments(arguments);
            return currentCommand;
        }
    }

    public AbstractComplexCommand convertRawIntoComplex(RawCommand rawCommand) throws InvalidCommandTypeException {
        if (rawCommand.getCommandType() == CommandTypes.SIMPLE_COMMAND) {
            throw new InvalidCommandTypeException();
        } else {
            switch (ComplexCommands.valueOf(rawCommand.getCommand().toUpperCase(Locale.ROOT))) {
                //todo
            }
            return null;
        }
    }
}
