package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenStaff extends Staff {

	final private Condition alert;
	final private ReentrantLock personallock;
	
	
	public ReentrantLock getLock() {
		return personallock;
	}

	public KitchenStaff(int staffID, String name) {
		super(staffID, name);
		
			
			
			personallock = new ReentrantLock();
			alert =  personallock.newCondition();
			moveToKitchen();
	
			
	}

	private void moveToKitchen() {
		Thread ks = new Thread() {
			public void run() {
				
				
				while(true) {
				
				personallock.lock();
				
				
				
				System.out.println("kitchen staff "+ getName() + " is waiting for meals to cook" );
				
				try {
					alert.await();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println("kitchen staff "+ getName() + "is cooking the meal" );
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("kitchen staff "+ getName() + "has cooked the meal, returning it to server" );
				alert.signal();
				personallock.unlock();	
				}
			}
			};
			ks.start();
			
		
	}
	
	
	Condition getAlert() {
		return alert;
		
	}
	
	
	
	
}
