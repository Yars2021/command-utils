package ru.itmo.p3114.s312198.managers;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.CommandRecord;
import ru.itmo.p3114.s312198.commands.actions.simple.Help;
import ru.itmo.p3114.s312198.exceptions.InitializationException;
import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.exceptions.InvalidCommandException;
import ru.itmo.p3114.s312198.exceptions.InvalidFileException;
import ru.itmo.p3114.s312198.exceptions.NoSuchCommandException;
import ru.itmo.p3114.s312198.io.RecursiveScriptFileReader;
import ru.itmo.p3114.s312198.parsers.CommandParser;
import ru.itmo.p3114.s312198.parsers.RequestParser;
import ru.itmo.p3114.s312198.transmission.PrimaryPack;
import ru.itmo.p3114.s312198.transmission.ResponsePack;
import ru.itmo.p3114.s312198.transmission.SecondaryPack;
import ru.itmo.p3114.s312198.transmission.User;

import java.io.IOException;
import java.util.ArrayList;

public class ClientConsoleManager extends ConsoleManager {
    private final CommandParser commandParser = new CommandParser();
    private final RequestParser requestParser = new RequestParser();

    public ClientConsoleManager(CommandHashMap validCommands) {
        super(validCommands);
    }

    public PrimaryPack formPrimaryPack(User actor) throws InitializationException {
        if (consoleReader == null || validCommands == null) {
            throw new InitializationException("Console manager was not initialized");
        } else {
            ArrayList<CommandRecord> commandRecords = new ArrayList<>();
            try {
                String userInput = consoleReader.flexibleConsoleReadLine();
                String commandName = commandParser.parseCommandName(userInput, validCommands);
                String argumentLine = commandParser.getArgumentLine(userInput, commandName);
                CommandRecord commandRecord = commandParser.createCommandRecord(commandName, argumentLine, validCommands);
                commandRecord.getCommand().setOwner(actor.getUsername());
                commandRecords.add(commandRecord);
                if ("exit".equals(commandRecord.getCommand().getCommandName())) {
                    shutdown();
                }
                if ("execute_script".equals((commandRecord.getCommand().getCommandName()))) {
                    try (RecursiveScriptFileReader scriptFileReader = new RecursiveScriptFileReader()) {
                        commandRecords.addAll(commandParser.formCommandRecords(scriptFileReader
                                .readCommands(commandRecord.getCommand().getArguments().get(0), validCommands)));
                    } catch (InvalidFileException invalidFileException) {
                        System.out.println(invalidFileException.getMessage());
                    }
                }
            } catch (NoSuchCommandException | InvalidCommandException exception) {
                System.out.println(exception.getMessage());
            } catch (IOException ioException) {
                System.out.println("Unexpected IOException occurred");
            }
            return new PrimaryPack(commandRecords, actor);
        }
    }

    public SecondaryPack formSecondaryPack(User actor) throws InitializationException {
        if (consoleReader == null || validCommands == null) {
            throw new InitializationException("Console manager was not initialized");
        } else {
            try {
                return new SecondaryPack(requestParser.requestStudyGroup(consoleReader), actor);
            } catch (InputInterruptedException inputInterruptedException) {
                return null;
            }
        }
    }

    public void printResponsePack(ResponsePack responsePack) {
        for (String line : responsePack.getOutput()) {
            System.out.println(line);
        }
    }
}
