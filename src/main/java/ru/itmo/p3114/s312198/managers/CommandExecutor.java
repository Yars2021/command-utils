package ru.itmo.p3114.s312198.managers;

import ru.itmo.p3114.s312198.commands.CommandRecord;
import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.exceptions.InvalidCommandException;
import ru.itmo.p3114.s312198.io.ConsoleReader;

public class CommandExecutor {
    private final SynchronizedCollectionManager synchronizedCollectionManager;

    public CommandExecutor(SynchronizedCollectionManager synchronizedCollectionManager) {
        this.synchronizedCollectionManager = synchronizedCollectionManager;
    }

    public CommandResult executeCommand(AbstractCommand command) throws InvalidCommandException {
        if (command == null) {
            throw new InvalidCommandException("No command found");
        } else {
            if (!(command instanceof CollectionInteracting)) {
                return command.execute();
            } else {
                throw new InvalidCommandException("This command interacts with the collection");
            }
        }
    }

    public CommandResult executeCommandRecord(CommandRecord commandRecord) throws InvalidCommandException {
        AbstractCommand command = commandRecord.getCommand();
        if (command instanceof CollectionInteracting) {
            return synchronizedCollectionManager.applyToCollection(command);
        } else {
            return executeCommand(command);
        }
    }
}
