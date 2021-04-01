// Setting the package
package model;

//Importing class
import java.time.LocalDateTime;

/**
 * Class for storing information of orders
 * 
 * @author Rose Ulldemolins
 * 
 */

public class Order {

	private final LocalDateTime orderDateTime;
	private int customerID;
	private Item item;

	/**
	 * Constructor for the class
	 * 
	 * @param orderDateTime The date and time of the order e.g. 2019-10-06T10:34
	 * @param customerID    The unique ID of a customer e.g. 4
	 * @param item          The item that has been ordered
	 */
	public Order(LocalDateTime orderDateTime, int customerID, Item item) {
		this.customerID = customerID;
		this.item = item;
		this.orderDateTime = orderDateTime;
	}

	/**
	 * Returns the date and time of the order
	 * 
	 * @return LocalDateTime
	 */
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	/**
	 * Returns the customer ID of the order
	 * 
	 * @return int
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * Returns the item on an order
	 * 
	 * @return Item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Returns the price of an order
	 * 
	 * @return price
	 */
	public double getPrice() {
		return item.getItemPrice();
	}

	/**
	 * Updates the customer ID of an order
	 * 
	 * @param cusID
	 */
	public void setCustomerID(int cusID) {
		customerID = cusID;
	}

	/**
	 * Updates the item on an order
	 * 
	 * @param updatedItem
	 */
	public void setItem(Item updatedItem) {
		item = updatedItem;
	}

}
