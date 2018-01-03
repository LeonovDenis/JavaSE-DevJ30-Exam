package org.dictionary.server;
import java.io.IOException;
import org.dictionary.model.messaging.Requests;
import org.dictionary.networking.*;
import org.dictionary.storage.Terms;

public class DictionaryServer extends Server  
                              implements Connection.MessageListener {


    public static void main(String[] args) throws Exception {
        try (DictionaryServer server = new DictionaryServer()) {
            server.start();
            System.out.println("Server started! Press ENTER to exit");
            System.in.read();
        }
    }

    private DictionaryServer() throws IOException {
        super(1001);
    }

    @Override
    public void onMessage(Connection source, Object message) {
        System.out.println(message);
        if (message instanceof Requests) {
            handleRequest(source, (Requests) message);
        }
    }
    
    private void handleRequest (Connection connection, Requests request){
        try {
            switch (request) {
                case ListTerms:
                    connection.send(Terms.all());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    @Override
    protected void onConnectionOpen(Connection connection) {
        connection.addMessageListener(this);
    }
}
