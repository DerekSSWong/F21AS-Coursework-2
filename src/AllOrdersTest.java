import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class AllOrdersTest {
	
    private Order testOrder1;
    private Order testOrder2;
    private AllOrders testAllOrders;
    
    @BeforeEach
    public void setUp() throws Exception {
    	LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("tst001", Item.ItemCat.COLDDRINK, "TestItem", "This is a test item", 12.99);
        Item testItem2 = new Item("tst002", Item.ItemCat.HOTDRINK, "TestItem", "This is another test item", 2.99);
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 2, testItem2);
        testAllOrders = new AllOrders();
        testAllOrders.addOrder(testOrder1);
        testAllOrders.addOrder(testOrder2);
        
    }
    
    @Test
    void testfindByID(){
    	assertEquals(testOrder2, testAllOrders.findByID(2));
    }
    
    
}
