package org.dictionary.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import org.dictionary.model.Term;
import org.dictionary.model.messaging.Requests;
import org.dictionary.networking.Connection;
import org.dictionary.ui.AbstractFrame;

public class DictionaryClient extends AbstractFrame 
                                implements Connection.MessageListener{

    public static void main(String[] args) throws IOException {
        DictionaryClient window = new DictionaryClient();
        window.setVisible(true);
        
    }
    
    private final Connection connection;
    private final DefaultListModel<Term> terms;
    private final JList<Term> list;
    
    
    private DictionaryClient() throws IOException{
        terms = new DefaultListModel<>();
        list = new JList<>(terms);
        connection = Connection.open(InetAddress.getLoopbackAddress(), 1001);
        connection.addMessageListener(this);
        connection.send(Requests.ListTerms);
    }

    @Override
    public void onInitComponents() {
        setTitle("Dictionary");
        setPreferredSize(300,400);
        add(list);
        pack();
    }

    @Override
    public void onMessage(Connection source, Object message) {
        if (message instanceof Collection) {
            Collection<Term> collection = (Collection<Term>) message;
            for (Term term : collection) {
                terms.addElement(term);
            }
                
        }
    }
    
    

    @Override
    public void dispose() {
        try {
            connection.close();
        } catch (IOException ignore) {
            
        } finally {
            super.dispose();
        }
    }
}
