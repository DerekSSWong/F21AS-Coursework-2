
//Imports for unit test libraries
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the bill class
 * 
 * @author Rose Ulldemolins
 * 
 */
public class BillTest {

    private Order testOrder1;
    private Order testOrder2;
    private List<Order> orderList;
    private Bill testBill;

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
    }

    // Testing getting the customer ID
    @Test
    void testGetCustomerID() {
        assertEquals(1, testBill.getCustomerID());
    }

    // Testing getting the list of orders
    @Test
    void testGetOrderList() {
        assertEquals(orderList, testBill.getOrderList());
    }

    // Testing getting the discounted price on a bill
    @Test
    void testGetDiscountedPrice() {
        testBill.setDiscountedPrice();
        assertEquals(7.99, testBill.getDiscountedPrice());
    }

    // Testing calculating the total price
    @Test
    void testCalculateTotalPrice() {
        assertEquals(15.98, testBill.calculateTotalPrice());
    }

    // Testing adding an order
    @Test
    void testAddOrder() {
        testBill.addOrder(testOrder1);
        assertEquals(3, testBill.getOrderList().size());
    }

    // Testing removing an order
    @Test
    void testRemoveOrder() {
        testBill.removeOrder(testOrder1);
        assertEquals(1, testBill.getOrderList().size());
    }

}
