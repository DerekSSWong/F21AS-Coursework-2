
//Imports for class
import java.util.List;

/**
 * Class for storing orders on a particular bill
 * 
 * @author Rose Ulldemolins
 * 
 */

public class Bill {

    private List<Order> orderList;
    private int customerID;
    private double discountedPrice;

    /**
     * Constructor for the class
     * 
     * @param orderList       The list of Order's made by a customer
     * @param customerID      The unique identifier of the customer who the bill
     *                        belongs to e.g. 1
     * @param discountedPrice The price after discounts of ther bill e.g. £3.50
     */
    public Bill(List<Order> orderList, int customerID) {

        this.orderList = orderList;
        this.customerID = customerID;
        this.discountedPrice = discountedPrice;
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

    }

    /**
     * Removes an order from the bill
     * 
     * @param order
     */
    public void removeOrder(Order order) {
        orderList.remove(order);
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
    public void setDiscountedPrice(double discount) {
        discountedPrice = calculateTotalPrice() - discount;
    }

}