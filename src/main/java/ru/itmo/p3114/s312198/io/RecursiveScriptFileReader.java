package ru.itmo.p3114.s312198.io;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.Message;
import ru.itmo.p3114.s312198.exceptions.InvalidFileException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class RecursiveScriptFileReader implements AutoCloseable {
    private ScriptFileReader scriptFileReader;
    private final HashSet<String> filesInUse = new HashSet<>();

    public ArrayList<AbstractCommand> readCommands(String path, CommandHashMap validCommands) throws IOException, InvalidFileException {
        ArrayList<AbstractCommand> commands = new ArrayList<>();
        try {
            scriptFileReader = new ScriptFileReader(path);
        } catch (InvalidFileException invalidFileException) {
            Message message = new Message();
            message.setArgument(invalidFileException.getMessage());
            commands.add(message);
            return commands;
        }
        if (path == null) {
            Message message = new Message();
            message.setArgument("Unable to read a file, invalid path");
            commands.add(message);
            return commands;
        } else {
            String canonicalPath = new File(path).getCanonicalPath();
            if (filesInUse.contains(canonicalPath)) {
                Message message = new Message();
                message.setArgument("\"" + canonicalPath + "\" is already in use, opening it will result in stack overflow");
                commands.add(message);
                return commands;
            } else {
                ArrayList<AbstractCommand> recursiveCommands = new ArrayList<>();
                filesInUse.add(canonicalPath);
                commands = scriptFileReader.readCommands(validCommands);
                for (AbstractCommand command : commands) {
                    if ("execute_script".equals(command.getCommandName())) {
                        recursiveCommands.addAll(readCommands(command.getArguments().get(0), validCommands));
                    } else {
                        recursiveCommands.add(command);
                    }
                }
                filesInUse.remove(canonicalPath);
                return recursiveCommands;
            }
        }
    }

    @Override
    public void close() throws IOException {
        if (scriptFileReader != null) {
            scriptFileReader.close();
        }
        filesInUse.clear();
    }
}
