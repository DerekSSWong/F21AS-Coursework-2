import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.Test;

class OrderTest {
    LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
    Item testItem = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
    Order testOrder = new Order(testDateTime, 1, testItem);

    // Testing getting order date and time
    @Test
    void testGetOrderDateTime() {
        assertEquals(testDateTime, testOrder.getOrderDateTime());
    }

    // Testing getting customer ID on order
    @Test
    void testGetCustomerID() {
        assertEquals(1, testOrder.getCustomerID());
    }

    // Testing getting the item on the order
    @Test
    void testGetItem() {
        assertEquals(testItem, testOrder.getItem());
    }

    // Testing getting the price of the order
    @Test
    void testGetPrice() {
        assertEquals(12.99, testOrder.getPrice());
    }

    // Testing updating the customer ID of an order
    @Test
    void testSetCustomerID() {
        testOrder.setCustomerID(3);
        assertEquals(3, testOrder.getCustomerID());
    }

    // Testing updating the customer ID of an order
    @Test
    void testSetItem() {
        Item testItem2 = new Item("TestItem2", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is also test item");
        testOrder.setItem(testItem2);
        assertEquals(testItem2, testOrder.getItem());
    }

    // Testing implementing the hashcode
    @Test
    void testHashCode() {
        assertEquals(1, testOrder.hashCode());
    }

}
