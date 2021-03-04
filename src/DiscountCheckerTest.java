
//Imports for unit test libraries
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the discount checker class
 * 
 * @author Rose Ulldemolins
 * 
 */
public class DiscountCheckerTest {
    private Order testOrder1;
    private Order testOrder2;
    private Order testOrder3;
    private Order testOrder4;
    private Order testOrder5;
    private List<Order> orderList1, orderList2;;
    private DiscountChecker discountChecker1;
    private DiscountChecker discountChecker2;
    private DiscountChecker discountChecker3;

    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("TestItem1", "tst001", Item.ItemCat.COLDDRINK, 0.99, "This is a test item");
        Item testItem2 = new Item("TestItem2", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is another test item");
        Item testItem3 = new Item("TestItem3", "tst003", Item.ItemCat.HOTDRINK, 1.49, "This is another test item");
        Item testItem4 = new Item("TestItem4", "tst004", Item.ItemCat.SNACKS, 1.99, "This is another test item");
        Item testItem5 = new Item("TestItem5", "tst005", Item.ItemCat.MAIN, 3.99, "This is another test item");
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 1, testItem2);
        testOrder3 = new Order(testDateTime, 1, testItem3);
        testOrder4 = new Order(testDateTime, 1, testItem4);
        testOrder5 = new Order(testDateTime, 1, testItem5);
        orderList1 = new ArrayList<Order>();
        orderList1.add(testOrder1);
        orderList1.add(testOrder2);
        orderList1.add(testOrder1);
        orderList1.add(testOrder1);
        orderList1.add(testOrder4);
        orderList1.add(testOrder5);
        orderList2 = new ArrayList<Order>();
        orderList2.add(testOrder2);
        orderList2.add(testOrder3);
        orderList2.add(testOrder4);
        orderList2.add(testOrder3);
        orderList2.add(testOrder4);
        orderList2.add(testOrder4);
        discountChecker1 = new DiscountChecker(orderList1, 6.94);
        discountChecker2 = new DiscountChecker(orderList2, 16.94);
        discountChecker3 = new DiscountChecker(orderList2, 10.00);
    }

    // Testing the spend over £10 discount
    @Test
    void testSpendOver() {
        // Under £10
        assertEquals(0.0, discountChecker1.spendOver());
        // Over £10
        assertEquals(2.0, discountChecker2.spendOver());
        // On boundary
        assertEquals(2.0, discountChecker3.spendOver());
    }

    // Testing the meal deal discount
    @Test
    void testMealDeal() {
        // Multiple drinks
        assertEquals(3.97, discountChecker1.mealDeal());

    }

    // Testing the afternoon tea offer
    @Test
    void testAfterNoonTea() {
        // Multiple drinks
        assertEquals(2.46, discountChecker2.afternoonTea());

    }

    // Testing the category offer
    @Test
    void testCategoryDiscount() {
        // Multiple drinks
        assertEquals(1.19, discountChecker2.categoryOfTheDay());

    }
}
