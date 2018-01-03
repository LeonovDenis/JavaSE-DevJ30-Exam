package org.dictionary.ui;

import java.awt.Dimension;
import java.awt.event.WindowListener;

public interface Window {
    
    void addWindowListener (WindowListener listener);
    
    void setDefaultCloseOperation(int operation);
    
    void setPreferredSize(Dimension dimension);
    
    void setLocationByPlatform (boolean isSetByPlatform);
    
    default void onInitComponents(){
        
    }
    
    default void setPreferredSize(int widht, int height){
        setPreferredSize(new Dimension(widht, height));
    }
}
