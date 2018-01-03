package org.dictionary.networking.messaging;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

public interface MessagingChanel extends Closeable {
    
    boolean isOpend();
    
    void send(Object message) throws IOException;
    
    Object reciive() throws IOException;
    
    static MessagingChanel open(Socket socket) throws IOException {
        return new MessagingChanelImpl(socket);
    }

}
