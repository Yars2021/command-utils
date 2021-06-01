package ru.itmo.p3114.s312198.io;

import ru.itmo.p3114.s312198.exceptions.ConsoleException;

import java.io.*;

public class ConsoleReader implements AutoCloseable {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private final Console console = System.console();

    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    public String readConsoleLine() throws ConsoleException {
        if (console == null) {
            throw new ConsoleException("No console found");
        }
        try {
            return console.readLine();
        } catch (IOError ioError) {
            throw new ConsoleException("Unexpected IO error");
        }
    }

    public String readConsolePassword() throws ConsoleException {
        if (console == null) {
            throw new ConsoleException("No console found");
        }
        try {
            return new String(console.readPassword());
        } catch (IOError ioError) {
            throw new ConsoleException("Unexpected IO error");
        }
    }

    @Override
    public void close() throws IOException {
        try {
            bufferedReader.close();
        } catch (IOException ioException) {
            throw new IOException("Unexpected IO exception on reader.close()");
        }
    }
}
