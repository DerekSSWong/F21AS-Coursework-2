/**
 * Class for a server
 * 
 * @author Rose Ulldemolins
 * 
 */

public class Staff {

    private int staffID;
    private String name;

    /**
     * Constructor for the class
     * 
     * @param serverID The unique ID of a server e.g. 1
     */
    public Staff(int staffID, String name) {

        this.staffID = staffID;
        this.name = name;

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
            Thread.sleep(amountOfItems * 100);
            bill.setProcessedStatus(true);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
