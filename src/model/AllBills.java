package model;
import java.util.HashMap;

/**
 * 
 * Class for storing all bills
 * 
 * @author Dan Ryan
 *
 */

public class AllBills {
	
	HashMap<Integer, Bill> ab = new HashMap<Integer, Bill>();
	
	/**
	 * adds bill to hashmap using bill customerID as the key
	 * 
	 * @param bill
	 */
	
	public void addBill(Bill bill) {
		ab.put(bill.getCustomerID(), bill);
	}
	
	/**
	 * Creates a net income
	 * Loops through ab hashmap entries and adds the calculated price from each bill to the net income
	 * 
	 * @return double
	 */
	
	public double getNetIncome() {
		Double netIncome = 0.00;
		for (HashMap.Entry<Integer, Bill> entry : ab.entrySet()) {
			netIncome += entry.getValue().getDiscountedPrice();
			
		}//for
		return netIncome;
	}//getNetIncome
	
	/**
	 * finds a bill using the customer ID as a key
	 * 
	 * @param cusID
	 * @return Bill
	 */
	
	public Bill findBill(int cusID) {
		return ab.get(cusID);
	} 
	

	public HashMap<Integer, Bill> getBillList() {
		return ab;
	}

	
}
