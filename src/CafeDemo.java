import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CafeDemo {
    private Manager manager;
    private QueueGUI gui;

    public CafeDemo() {
        manager = new Manager();
        // manager.readFile("Menu.csv");
        // manager.readFile("ExistingOrders.csv");
    }

    public void showGUI() {

        LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
        Item testItem2 = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is another test item");
        Order testOrder1 = new Order(testDateTime, 1, testItem1);
        Order testOrder2 = new Order(testDateTime, 1, testItem2);
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(testOrder1);
        orderList.add(testOrder2);
        Bill testBill = new Bill(1);
        testBill.addOrder(testOrder1);
        testBill.addOrder(testOrder2);
        System.out.println(testBill);
        Queue queue = new Queue();
        queue.addQueueBill(testBill);
        queue.addQueueBill(testBill);
        queue.addQueueBill(testBill);

        // Create Queue display
        QueueDisplay qDisplay = new QueueDisplay(queue);
        gui = new QueueGUI();
        gui.addNorthPanel(qDisplay);
        gui.setVisible(true);
    }

    public static void main(String arg[]) {
        CafeDemo cafeDemo = new CafeDemo();

        cafeDemo.showGUI();
    }
}