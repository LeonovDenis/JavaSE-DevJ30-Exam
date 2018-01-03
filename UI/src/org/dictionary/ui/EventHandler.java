package org.dictionary.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;

final class EventHandler extends WindowAdapter {
    
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignore) {
            
        }
    }
    
    private final Window window;

    @Override
    public void windowOpened(WindowEvent we) {
        window.onInitComponents();
    }

    private EventHandler(Window window) {
        this.window = window;
    }
    
    static void handle(Window window) {
        EventHandler eventHandler = new EventHandler(window);
        window.addWindowListener(eventHandler);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationByPlatform(true);
    }

}
