package interfaces;

/**
 * The Observer part of the Observer pattern.
 * All classes implementing this interface MUST have this method.
 * 
 * This Observer is taken from the MVCClockExample 
 *  given in the example under lectures Week 6: Design Patterns
 */
public interface Observer {
	
	/**
	 * Tell Observer to update itself
	 */
	public void update();
}