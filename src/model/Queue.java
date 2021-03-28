/**
 * The MVC clock example from the MVC lecture.
 * This class is the Model, which holds the program's state.
 */

package model;
//Imports for class
import interfaces.Observer;
import interfaces.Subject;
import views.Bill;

import java.util.*;
import java.util.LinkedList;
import java.util.List;


/**
*
* Class for storing bills within a queue system
*@author Andrew Dalley 
*/



public class Queue implements Subject {
	private final int MINUTES_IN_HR = 60;
	private int minutes; // the time, stored as minutes since midnight

	public LinkedList<Bill> QueueList = new LinkedList<Bill>();
	
	  /**
     * Adds new bills to the end of the queue list equivalent of a customer joining
     * the back of a queue
     *
     * @param bill
     */

    public synchronized void addQueueBill(Bill bill) {
        QueueList.addLast(bill);
        System.out.println("Queued bill " + bill.getCustomerID());
    }
	
    public synchronized void setBillProcessedState(int index, boolean state) {
        QueueList.get(index).setProcessedStatus(state);
    }

    public synchronized Bill getAvailableBill() {
        Bill bill = null;
        for (Bill b : QueueList) {
            if (b.getProcessedStatus() == false) {
                bill = b;
                break;
            }
        }
        return bill;
    }

    public int getQsize() {
        return QueueList.size();
    }

    /**
     * Removes the first bill in the queue equivalent of serving the first customer
     *
     */

    public void removeQueueBill() {
        QueueList.removeFirst();
    }

    /**
     * Finds the Index of a searched bill
     *
     * @param bill
     * @return int
     */

    public int getQueueIndex(Bill bill) {
        return QueueList.indexOf(bill);
    }

    /**
     * Returns all of the Bills in the queue
     *
     * @return List<Bill>
     */

    public List<Bill> getQueueList() {
        return QueueList;
    }

    /**
     * Retrieves the bill from the queue list located at an index position
     *
     * @param index
     * @return Bill
     */

    public Bill getQueueBill(int index) {
        return QueueList.get(index);
    }

    /**
     * removes at a bill from the queue list at an index position
     *
     * @param index
     */

    public void removeBillAtIndex(int index) {
        QueueList.remove(index);
    }

    
    public ArrayList<ArrayList<String> > getTable() {
    	
    	 int i = getQueueList().size();
    	  
         // an ArrayList of ArrayLists
         ArrayList<ArrayList<String> > table = new ArrayList<ArrayList<String> >(i);
   
         // Create n lists one by one and append to the 
         // master list (ArrayList of ArrayList)
         
         
         
         List<Bill> queueList = getQueueList();
         
         
         for (Bill bill : queueList) {
        	 
        	 ArrayList<String> row = new ArrayList<String>();
        	 row.add(String.valueOf(bill.getCustomerID()));
        	 row.add(String.valueOf(bill.getOrderList().size()));
        	 row.add(String.valueOf(bill.getDiscountedPrice()));
        	 table.add(row);
         }
                                
         return table;
   
       
    }
    
    
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	
	// initially, set time to midnight
	//public Queue() {
	//	minutes = 0;
	//}

	// VARIOUS GET METHODS
	// returns hours component of the time
	public int getWholeHours() {
		return minutes / MINUTES_IN_HR;
	}

	// returns minutes component of the time
	public int getRemMins() {
		return minutes % MINUTES_IN_HR;
	}

	// returns value of time period in minutes
	public int getTimeInMins() {
		return minutes;
	}

	// creates a phrase 'x hours and y mins'
	public String getTimePhrase() {
		return this.getWholeHours() + " hours and  " + this.getRemMins() + " minutes";
	}

	// Stringifies the current time
	public String getTime12() {
		String time = "";
		String dayPart = "am";
		int wholeHours = this.getWholeHours();
		int minsLeft = this.getRemMins();
		if (wholeHours >= 12)
			dayPart = "pm";
		if (wholeHours > 12)
			wholeHours = wholeHours - 12;
		if (wholeHours < 9)
			time += "0";
		time += wholeHours + ":";
		if (minsLeft < 9)
			time += "0";
		time += minsLeft;
		return time + dayPart;
	}

	// Stringifies the current time, in 24 hour format
	public String getTime24() {
		String time = "";
		int wholeHours = this.getWholeHours();
		int minsLeft = this.getRemMins();
		if (wholeHours < 9)
			time += "0";
		time += wholeHours + ":";
		if (minsLeft < 9)
			time += "0";
		time += minsLeft;
		return time;
	}

	// adds amount in minutes to period of time
	public void addMinutes(int m) {
		minutes = minutes + m;
		notifyObservers();
	}

	// initialises time from hours and minutes
	public void setTime24(int hour, int min) {
		minutes = hour * 60 + min;
		notifyObservers();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	// Following the observer patter - Queue must be able to register, remove and
    // notify observers

    /**
     * List to hold any observers
     */
    private List<Observer> registeredObservers = new LinkedList<Observer>();

    /**
     * Register an observer with this subject
     */
    public void registerObserver(Observer observer) {
        registeredObservers.add(observer);
    }

    /**
     * De-register an observer with this subject
     */
    public void removeObserver(Observer observer) {
        registeredObservers.remove(observer);
    }
   
    /**
     * Inform all registered observers that there's been an update
     */
    public void notifyObservers() {
        for (Observer observer : registeredObservers)
            observer.update();
    }
    
	
	
}
