package ru.itmo.p3114.s312198.io;

import ru.itmo.p3114.s312198.commands.actions.simple.AbstractCommand;
import ru.itmo.p3114.s312198.exceptions.InvalidFileException;

import java.io.*;
import java.util.ArrayList;

public class ScriptFileReader implements AutoCloseable {
    private final BufferedReader bufferedReader;

    public ScriptFileReader(String path) throws InvalidFileException {
        if (path == null) {
            throw new InvalidFileException("Invalid path");
        } else {
            File file = new File(path);
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException fileNotFoundException) {
                if (file.isDirectory()) {
                    throw new InvalidFileException(path + " is a directory");
                } else {
                    throw new InvalidFileException(path + " does not exist");
                }
            }
        }
    }

    public ArrayList<AbstractCommand> readCommands() {
        //todo
        return null;
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
