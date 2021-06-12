package ru.itmo.p3114.s312198.transmission;

import ru.itmo.p3114.s312198.exceptions.TransmissionException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CSChannel implements AutoCloseable {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public CSChannel(Socket socket) throws TransmissionException {
        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ioException) {
            throw new TransmissionException(ioException.getMessage());
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void writeObject(Object object) throws TransmissionException {
        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException ioException) {
            throw new TransmissionException("Unable to write: " + ioException.getMessage());
        }
    }

    public Object readObject() throws TransmissionException {
        try {
            return inputStream.readObject();
        } catch (IOException ioException) {
            throw new TransmissionException("Unable to read: " + ioException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            throw new TransmissionException("The data packet has been damaged");
        }
    }

    @Override
    public void close() throws TransmissionException {
        try {
            socket.shutdownInput();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException ioException) {
            throw new TransmissionException(ioException.getMessage());
        }
    }
}
