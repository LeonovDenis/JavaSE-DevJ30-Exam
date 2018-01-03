package org.dictionary.networking;

import java.io.IOException;
import java.util.Collection;
import org.dictionary.networking.messaging.MessagingChanel;
import org.dictionary.threading.Asynchronous;
import static java.util.Collections.synchronizedSet;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

class ConnectionImpl implements Connection, Asynchronous {
    
    private final MessagingChanel chanel;
    private final Collection<StataChangeListener> stataChangeListeners;
    private final Collection<MessageListener> messageListeners;

    ConnectionImpl(MessagingChanel chanel) {
        this.chanel = chanel;
        stataChangeListeners = synchronizedSet(new HashSet<>());
        messageListeners = synchronizedSet(new HashSet<>());
    }

    @Override
    public void addStateChangeListener(StataChangeListener listener) {
        stataChangeListeners.add(listener);
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        messageListeners.add(listener);
    }

    @Override
    public void send(Object message) throws IOException {
        chanel.send(message);
    }

    @Override
    public void run() throws Exception {
        while (chanel.isOpend()) {
            Object message = chanel.reciive();
            for (MessageListener listener : messageListeners){
                listener.onMessage(this, message);
            }
        }
    }

    @Override
    public void onHandleException(Exception e) {
        try {
            close();
        } catch (IOException ignore) {
            Logger.getLogger(ConnectionImpl.class.getName()).log(Level.SEVERE, null, ignore);
        }
    }
    
    @Override
    public void close() throws IOException {
        if (!chanel.isOpend()){
            for (StataChangeListener listener : stataChangeListeners) {
                listener.onConnectionCloused(this);
            }
        }
        chanel.close();
    }
}
