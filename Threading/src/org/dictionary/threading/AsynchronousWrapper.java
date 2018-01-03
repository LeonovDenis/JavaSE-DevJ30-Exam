package org.dictionary.threading;

class AsynchronousWrapper implements Runnable {

    private final Asynchronous target;

    public AsynchronousWrapper(Asynchronous target) {
        this.target = target;
    }
      
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            target.run();
        } catch (Exception e) {
            target.onHandleException(e);
        }
    }
    
    
    
}
