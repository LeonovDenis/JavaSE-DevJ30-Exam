package org.dictionary.networking;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import org.dictionary.networking.messaging.MessagingChanel;

public interface Connection extends Closeable {
    interface StataChangeListener {
        default void onConnectionCloused(Connection connection){}
    }
    
    interface MessageListener {
        default void onMessage(Connection source, Object message){}
    }
    
    void addStateChangeListener (StataChangeListener listener);
    
    void addMessageListener (MessageListener listener);
    
    void send (Object message) throws IOException;
    
    static Connection open(SocketAddress address) throws IOException{
        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(address);
            ConnectionImpl connection = new ConnectionImpl(MessagingChanel.open(socket));
            connection.start();
            return connection;
        } catch (IOException e) {
            if (socket != null){
                socket.close();
            }
            throw e;
        }
    }
    
    static Connection open(InetAddress address, int port) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(address, port);
        return open(socketAddress);
    }
        
}
