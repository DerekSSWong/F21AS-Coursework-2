package model;

//Imports for class
import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing orders on a particular bill
 * 
 * @author Rose Ulldemolins
 * 
 */

public class Bill {
    private List<Order> orderList = new ArrayList<Order>();
    private int customerID;
    private double discountedPrice;
    private boolean processed = false;

    /**
     * Constructor for the class
     * 
     * @param customerID The unique identifier of the customer who the bill belongs
     *                   to e.g. 1
     */
    public Bill(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Calculates the total price of a bill
     * 
     * @return double
     */
    public double calculateTotalPrice() {
        double total = 0;
        for (Order order : orderList) {
            total += order.getPrice();
        }
        return total;
    }

    /**
     * Adds an order to the bill
     * 
     * @param order
     */
    public void addOrder(Order order) {
        orderList.add(order);
        setDiscountedPrice();

    }

    /**
     * Removes an order from the bill
     * 
     * @param order
     */
    public void removeOrder(Order order) {
        orderList.remove(order);
        setDiscountedPrice();
    }

    /**
     * Removes an order from the bill by index
     * 
     * @param index
     */
    public void removeOrderByIndex(int index) {
        orderList.remove(index);
        setDiscountedPrice();
    }

    /**
     * Returns the ID of the customer
     * 
     * @return int
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Returns the processing status of a bill
     * 
     * @return boolean
     */
    public boolean getProcessedStatus() {
        return processed;
    }

    /**
     * Returns orders on a bill
     * 
     * @return List<Order>
     */
    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     * Returns the price of a bill after discount
     * 
     * @return double
     */
    public double getDiscountedPrice() {
        return discountedPrice;
    }

    /**
     * Sets the discounted price of a bill
     * 
     * @param discount
     */
    public void setDiscountedPrice() {
        DiscountChecker discountChecker = new DiscountChecker(orderList, calculateTotalPrice());
        discountedPrice = calculateTotalPrice() - discountChecker.overallDiscount();
       // System.out.println("discounted price" +discountedPrice);
    }

    /**
     * Sets the processing status
     * 
     * @param isProcessed
     */
    public void setProcessedStatus(boolean isProcessed) {
        processed = isProcessed;
    }

}
