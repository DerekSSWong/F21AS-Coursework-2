package model;

import java.util.LinkedList;
import java.util.List;

import interfaces.Observer;

/**
 * Class for a server
 * 
 * @author Rose Ulldemolins
 * 
 */

public class Staff {

    private int staffID;
    private String name;
    private boolean working;
    private static int timePerItem = 5000;
    private Bill currentlyProcessing = null;
    private boolean onShift;

    /**
     * Constructor for the class
     * 
     * @param serverID The unique ID of a server e.g. 1
     */
    public Staff(int staffID, String name) {

        this.staffID = staffID;
        this.name = name;
        this.working = false;
        this.onShift = true;

    }

    /**
     * Returns the ID of the staff member e.g. 1
     * 
     * @return int
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * Returns the name of a staff member e.g. Elvis Presley
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Processes the Bill
     * 
     * @param bill
     */
    public void processBill(Bill bill) {

        int amountOfItems = bill.getOrderList().size();
        try {
            notifyObservers();
            Thread.sleep(amountOfItems * timePerItem);
            bill.setProcessedStatus(true);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sets the working status of staff
     * 
     * @param working
     */
    public void setWorking(boolean working) {
        this.working = working;
    }

    /**
     * Checks if the staff is currently processing an order
     * 
     * @return boolean
     */
    public boolean isWorking() {
        return working;
    }

    /**
     * Checks if the staff is currently on shift
     * 
     * @return boolean
     */
    public boolean isOnShift() {
        return onShift;
    }

    /**
     * Sets whether the staff is on shift
     * 
     * @param working
     */
    public void setOnShift(boolean onShift) {
        this.onShift = onShift;
    }

    public int getTimePerItem() {
        return timePerItem;
    }

    public static void setTimePerItem(int time) {
        timePerItem = time;
    }

    public void assignBill(Bill bill) {
        currentlyProcessing = bill;

    }

    public Bill getBill() {

        return currentlyProcessing;

    }

    public void removeBill() {
        currentlyProcessing = null;
    }

    // Following the observer patter - Staff must be able to register, remove and
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
