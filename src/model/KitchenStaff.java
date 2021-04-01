// Stating the package
package model;

// Importing other classes including concurrency 
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Importing model
import model.Item.ItemCat;

public class KitchenStaff extends Staff {
	final private Condition alert;
	final private ReentrantLock personalLock;
	private KitchenStaff thisStaff;
	String cookingState = "";
	Bill currentlyWorking;

	public ReentrantLock getLock() {
		return personalLock;
	}

	public KitchenStaff(int staffID, String name) {
		super(staffID, name);
		personalLock = new ReentrantLock();
		alert = personalLock.newCondition();
		// Moves the staff to the kitchen
		moveToKitchen(staffID);

	}

	private void moveToKitchen(int staffID) {
		// Starts a new thread
		Thread ks = new Thread() {
			public void run() {
				// So that it keeps running
				while (true) {
					// Locks, sets the cook to be cooking and then notifies observers
					personalLock.lock();
					cookingState = "Waiting to cook";
					notifyObservers();
					System.out.println("kitchen staff " + name + " is waiting for meals to cook");

					try {
						alert.await();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

					// List of items being cooked
					ArrayList<String> thingsBeingHeatedup = new ArrayList<String>();
					// Loops through the order list of the bill they're currently working
					for (Order od : currentlyWorking.getOrderList()) {
						// If it's a main
						if (od.getItem().getItemCat() == ItemCat.MAIN) {
							// Then add
							thingsBeingHeatedup.add(od.getItem().getItemName());

						}
					}
					// Starts off as an empty string
					cookingState = "";
					// Loops through the names of items being heated up
					for (String str : thingsBeingHeatedup)
						// Adds them to the string with a new line
						cookingState += str + "\n";
					// Tells the obsservers
					notifyObservers();
					System.out.println("kitchen staff " + name + " is cooking the meal");
					// Thread is sleeping so needs to catch the exception
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Sets the meal to be returned
					cookingState = "Returning the meal";
					System.out.println("Kitchen staff " + getName() + "has cooked the meal, returning it to server");
					alert.signal();
					// Unlocks and adds the staff back to the staff list
					personalLock.unlock();
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

}
