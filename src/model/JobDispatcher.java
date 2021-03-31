package model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Gradually queues bills from AllBills for server(s) to process
 * 
 * @author Derek
 *
 */
public class JobDispatcher {
	private ReentrantLock lock = new ReentrantLock();
	private String log = "";
	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	private ArrayList<Job> jobList = new ArrayList<Job>();
	public Queue q = new Queue();
	private int totalSize = 0;
	private boolean isLast = false;
	private int queueDelay = 2000;
	private Bill lastBillItem;
	static JobDispatcher dispatcher = new JobDispatcher();
	boolean lastBill=false;

	// Singleton
	private JobDispatcher() {
	}

	public static JobDispatcher getInstance() {
		return dispatcher;
	}

	public synchronized void addStaff(Staff staff) {
		// Setting the staff to be working
		staff.setOnShift(true);
		staff.setWorking(false);
		// Adding the staff to the staff list
		staffList.add(staff);
		// Adding to log and printing
		addToLog("Staff " + staff.getStaffID() + " added");
		System.out.println("Staff " + staff.getStaffID() + " added");
	}

	public synchronized void removeStaff(Staff staff) {
		lock.lock();
		// Setting the staff to be no longer working
		staff.setOnShift(false);
		// Removing from the staff list
		staffList.remove(staff);
		// Adding to log and printing
		for(Job jb : jobList) {
			
			if(jb.getStaff()== staff){
				
				if(jb.getStatus() == true) {
				
				
				jb.decatiavteJob();
				q.addQueueBill(jb.getBill());
				q.decrementBillsRemoved();
				jb.getBill().setProcessedStatus(false);
				lastBill=false;
				System.out.println("Bill being added back on queue");
				System.out.println("Bill with customer id " + jb.getBill().getCustomerID() + " was not finished by staff "+ staff.getStaffID() + " so is put back on stafflist "   );
				addToLog("Bill with customer id " + jb.getBill().getCustomerID() + " was not finished by staff "+ staff.getStaffID() + " so is put back on stafflist "   );
				q.notifyObservers();
				}
			}
			
		}
		
		addToLog("Staff " + staff.getStaffID() + " removed");
		System.out.println("Staff " + staff.getStaffID() + " removed");
		lock.unlock();
	}

		
	
	// Adds a bill to the queue
	public void addBill(Bill bill) {
		q.addQueueBill(bill);
	}

	// A method to find an available bill and staff member, assign them to each
	// other and set the working/processed boolean for each
	private void findStaffAndBill() {
		Staff staff = null;
		Integer sIndex = null;

		// Attempts to find an available bill
		Bill bill = q.getAvailableBill();
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
		if (bill == null && staff != null) {
			staff.setWorking(false);
		}
		if (bill != null && staff == null) {
			q.setBillProcessedState(q.getQueueIndex(bill), false);
		}

		// If available bill and staff is found, a job is created
		if (bill != null && staff != null) {
			Job jb = new Job(staff,bill);
			jobList.add(jb);
			
			job(staff, bill,jb);
			
			 
		}
	}

	
	private class Job {

		private Staff staff;
		private Bill bill;
		boolean running = true;
		
		public Job(Staff staff, Bill bill) {
			
			this.staff =staff;
			this.bill =bill;
			
		}
		
		public Bill getBill() {
			return bill;
			
		}

		void decatiavteJob() {
			running= false;
		}
		
		boolean getStatus() {
			return running;
		}
		
		 
		private Staff getStaff(){
			return staff;
		}		
		
	}
	
	
	
	/**
	 * Checks the availabilities of staff and bills - if both are available, assigns
	 * a bill to a staff
	 */
	public synchronized void dispatch() {

		// Creates a new thread
		Thread thread = new Thread() {
			public void run() {
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
				while (!isLast) {
					// Calls the method to find an available staff and bill
					findStaffAndBill();

					// Exits the while loop if the last job is processed
					if (isLast) {
						System.out.println("Last Bill has this number of orders" + lastBillItem.getOrderList().size());
						sleepTime = lastBillItem.getOrderList().size() * getList(0).getTimePerItem();
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
				
				Manager.getInstance().writeFile();
				writeLog();
				System.exit(0);

			}
		};
		thread.start();

	}
	
	//public void setStaffJob(staff) {
		
	//	b
		
	//}
	
	
	//public void takeningJob(staff st, bill bill) {
		
	//	Bill currentBill = bill;
		
		
	//}

	/**
	 * Where a staff processes a bill
	 * 
	 * @param s Staff
	 * @param b Bill
	 */
	public void job(Staff s, Bill b, Job j) {
		Thread task = new Thread() {
			public void run() {

				int sIndex = staffList.indexOf(s);

				// Set the staff to be working as assign them the bill
				s.setWorking(true);
				s.assignBill(b);
				// Add this to the log and print
				addToLog("Staff " + s.getStaffID() + " is processing bill " + b.getCustomerID() + " size "
						+ b.getOrderList().size());
				System.out.println("Staff " + s.getStaffID() + " is processing bill " + b.getCustomerID() + " size "
						+ b.getOrderList().size());

				lastBill = q.removeQueueBill(b);
				// Start the staff processing the bill and set them to be working
				s.processBill(b);
				
				//System.out.println("staff" + s.getStaffID() +"is here");
				
								
				
				if(!j.getStatus()) {
					lock.lock();
					//lastBill=false;
					jobList.remove(j);
					
					//q.addQueueBill(b);
					//q.decrementBillsRemoved();
					b.setProcessedStatus(false);
					//lastBill=false;
					//System.out.println("Bill being added back on queue");
					//System.out.println("Bill with customer id " + b.getCustomerID() + " was not finished by staff "+ s.getStaffID() + " so is put back on stafflist "   );
					q.notifyObservers();
					jobList.remove(j);
					
					lock.unlock();
				}
				else {
				lock.lock();
				s.setWorking(false);
				// Add this to the log and print
				
				addToLog("Bill " + b.getCustomerID() + " finished");
				System.out.println("Bill " + b.getCustomerID() + " finished by staff" + s.getStaffID() );
				s.removeBill();
				jobList.remove(j);

				// Checks if the bill is the last one left
				if (lastBill) {
					System.out.print("Bill + " + b.getCustomerID() + "was the last one");
					isLast = true;
					lastBillItem = b;
				
				}
				lock.unlock();
				}
				
			}
		};
		task.start();
	}
	
	
	public synchronized Staff getList(int sIndex){
		return staffList.get(sIndex);
		
		
	}

	/**
	 * Reads the files with the Manager class and loads them into the Queue instance
	 * at a fixed rate
	 */
	public void loadBills() {
		Thread lb = new Thread() {
			public void run() {
				// Reads files
				HashMap<Integer, Bill> allBills = Manager.getInstance().getAllBills().getBillList();
				totalSize = allBills.size();
				q.setQueueSize(allBills.size());

				// Loads the bills into Queue with a set delay
				for (Map.Entry<Integer, Bill> entry : allBills.entrySet()) {
					// Locking while the bill is added
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

	// Method to write to the log
	public void writeLog() {
		try {
			FileWriter reportFile = new FileWriter("Log.csv");
			reportFile.write(log);
			reportFile.close();

		}
		// Displays an error if the file isn't in the folder
		catch (FileNotFoundException fnf) {
			System.out.println("Log.csv" + " not found ");
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
