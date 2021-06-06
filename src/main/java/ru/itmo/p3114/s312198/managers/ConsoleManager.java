package ru.itmo.p3114.s312198.managers;

import ru.itmo.p3114.s312198.commands.CommandHashMap;
import ru.itmo.p3114.s312198.commands.CommandRecord;
import ru.itmo.p3114.s312198.commands.HistoryRecord;
import ru.itmo.p3114.s312198.commands.actions.complex.AbstractComplexCommand;
import ru.itmo.p3114.s312198.commands.actions.simple.Exit;
import ru.itmo.p3114.s312198.commands.actions.simple.Help;
import ru.itmo.p3114.s312198.commands.actions.simple.History;
import ru.itmo.p3114.s312198.commands.actions.simple.Message;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.commands.types.CommandTypes;
import ru.itmo.p3114.s312198.exceptions.InitializationException;
import ru.itmo.p3114.s312198.exceptions.InputInterruptedException;
import ru.itmo.p3114.s312198.exceptions.InvalidCommandException;
import ru.itmo.p3114.s312198.exceptions.NoSuchCommandException;
import ru.itmo.p3114.s312198.io.ConsoleReader;
import ru.itmo.p3114.s312198.parsers.CommandParser;
import ru.itmo.p3114.s312198.parsers.RequestParser;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.IOException;

public class ConsoleManager {
    private final SynchronizedCollectionManager synchronizedCollectionManager = new SynchronizedCollectionManager();
    private final CommandExecutor commandExecutor = new CommandExecutor(synchronizedCollectionManager);
    private final HistoryManager historyManager = new HistoryManager(5);
    private final CommandHashMap validCommands;
    private ConsoleReader consoleReader;
    private volatile Boolean running;
    private volatile Boolean waiting;

    public ConsoleManager(CommandHashMap validCommands) {
        this.validCommands = validCommands;
    }

    public void autoInitialize() {
        consoleReader = new ConsoleReader();
        commandExecutor.initialize(consoleReader);
        synchronizedCollectionManager.autoInitialize();
        running = Boolean.TRUE;
        waiting = Boolean.FALSE;
    }

    public void initialize(ConsoleReader consoleReader) {
        autoInitialize();
        commandExecutor.initialize(consoleReader);
    }

    public void shutdown() {
        running = Boolean.FALSE;
    }

    public void pause() {
        waiting = Boolean.TRUE;
    }

    public void resume() {
        waiting = Boolean.FALSE;
    }

    public States getState() {
        if (running) {
            if (waiting) {
                return States.WAITING;
            } else {
                return States.RUNNING;
            }
        } else {
            return States.STOPPED;
        }
    }

    //todo Change actorName to an actual actor (instance of User)
    public void runBy(String actorName) throws InitializationException {
        if (consoleReader == null || validCommands == null) {
            throw new InitializationException("Console manager was not initialized");
        } else {
            CommandParser commandParser = new CommandParser();
            RequestParser requestParser = new RequestParser();
            System.out.println("Collection manager program");
            System.out.println("Use \"help\" or \"?\" to see the command reference");
            while (running) {
                System.out.print("> ");
                try {
                    String userInput = consoleReader.flexibleConsoleReadLine();
                    String commandName = commandParser.parseCommandName(userInput, validCommands);
                    String argumentLine = commandParser.getArgumentLine(userInput, commandName);
                    CommandRecord commandRecord = commandParser.createCommandRecord(commandName, argumentLine, validCommands);
                    commandRecord.getCommand().setOwner(actorName);
                    commandRecord.getCommand().setTarget(synchronizedCollectionManager.getCollection());
                    if (commandRecord.getCommandType() == CommandTypes.COMPLEX_COMMAND) {
                        try {
                            if (((AbstractComplexCommand) commandRecord.getCommand()).canExecute()) {
                                StudyGroup complexArgument = requestParser.requestStudyGroup(consoleReader);
                                ((AbstractComplexCommand) commandRecord.getCommand()).setComplexArgument(complexArgument);
                            } else {
                                Message message = new Message();
                                message.setArgument("Element does not exist or you don't have enough access rights");
                                commandRecord = new CommandRecord(message, CommandTypes.SIMPLE_COMMAND);
                            }
                        } catch (InputInterruptedException inputInterruptedException) {
                            commandRecord = new CommandRecord(new Exit(), CommandTypes.SIMPLE_COMMAND);
                        }
                    }
                    if ("help".equals((commandRecord.getCommand().getCommandName()))) {
                        ((Help) commandRecord.getCommand()).setCommandHashMap(validCommands);
                    }
                    if ("history".equals(commandRecord.getCommand().getCommandName())) {
                        ((History) commandRecord.getCommand()).setHistoryManager(historyManager);
                    }
                    if ("exit".equals(commandRecord.getCommand().getCommandName())) {
                        shutdown();
                    }
                    CommandResult commandResult = commandExecutor.executeCommandRecord(commandRecord);
                    historyManager.push(new HistoryRecord(commandRecord.getCommand(), commandResult));
                    for (String line : commandResult.getOutput()) {
                        System.out.println(line);
                    }
                } catch (NoSuchCommandException | InvalidCommandException exception) {
                    System.out.println(exception.getMessage());
                } catch (IOException ioException) {
                    System.out.println("Unexpected IOException occurred");
                }
            }
            System.out.println("Shutting down the console manager");
        }
    }
}
