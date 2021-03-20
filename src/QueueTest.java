import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *Class for testing the queue class
 *
 */

public class QueueTest {

    private Order testOrder1;
    private Order testOrder2;
    private Order testOrder3;
    private Order testOrder4;
    private List<Order> orderList;
    private Bill testBill1;
    private Bill testBill2;
    private Queue queueList;

    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
        Item testItem2 = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is another test item");
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 1, testItem2);
        testOrder3 = new Order(testDateTime, 2, testItem1);
        testOrder4 = new Order(testDateTime, 2, testItem1);
        orderList = new ArrayList<Order>();
        orderList.add(testOrder1);
        orderList.add(testOrder2);
        testBill1 = new Bill(1);
        testBill1.addOrder(testOrder1);
        testBill1.addOrder(testOrder2);
        testBill2 = new Bill(2);
        testBill2.addOrder(testOrder3);
        testBill2.addOrder(testOrder4);
        queueList = new Queue();
        queueList.addQueueBill(testBill1);
        queueList.addQueueBill(testBill2);
    }

    @Test
    void testGetQueueBill() {
        assertEquals(testBill2, queueList.getQueueBill(1));
    }

    @Test
    void testAddQueueBill() {
        queueList.addQueueBill(testBill1);
        assertEquals(testBill1, queueList.getQueueBill(0));
    }

    @Test
    void testRemoveQueueBill() {
        queueList.removeQueueBill();
        assertEquals(testBill2, queueList.getQueueBill(0));
    }

    @Test
    void testGetQueueIndex() {
        assertEquals(1, queueList.getQueueIndex(testBill2));
    }

    @Test
    void testRemoveBillAtIndex() {
        queueList.removeBillAtIndex(0);
        assertEquals(testBill2, queueList.getQueueBill(0));
    }

}
