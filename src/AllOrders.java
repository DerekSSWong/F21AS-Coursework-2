import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 
 * Class for storing all orders
 * 
 * @author Dan Ryan
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

	/**
	 * 
	 * Creates a new arrayList used to overwrite arrayList when searching for Orders
	 * with a customerID
	 * 
	 */

	public void searchAllOrdersArrays() {
		orderSearch = new ArrayList<Order>();
	}

	/**
	 * Loops through ao treemap and checks if each value contains customerID adds
	 * Order to an arrayList if Order contains customerID
	 * 
	 * @param cusID
	 * @return ArrayList<Order>
	 */

	public ArrayList<Order> findByID(int cusID) {
		searchAllOrdersArrays();
		for (int i = 1; ao.lastKey() < i; i++) {
			if (ao.get(i).getCustomerID() == cusID) {
				orderSearch.add(ao.get(i));
			} else if (orderSearch.size() > 0) {
				break;
			} // if
		} // for
		return orderSearch;
	}// findByID
}
