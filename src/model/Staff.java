// Stating the package
package model;

//Importing the components
import java.util.LinkedList;
import java.util.List;

// Importing the interface
import interfaces.Observer;

//Importing the model
import model.Item.ItemCat;

/**
 * Class for a server
 * 
 * @author Rose Ulldemolins, Andrew Daley
 * 
 */

public class Staff {

    private int staffID;
    protected String name;
    private boolean working;
    private static int timePerItem = 4000;
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
        // Gets the size of the order list
        int amountOfItems = bill.getOrderList().size();
        // Starts cooking status off as false
        boolean toCook = false;
        // Loops through all of the orders in the list
        for (Order od : bill.getOrderList()) {
            // If the item is a main
            if (od.getItem().getItemCat() == ItemCat.MAIN) {
                // Then set the staff to be cooking
                toCook = true;
                break;
            }
        }
        // Notify the observers of this change
        notifyObservers();
        // If the staff is cooking
        if (toCook) {
            System.out.println("Staff" + staffID + "waiting for the cook");
            // Get an available cook for the bill
            JobDispatcher.getAvailableCooks(bill);
        }

        try {
            // Make the thread sleep for a variable amount of time and then set the bill to
            // be processed
            Thread.sleep(amountOfItems * timePerItem);
            bill.setProcessedStatus(true);

        } catch (InterruptedException e) {
            // Because the thread is sleeping
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
        notifyObservers();
    }

    /**
     * Returns if the staff is on shift
     * 
     * @return boolean
     */
    public boolean getOnShift() {
        return onShift;
    }

    /**
     * Returns the time it takes to process an item
     * 
     * @return int
     */
    public int getTimePerItem() {
        return timePerItem;
    }

    /**
     * Sets the time it takes to process an item
     * 
     * @param int
     */
    public static void setTimePerItem(int time) {
        timePerItem = time;
    }

    /**
     * Assigns a given bill (to the staff)
     * 
     * @param Bill
     */
    public void assignBill(Bill bill) {
        currentlyProcessing = bill;

    }

    /**
     * Returns the bill a staff member is currently processing
     * 
     * @return int
     */
    public Bill getBill() {
        return currentlyProcessing;
    }

    // Removes the bill from the staff
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
