package org.dictionary.ui;

import javax.swing.JFrame;

public abstract class AbstractFrame extends JFrame 
                                    implements Window {

    public AbstractFrame() {
        EventHandler.handle(this);
    }
    
}
