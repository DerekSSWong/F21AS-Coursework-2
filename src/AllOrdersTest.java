import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AllOrdersTest {

    private Order testOrder1;
    private Order testOrder2;
    private AllOrders testAllOrders;

    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("Latte", "HTDK1", Item.ItemCat.HOTDRINK, 2.25, "Description");
        Item testItem2 = new Item("Muesli Bircher", "CLDK14", Item.ItemCat.COLDDRINK, 4.45, "Description");
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 2, testItem2);
        testAllOrders = new AllOrders();
        testAllOrders.addOrder(testOrder1);
        testAllOrders.addOrder(testOrder2);

    }

    @Test
    void testfindByID(){
    	ArrayList<Order> searchResult = testAllOrders.findByID(1);
    	assertEquals(searchResult.get(0).getCustomerID(), 1);
    	assertEquals(searchResult.get(0).getItem().getItemName(), "Latte");
    }

}
