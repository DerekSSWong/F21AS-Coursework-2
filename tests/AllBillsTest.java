import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AllBillsTest {

    private Order testOrder1;
    private Order testOrder2;
    private Bill testBill;
    private AllBills testAllBills;

    @BeforeEach
    public void setUp() throws Exception {
        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JUNE, 19, 20, 30, 40);
        Item testItem1 = new Item("Latte", "HTDK1", Item.ItemCat.HOTDRINK, 2.25, "Description");
        Item testItem2 = new Item("Muesli Bircher", "CLDK14", Item.ItemCat.COLDDRINK, 4.45, "Description");
        testOrder1 = new Order(testDateTime, 1, testItem1);
        testOrder2 = new Order(testDateTime, 1, testItem2);
        testBill = new Bill(1);
        testBill.addOrder(testOrder1);
        testBill.addOrder(testOrder2);
        testAllBills = new AllBills();
        testAllBills.addBill(testBill);
    }

    @Test
    void testgetNetIncome() {
        assertEquals(6.25, testAllBills.getNetIncome());
    }

    @Test
    void testfindBill() {
        Bill bill = testAllBills.findBill(1);
        List<Order> billOrder = bill.getOrderList();
        assertEquals(billOrder.get(0).getItem().getItemName(), "Latte");
        assertEquals(billOrder.get(1).getItem().getItemName(), "Muesli Bircher");
    }

}
