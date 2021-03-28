package interfaces;

/**
 * The interface for the observer of the observer pattern - all classes that
 * implement this will need to implement the method.
 * 
 * @author Rose Ulldemolins
 * 
 */

public interface Observer {
    // Telling the Observer to update
    public void update();
}