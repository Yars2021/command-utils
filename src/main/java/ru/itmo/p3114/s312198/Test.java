package ru.itmo.p3114.s312198;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.actions.complex.Add;
import ru.itmo.p3114.s312198.commands.actions.complex.AddIfMax;
import ru.itmo.p3114.s312198.commands.actions.complex.RemoveGreater;
import ru.itmo.p3114.s312198.commands.actions.complex.Update;
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
import ru.itmo.p3114.s312198.commands.actions.simple.Show;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;
import ru.itmo.p3114.s312198.exceptions.InitializationException;
import ru.itmo.p3114.s312198.managers.ConsoleManager;

public class Test {
    public static void main(String[] args) {
        CommandHashMap commandHashMap = new CommandHashMap();
        commandHashMap.addCommandRecord("help", CommandTypes.SIMPLE_COMMAND, new Help());
        commandHashMap.addCommandRecord("info", CommandTypes.SIMPLE_COMMAND, new Info());
        commandHashMap.addCommandRecord("show", CommandTypes.SIMPLE_COMMAND, new Show());
        commandHashMap.addCommandRecord("remove_by_id", CommandTypes.SIMPLE_COMMAND, new RemoveById());
        commandHashMap.addCommandRecord("clear", CommandTypes.SIMPLE_COMMAND, new Clear());
        commandHashMap.addCommandRecord("execute_script", CommandTypes.SIMPLE_COMMAND, new ExecuteScript());
        commandHashMap.addCommandRecord("exit", CommandTypes.SIMPLE_COMMAND, new Exit());
        commandHashMap.addCommandRecord("history", CommandTypes.SIMPLE_COMMAND, new History());
        commandHashMap.addCommandRecord("remove_all_by_should_be_expelled", CommandTypes.SIMPLE_COMMAND, new RemoveAllByShouldBeExpelled());
        commandHashMap.addCommandRecord("remove_any_by_transferred_students", CommandTypes.SIMPLE_COMMAND, new RemoveAnyByTransferredStudents());
        commandHashMap.addCommandRecord("print_field_ascending_group_admin", CommandTypes.SIMPLE_COMMAND, new PrintFieldAscendingGroupAdmin());
        commandHashMap.addCommandRecord("nop", CommandTypes.SIMPLE_COMMAND, new Nop());
        commandHashMap.addCommandRecord("msg", CommandTypes.SIMPLE_COMMAND, new Message());
        commandHashMap.addCommandRecord("permission", CommandTypes.SIMPLE_COMMAND, new Permission());
        commandHashMap.addCommandRecord("add", CommandTypes.COMPLEX_COMMAND, new Add());
        commandHashMap.addCommandRecord("update", CommandTypes.COMPLEX_COMMAND, new Update());
        commandHashMap.addCommandRecord("add_if_max", CommandTypes.COMPLEX_COMMAND, new AddIfMax());
        commandHashMap.addCommandRecord("remove_greater", CommandTypes.COMPLEX_COMMAND, new RemoveGreater());

        ConsoleManager consoleManager = new ConsoleManager(commandHashMap);
        consoleManager.autoInitialize();
        try {
            consoleManager.runBy("Yars");
        } catch (InitializationException initializationException) {
            initializationException.printStackTrace();
        }
    }
}
