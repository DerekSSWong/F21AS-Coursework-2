package model;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import model.Item.ItemCat;

public class KitchenStaff extends Staff {

	final private Condition alert;
	final private ReentrantLock personallock;
	private KitchenStaff thisStaff;
	String cookingState = "";
	Order currentlyCooking = null;
	Bill currentlyWorking;

	public ReentrantLock getLock() {
		return personallock;
	}

	public KitchenStaff(int staffID, String name) {
		super(staffID, name);
		personallock = new ReentrantLock();
		alert = personallock.newCondition();
		moveToKitchen(staffID);

	}

	private void moveToKitchen(int staffID) {
		Thread ks = new Thread() {
			public void run() {
				while (true) {
					personallock.lock();
					cookingState = "Waiting to cook";
					notifyObservers();
					System.out.println("kitchen staff " + name + " is waiting for meals to cook");

					try {
						alert.await();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					ArrayList<String> thingsBeingHeatedup = new ArrayList<String>();
					for(Order od: currentlyWorking.getOrderList()) {
			        	if(od.getItem().getItemCat() == ItemCat.MAIN) {
			        		thingsBeingHeatedup.add(od.getItem().getItemName());
			        		
			        	}
					}
					cookingState = "Currently cooking " +  currentlyWorking.getCustomerID() ; 
					for(String str :thingsBeingHeatedup)
						cookingState += str + "  ";
						
						
					//cookingState = "Cooking the meal";
					notifyObservers();
					System.out.println("kitchen staff " + name + " is cooking the meal");

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cookingState = "Returning the meal";
					notifyObservers();
					System.out.println("kitchen staff " + getName() + "has cooked the meal, returning it to server");
					alert.signal();
					personallock.unlock();
					JobDispatcher.getInstance().addCookStaffList(thisStaff);
					notifyObservers();
				}
			}
		};
		ks.start();

	}

	Condition getAlert() {
		return alert;

	}

	public String getCookingState() {
		return cookingState;
	}

	public void storeStaff(Bill currentlyWorking) {
		thisStaff = this;
		this.currentlyWorking = currentlyWorking;
	}

	/**
	 * Sets whether the order the staff is cooking
	 * 
	 * @param order
	 */
	public void setCurrentlyCooking(Order order) {
		this.currentlyCooking = order;
		notifyObservers();
	}
	
	

}
