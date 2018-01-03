package org.dictionary.networking.messaging;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class MessagingChanelImpl implements MessagingChanel {
    
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    
    @Override
    public boolean isOpend() {
        return !socket.isClosed();
    }

    @Override
    public void send(Object message) throws IOException {
        outputStream.writeObject(message);
    }

    @Override
    public Object reciive() throws IOException {
        try {
            return inputStream.readObject();
        } catch (ClassNotFoundException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    MessagingChanelImpl(Socket socket) throws IOException {
        this.socket=socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
    }
}
