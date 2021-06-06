package ru.itmo.p3114.s312198.managers;

import ru.itmo.p3114.s312198.commands.actions.AbstractCommand;
import ru.itmo.p3114.s312198.commands.markers.CollectionInteracting;
import ru.itmo.p3114.s312198.commands.results.CommandResult;
import ru.itmo.p3114.s312198.exceptions.InvalidCommandException;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.util.LinkedHashSet;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedCollectionManager {
    private LinkedHashSet<StudyGroup> collection;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public SynchronizedCollectionManager() {
        autoInitialize();
    }

    public void autoInitialize() {
        collection = new LinkedHashSet<>();
    }

    public void initialize(LinkedHashSet<StudyGroup> collection) {
        this.collection = collection;
    }

    public LinkedHashSet<StudyGroup> getCollection() {
        return collection;
    }

    public CommandResult applyToCollection(AbstractCommand command) throws InvalidCommandException {
        if (command == null) {
            throw new InvalidCommandException("No command found");
        } else {
            if (command instanceof CollectionInteracting) {
                reentrantLock.lock();
                command.setTarget(collection);
                CommandResult commandResult = command.execute();
                reentrantLock.unlock();
                return commandResult;
            } else {
                throw new InvalidCommandException("This command does not interact with the collection");
            }
        }
    }
}
