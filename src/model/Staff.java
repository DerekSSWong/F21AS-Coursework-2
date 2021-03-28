package model;
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
    private int timePerItem = 100;

    /**
     * Constructor for the class
     * 
     * @param serverID The unique ID of a server e.g. 1
     */
    public Staff(int staffID, String name) {

        this.staffID = staffID;
        this.name = name;
        this.working = false;

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
            Thread.sleep(amountOfItems * timePerItem);
            bill.setProcessedStatus(true);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Sets the working status of staff
     * @param working
     */
    public void setWorking(boolean working) {
    	this.working = working;
    }
    /**
     * Checks if the staff is currently processing an order
     * @return boolean
     */
    public boolean isWorking() {
    	return working;
    }
    
    public int getTimePerItem() {
    	return timePerItem;
    }
    public void setTimePerItem(int time) {
    	timePerItem = time;
    }
    	
}
