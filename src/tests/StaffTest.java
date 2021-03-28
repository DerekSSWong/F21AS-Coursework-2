package tests;
import model.*;
//Imports for unit test libraries
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the staff class
 * 
 * @author Rose Ulldemolins
 * 
 */
public class StaffTest {

    private Order testOrder1;
    private Order testOrder2;
    private List<Order> orderList;
    private Bill testBill;
    private Staff testStaff;

    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
        Item testItem2 = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is another test item");
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 1, testItem2);
        orderList = new ArrayList<Order>();
        orderList.add(testOrder1);
        orderList.add(testOrder2);
        testBill = new Bill(1);
        testBill.addOrder(testOrder1);
        testBill.addOrder(testOrder2);
        testStaff = new Staff(1, "Joe Bloggs");
    }

    // Testing getting the staff ID
    @Test
    void testGetStaffID() {
        assertEquals(1, testStaff.getStaffID());
    }

    // Testing getting the name of a staff member
    @Test
    void testGetName() {
        assertEquals("Joe Bloggs", testStaff.getName());
    }

    // Testing processing a Bill
    @Test
    void testProcessBill() {
        testStaff.processBill(testBill);
        assertEquals(true, testBill.getProcessedStatus());
    }

}
