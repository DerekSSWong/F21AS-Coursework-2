package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenStaff extends Staff {

	final private Condition alert;
	final private ReentrantLock personallock;
	private KitchenStaff thisStaff;
	String cookingState = "";

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
				System.out.println("staff ID: " + staffID);
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

					cookingState = "Cooking the meal";
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
					
					
					//JobDispatcher.cookLock.lock();
					JobDispatcher.getInstance().addCookStaffList(thisStaff);
					//JobDispatcher.cookLock.unlock();
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
	
	public void storeStaff() {
		//System.out.println("This is " + this.getName());
		thisStaff = this;
	}

}


