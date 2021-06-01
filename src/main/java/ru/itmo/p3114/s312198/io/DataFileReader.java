package ru.itmo.p3114.s312198.io;

import ru.itmo.p3114.s312198.exceptions.InvalidFileException;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.*;
import java.util.LinkedHashSet;

public class DataFileReader implements AutoCloseable {
    private final BufferedReader bufferedReader;

    public DataFileReader(String path) throws InvalidFileException {
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

    public LinkedHashSet<StudyGroup> readCollection() {
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
