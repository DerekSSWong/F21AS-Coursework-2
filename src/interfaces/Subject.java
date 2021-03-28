package interfaces;

/**
 * This is the interface for the subject of the observer pattern, so all classes
 * implementing this will need these methods.
 * 
 * @author Rose Ulldemolins
 * 
 */
public interface Subject {
    // Register an observer with a subject
    public void registerObserver(Observer observer);

    // Remove an observer of a subject
    public void removeObserver(Observer observer);

    // Let all refistered observers know there's been an update
    public void notifyObservers();
}