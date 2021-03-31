/**
 * A class for the Queue (which is a subject and model)
 */

package model;

// Component imports
import java.util.*;
import java.util.LinkedList;
import java.util.List;
// Interface imports
import interfaces.Observer;
import interfaces.Subject;

/**
 *
 * Class for storing bills within a queue system
 * 
 * @author Dan Ryan, Andrew Dalley, Derek Wong and Rose Ulldemolins
 */

public class Queue implements Subject {

    public LinkedList<Bill> QueueList = new LinkedList<Bill>();
    private int NumberofBills;
    private int billsRemoved = 0;

    /**
     * Adds new bills to the end of the queue list equivalent of a customer joining
     * the back of a queue
     *
     * @param bill
     */

    public synchronized void addQueueBill(Bill bill) {
        QueueList.addLast(bill);
        notifyObservers();
    }

    /**
     * Takes in an index (for the bill) and whether or not it's processed and set's
     * it
     * 
     * @param state
     * @param index
     */

    public synchronized void setBillProcessedState(int index, boolean state) {
        QueueList.get(index).setProcessedStatus(state);
    }

    /**
     * Takes in an index (for the bill) and whether or not it's processed and set's
     * it
     * 
     * @return Bill
     */
    public synchronized Bill getAvailableBill() {
        // Loops through the bills
        Bill bill = null;
        for (Bill b : QueueList) {
            // If one hasn't been processed
            if (b.getProcessedStatus() == false) {
                bill = b;
                break;
            }
        }
        return bill;
    }

    /**
     * Returns how many bills are in the queue
     * 
     * @return int
     */
    public int getQsize() {
        return QueueList.size();
    }

    /**
     * Removes the bill from the queue
     * 
     * @param b
     */
    public synchronized boolean removeQueueBill(Bill b) {
        boolean rtn = false;
        // Removed the bill from the list
        QueueList.remove(b);
        System.out.println("QueueList size is " + QueueList.size());
        System.out.println("NumberofBills  is " + NumberofBills);
        System.out.println("billsRemoved  is " + billsRemoved);

        // If the bill removed was the last one
        if ((NumberofBills - 1) == billsRemoved) {
            System.out.println("activated");
            rtn = true;
        }
        // Adds 1 onto bills removed
        billsRemoved++;
        notifyObservers();
        return rtn;
    }

    // Takes 1 away from bills removed
    public synchronized void decrementBillsRemoved() {
        billsRemoved--;
    }

    /**
     * Sets the queue size to be a given integer
     * 
     * @param number
     */
    public void setQueueSize(int number) {
        NumberofBills = number;
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

    public synchronized List<Bill> getQueueList() {
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
     * Removes at a bill from the queue list at an index position
     *
     * @param index
     */

    public void removeBillAtIndex(int index) {
        QueueList.remove(index);
        notifyObservers();
    }

    // Gets the table for the GUI
    public synchronized Vector<Vector<String>> getTable() {

        int i = getQueueList().size();

        // an ArrayList of ArrayLists
        Vector<Vector<String>> table = new Vector<Vector<String>>(i);

        // Create n lists one by one and append to the
        // master list (ArrayList of ArrayList)

        List<Bill> queueList = getQueueList();
        // For each bill in the queue
        for (Bill bill : queueList) {
            // Add a row for ID, size and price
            Vector<String> row = new Vector<String>();
            row.add(String.valueOf(bill.getCustomerID()));
            row.add(String.valueOf(bill.getOrderList().size()));
            row.add(String.valueOf(bill.getDiscountedPrice()));
            table.add(row);
        }

        return table;

    }

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
    public synchronized void notifyObservers() {

        try {
            for (Observer observer : registeredObservers)
                observer.update();
        }

        catch (Exception jd) {
            System.out.println(jd.getMessage());
        }
    }

}
