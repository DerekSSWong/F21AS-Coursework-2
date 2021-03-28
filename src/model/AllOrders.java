package model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * Class for storing all orders
 * 
 * @author Dan Ryan, Derek Wong, Andrew Dalley
 *
 */

public class AllOrders {

	int i = 1;
	TreeMap<Integer, Order> ao = new TreeMap<Integer, Order>();
	ArrayList<Order> orderSearch;

	/**
	 * 
	 * Adds order to treemap with key i increments i to ensure key is unique
	 *
	 */

	public void addOrder(Order order) {
		ao.put(i, order);
		i++;
	}

	//Test method by Derek
		//Prints cusID and itemName of all existing orders
		public void printOrders() {
			String[] order = new String[2];
			System.out.println(ao.size());
			
			for (Map.Entry<Integer, Order> entry : ao.entrySet()) {
				order[0] = Integer.toString(entry.getValue().getCustomerID());
				order[1] = entry.getValue().getItem().getItemName();
				System.out.println(Arrays.toString(order));
			}
				
		}
		
	/**
	 * Loops through ao treemap and checks if each value contains customerID adds
	 * Order to an arrayList if Order contains customerID
	 * 
	 * @param cusID
	 * @return ArrayList<Order>
	 */
	public ArrayList<Order> findByID(int cusID) {
		orderSearch = new ArrayList<Order>();
		for (Map.Entry<Integer, Order> entry : ao.entrySet()) {
			if (entry.getValue().getCustomerID() == cusID) {
				orderSearch.add(entry.getValue());
			} else if (orderSearch.size() > 0) {
				break;
			} // if
		} // for
		return orderSearch;
	}// findByID
	
	
	/**
	 * Loops through all the orders made and adds up how many times each item was
	 * ordered, then returns this value.   
	 * 
	 * @param item
	 * @return counter
	 */
	public int frequencyOfItem(Item item) {
		orderSearch = new ArrayList<Order>();
		int counter = 0 ;
		for (Map.Entry<Integer, Order> entry : ao.entrySet()) {
			if (entry.getValue().getItem().getItemID().equals( item.getItemID())) 
				counter++;
		} 
		return counter;
	} 
	
	
	
	
	
	
}
