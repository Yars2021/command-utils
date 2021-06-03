package ru.itmo.p3114.s312198.io;

import ru.itmo.p3114.s312198.exceptions.InvalidFileException;
import ru.itmo.p3114.s312198.structures.StudyGroup;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class DataFileWriter implements AutoCloseable {
    private final BufferedWriter bufferedWriter;

    public DataFileWriter(String path) throws InvalidFileException {
        if (path == null) {
            throw new InvalidFileException("Invalid path");
        } else {
            File file = new File(path);
            try {
                if (!file.exists()) {
                    try {
                        if (!file.createNewFile()) {
                            throw new InvalidFileException("Unable to create file");
                        }
                    } catch (SecurityException securityException) {
                        throw new InvalidFileException("Not enough rights to create " + path);
                    }
                }
                bufferedWriter = new BufferedWriter(new FileWriter(file));
            } catch (IOException ioException) {
                if (file.isDirectory()) {
                    throw new InvalidFileException(path + " is a directory");
                } else {
                    throw new InvalidFileException("Not enough rights to access " + path);
                }
            }
        }
    }

    public void writeCollection(LinkedHashSet<StudyGroup> studyGroups) throws IOException {
        if (bufferedWriter != null) {
            for (StudyGroup studyGroup : studyGroups) {
                if (studyGroup != null) {
                    bufferedWriter.write(studyGroup.toCSV());
                }
            }
        } else {
            throw new IOException("Unable to write");
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException ioException) {
            throw new IOException("Unexpected IO exception on reader.close()");
        }
    }
}
