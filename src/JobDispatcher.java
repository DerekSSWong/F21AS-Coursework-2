import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class JobDispatcher {
	
	private ArrayList<Bill> billList = new ArrayList<Bill>();
	public ReentrantLock lock = new ReentrantLock();
	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	
	//not thread safe yet
	public void addStaff(Staff staff) {
		staffList.add(staff);
	}
	
	//not thread safe yet
	public void addBill(Bill bill) {
		billList.add(bill);
	}
	
	/**
	 * Checks the availabilities of staff and bills
	 * If both are available, assigned a bill to a staff
	 */
	public void dispatch() {
		Thread thread = new Thread() {
			public void run() {
				
				Bill bill = null;
				Staff staff = null;
				
				lock.lock();
					for (Bill b : billList) {
						if (b.getProcessedStatus() == false) {
							b.setProcessedStatus(true); //Marks the bill to be processed
							bill = b;
							break;
						}
					}
					for (Staff s : staffList) {
						if (s.isWorking() == false) {
							s.setWorking(true); //Marks the staff to be assigned
							staff = s;
							break;
						}
					}
					
					//Clears the marks if no bill is available for worker, or no worker is available for bill
					if (bill == null & staff != null) {
						staff.setWorking(false);
					}
					if (bill != null & staff == null) {
						bill.setProcessedStatus(false);
					}
				lock.unlock();
				
				//If available bill and staff is found, a job is created
				if (bill != null & staff != null) {
					job(staff, bill);
					dispatch();
				} 
				//If no staff is available, wait a bit then try again
				else if (bill != null & staff == null) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					dispatch();
				} 
				//If there are no available bills, then we're done
				else {System.out.println("All bills are processing/processed");}
			}
		};
		thread.start();
	
	} //start()
	
	/**
	 * Where a staff processes a bill
	 * @param s Staff
	 * @param b Bill
	 */
	public void job(Staff s, Bill b) {
		Thread task = new Thread() {
			public void run() {
				System.out.println("Staff " + s.getStaffID() + " is processing bill " + b.getCustomerID());
				s.processBill(b);
				System.out.println("Bill " + b.getCustomerID() + " finished: " + b.getProcessedStatus());
			}
		};
		task.start();
	}
}
