package ru.itmo.p3114.s312198.io;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.CommandRecord;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.actions.complex.AbstractComplexCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.Message;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;
import ru.itmo.p3114.s312198.exceptions.InvalidFileException;
import ru.itmo.p3114.s312198.exceptions.InvalidFileFormatException;
import ru.itmo.p3114.s312198.exceptions.InvalidInputException;
import ru.itmo.p3114.s312198.exceptions.NoSuchCommandException;
import ru.itmo.p3114.s312198.parsers.CommandParser;
import ru.itmo.p3114.s312198.parsers.ComplexArgumentParser;
import ru.itmo.p3114.s312198.parsers.pairs.StudyGroupPair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScriptFileReader implements AutoCloseable {
    private final BufferedReader bufferedReader;
    private final String path;

    public ScriptFileReader(String path) throws InvalidFileException {
        if (path == null) {
            throw new InvalidFileException("Invalid path");
        } else {
            File file = new File(path);
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                this.path = path;
            } catch (FileNotFoundException fileNotFoundException) {
                if (file.isDirectory()) {
                    throw new InvalidFileException(path + " is a directory");
                } else {
                    throw new InvalidFileException(path + " does not exist");
                }
            }
        }
    }

    public ArrayList<AbstractCommand> readCommands(CommandHashMap validCommands) throws IOException, InvalidFileException {
        if (path == null) {
            throw new InvalidFileException("Invalid path, unable to read");
        } else {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8));
            ArrayList<AbstractCommand> commands = new ArrayList<>();
            CommandParser commandParser = new CommandParser();
            for (Integer current = 0; current < lines.size(); current++) {
                if (lines.get(current) != null) {
                    try {
                        String commandName = commandParser.parseCommandName(lines.get(current), validCommands);
                        String argumentLine = commandParser.getArgumentLine(lines.get(current), commandName);
                        CommandRecord commandRecord = commandParser.createCommandRecord(commandName, argumentLine, validCommands);
                        if (commandRecord.getCommandType() == CommandTypes.COMPLEX_COMMAND) {
                            try {
                                AbstractComplexCommand complexCommand = (AbstractComplexCommand) commandRecord.getCommand();
                                StudyGroupPair studyGroupPair = new ComplexArgumentParser().parseLinesIntoStudyGroup(lines, current + 1);
                                complexCommand.setComplexArgument(studyGroupPair.getStudyGroup());
                                current += studyGroupPair.getSkipped();
                            } catch (InvalidFileFormatException | InvalidInputException exception) {
                                continue;
                            }
                        }
                        commands.add(commandRecord.getCommand());
                    } catch (NoSuchCommandException noSuchCommandException) {
                        Message message = new Message();
                        message.setArgument(noSuchCommandException.getMessage());
                        commands.add(message);
                    }
                }
            }
            return commands;
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException ioException) {
            throw new IOException("Unexpected IO exception on reader.close()");
        }
    }
}
