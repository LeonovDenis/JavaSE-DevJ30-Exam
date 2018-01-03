package org.dictionary.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.dictionary.networking.messaging.MessagingChanel;
import org.dictionary.threading.Asynchronous;

public abstract class Server implements Asynchronous {
    private final ServerSocket socket;

    public Server(int port) throws IOException{
        this.socket = new ServerSocket(port);
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    protected abstract void onConnectionOpen(Connection connection);
    
    private void handleIncomingConnection(Socket socket) throws IOException {
        MessagingChanel chanel = MessagingChanel.open(socket);
        ConnectionImpl connection = new ConnectionImpl(chanel);
        connection.start();
        onConnectionOpen(connection);
    }
    
    @Override
    public void run() throws Exception {
        while (!socket.isClosed()) {
            Socket client = socket.accept();
            handleIncomingConnection(client);
        }
    }

    @Override
    public void onHandleException(Exception e) {
        //Asynchronous.super.onHandleException(e); //To change body of generated methods, choose Tools | Templates.
        if (!(e instanceof SocketException)) {
            e.printStackTrace(System.err);
        }
    }
}
