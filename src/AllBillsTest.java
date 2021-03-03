import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class AllBillsTest {
	
	private Order testOrder1;
	private Order testOrder2;
	private ArrayList<Order> orderList;
	private Bill testBill;
	private AllBills testAllBills;
    
    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JUNE, 19, 20, 30, 40);
        Item testItem1 = new Item("tst001", Item.ItemCat.COLDDRINK, "TestItem", "This is a test item", 12.99);
        Item testItem2 = new Item("tst002", Item.ItemCat.HOTDRINK, "TestItem", "This is another test item", 2.99);
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 1, testItem2);
        orderList = new ArrayList<Order>();
        orderList.add(testOrder1);
        orderList.add(testOrder2);
        testBill = new Bill(orderList, 1);
        testAllBills = new AllBills();
        testAllBills.addBill(testBill);
    }
    
    @Test
    void testgetNetIncome() {
		assertEquals(15.98, testAllBills.getNetIncome());
    }
    
    @Test
    void testfindBill() {
    	assertEquals(testBill, testAllBills.findBill(1));
    }
   
}
