package model;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import views.QueueDisplay;
import views.QueueGUI;
import controllers.QueueController;



public class CafeDemo {
    private Manager manager;
    private QueueGUI gui;

    public CafeDemo() {
        manager = new Manager();
        

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
        
        
        
        
        // model maintains the queue and broadcasts changes
        Queue model = new Queue();
        model.addQueueBill(testBill);
        model.addQueueBill(testBill);
        model.addQueueBill(testBill);

        
        
        		
		
		

		// This view displays the GUI, it contains the QueueDisplay that
        // updates when the queue is changed, and a Main QueueGUI which the
        // QueueDisplay is attached to. 
        QueueDisplay view = new QueueDisplay(model);
		gui = new QueueGUI();
		gui.addNorthPanel(view);
	    gui.setVisible(true);
		
			
		// this controller is yet to be implemented 
		// it needs to know about the view and the model
		QueueController controller = new QueueController(view, model);

		
	}
     
    public static void main(String arg[]) {
        CafeDemo cafeDemo = new CafeDemo();

        cafeDemo.showGUI();
    }
}