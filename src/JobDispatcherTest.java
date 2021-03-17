
//Imports for unit test libraries
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JobDispatcherTest {
	private Order testOrder1;
    private Order testOrder2;
    private Order testOrder3;
    private Order testOrder4;
    private Order testOrder5;
    private Order testOrder6;
    private Bill testBill1;
    private Bill testBill2;
    private Bill testBill3;
    private Staff testStaff1;
    private Staff testStaff2;

    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
        Item testItem2 = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is another test item");
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 2, testItem2);
        testOrder3 = new Order(testDateTime, 2, testItem1);
        testOrder4 = new Order(testDateTime, 3, testItem1);
        testOrder5 = new Order(testDateTime, 3, testItem2);
        testOrder6 = new Order(testDateTime, 3, testItem2);
        
        testBill1 = new Bill(1);
        testBill1.addOrder(testOrder1);
        
        testBill2 = new Bill(2);
        testBill2.addOrder(testOrder2);
        testBill2.addOrder(testOrder3);
        
        testBill3 = new Bill(3);
        testBill3.addOrder(testOrder4);
        testBill3.addOrder(testOrder5);
        testBill3.addOrder(testOrder6);
        
        testStaff1 = new Staff(1, "Joe Bloggs");
        testStaff2 = new Staff(2, "John Cena");
        
    }

    @Test
    public void oneJobOneStaff() {
    	JobDispatcher dispatcher = new JobDispatcher();
    	
    	dispatcher.addBill(testBill1);
    	dispatcher.addStaff(testStaff1);
    	
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	dispatcher.dispatch();
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void oneJobManyStaff() {
    	JobDispatcher dispatcher = new JobDispatcher();
    	
    	dispatcher.addBill(testBill1);
    	dispatcher.addStaff(testStaff1);
    	dispatcher.addStaff(testStaff2);
    	
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	dispatcher.dispatch();
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void manyJobOneStaff() {
    	JobDispatcher dispatcher = new JobDispatcher();
    	
    	dispatcher.addBill(testBill1);
    	dispatcher.addBill(testBill2);
    	dispatcher.addBill(testBill3);
    	dispatcher.addStaff(testStaff1);

    	
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	dispatcher.dispatch();
    	try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void manyJobManyStaff() {
    	JobDispatcher dispatcher = new JobDispatcher();
    	
    	dispatcher.addBill(testBill1);
    	dispatcher.addBill(testBill2);
    	dispatcher.addBill(testBill3);
    	dispatcher.addStaff(testStaff1);
    	dispatcher.addStaff(testStaff2);
    	
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	dispatcher.dispatch();
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    	
}
