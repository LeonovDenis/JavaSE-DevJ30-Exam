package org.dictionary.threading;

public interface Asynchronous extends AutoCloseable {
    
    void run() throws Exception;
    
    default void onHandleException(Exception e) {
        throw new RuntimeException(e);
    };
    
    default void start() {
        AsynchronousWrapper wrapper = new AsynchronousWrapper(this);
        Thread thread = new Thread(wrapper);
        thread.start();
        
    }
    
    
}
