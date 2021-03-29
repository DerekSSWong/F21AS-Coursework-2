package model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Gradually queues bills from AllBills for server(s) to process
 * 
 * @author Derek
 *
 */
public class JobDispatcher {

	public ReentrantLock lock = new ReentrantLock();

	public String log = "";

	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	public Queue q = new Queue();

	private int totalSize = 0;
	private boolean isLast = false;
	private int queueDelay = 4500;
	private Bill lastBillItem;

	static JobDispatcher dispatcher = new JobDispatcher();

	// Singleton
	private JobDispatcher() {
	}

	public static JobDispatcher getInstance() {
		return dispatcher;
	}

	public void addStaff(Staff staff) {
		lock.lock();
		staffList.add(staff);
		addToLog("Staff " + staff.getStaffID() + " added");
		System.out.println("Staff " + staff.getStaffID() + " added");
		lock.unlock();
	}

	// obsolete method
	public void addBill(Bill bill) {
		q.addQueueBill(bill);
	}

	/**
	 * Checks the availabilities of staff and bills If both are available, assigned
	 * a bill to a staff
	 */
	public void dispatch() {
		Thread thread = new Thread() {
			public void run() {

				Bill bill = null;
				Staff staff;
				int sleepTime = 100;

				// Waits for the totalSize to be loaded
				while (totalSize == 0) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// Keep checking for available tasks as long as nobody's processing the last
				// bill
				while (true) {

					lock.lock();
					staff = null;
					Integer sIndex = null;

					// Attempts to find an available bill
					bill = q.getAvailableBill();
					if (bill != null) {
						q.setBillProcessedState(q.getQueueIndex(bill), true); // Marks the bill to be processed
					}

					// Attempts to find an available staff
					for (Staff s : staffList) {
						if (s.isWorking() == false) {
							s.setWorking(true); // Marks the staff to be assigned
							sIndex = staffList.indexOf(s);
							staff = s;
							break;
						}
					}

					// Clears the marks if no bill is available for worker, or no worker is
					// available for bill
					if (bill == null & staff != null) {
						staffList.get(sIndex).setWorking(false);
					}
					if (bill != null & staff == null) {
						q.setBillProcessedState(q.getQueueIndex(bill), false);
					}

					lock.unlock();

					// If available bill and staff is found, a job is created
					if (bill != null & staff != null) {
						job(staff, bill);
					}

					// Exits the while loop if the last job is processed
					if (isLast) {
						System.out.println("Last Bill has this number of orders" + lastBillItem.getOrderList().size());
						sleepTime = lastBillItem.getOrderList().size() * staffList.get(0).getTimePerItem();
						break;
					}

					// Small delay between each loop
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				// Report related methods can go here
				addToLog("All jobs processed, producing report...");
				System.out.println("All jobs processed, producing report...");
				Manager manager = new Manager();
				manager.readFile("Menu.csv");
				manager.readFile("ExistingOrders.csv");
				manager.toBills();
				manager.writeFile();
				writeLog();
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.exit(0);

			}
		};
		thread.start();

	} // start()

	/**
	 * Where a staff processes a bill
	 * 
	 * @param s Staff
	 * @param b Bill
	 */
	public void job(Staff s, Bill b) {
		Thread task = new Thread() {
			public void run() {

				int sIndex = staffList.indexOf(s);

				staffList.get(sIndex).setWorking(true);
				staffList.get(sIndex).assignBill(b);

				addToLog("Staff " + s.getStaffID() + " is processing bill " + b.getCustomerID() + " size "
						+ b.getOrderList().size());
				System.out.println("Staff " + s.getStaffID() + " is processing bill " + b.getCustomerID() + " size "
						+ b.getOrderList().size());
				
				boolean LastBill = q.removeQueueBill(b);
				
				
				s.processBill(b);
				staffList.get(sIndex).setWorking(false);
				addToLog("Bill " + b.getCustomerID() + " finished");
				System.out.println("Bill " + b.getCustomerID() + " finished");

				staffList.get(sIndex).removeBill();

				// checks if the bill is the last one left
				if (LastBill) {
					System.out.print("Not meant to be here");
					isLast = true;
					lastBillItem =b;
				}
				
				// checks if the bill is the last one left
				//if (q.getQueueIndex(b) == totalSize - 1) {
				//	isLast = true;
				//}

		
			}
		};
		task.start();
	}

	/**
	 * Reads the files with the Manager class and loads them into the Queue instance
	 * at a fixed rate
	 */
	public void loadBills() {
		Thread lb = new Thread() {
			public void run() {

				// Reads files
				Manager manager = new Manager();
				manager.readFile("Menu.csv");
				manager.readFile("ExistingOrders.csv");
				manager.toBills();
				HashMap<Integer, Bill> allBills = manager.getAllBills().getBillList();
				totalSize = allBills.size();
				
				q.setQueueSize(allBills.size());
				
				// System.out.println(totalSize);

				// Loads the bills into Queue with a set delay
				for (Map.Entry<Integer, Bill> entry : allBills.entrySet()) {
					lock.lock();
					q.addQueueBill(entry.getValue());
					
					lock.unlock();
					try {
						Thread.sleep(queueDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		lb.start();
	}

	public synchronized void addToLog(String str) {
		str += "\n";
		log += str;
	}

	// Shamelessly ripped off Andrew's writeFile() method
	public void writeLog() {
		try {
			FileWriter reportFile = new FileWriter("Log.csv");
			reportFile.write(log);
			reportFile.close();

		}
		// Displays an error if the file isn't in the folder
		catch (FileNotFoundException fnf) {
			System.out.println("log.csv" + " not found ");
			System.exit(0);
		}
		// stack trace
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	public void setQueueDelay(int time) {
		queueDelay = time;
	}
	
	public int getQueueDelay() {
		return queueDelay;
	}
}
