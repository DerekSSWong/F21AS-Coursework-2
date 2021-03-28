package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import model.*;
import views.QueueDisplay;
import views.QueueGUI;

import org.junit.jupiter.api.Test;

public class QueueDisplayTest {

	@Test
	void test() {
		
		
		LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
        Item testItem1 = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
        Item testItem2 = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 2.99, "This is another test item");
        Order testOrder1 = new Order(testDateTime, 1, testItem1);
        Order testOrder2 = new Order(testDateTime, 2, testItem2);
        Order testOrder3  = new Order(testDateTime, 2, testItem1);
        Order testOrder4 = new Order(testDateTime, 3, testItem1);
        Order testOrder5 = new Order(testDateTime, 3, testItem2);
        Order testOrder6 = new Order(testDateTime, 3, testItem2);
        
        int speed = 2000;
        
        Bill testBill1 = new Bill(1);
        testBill1.addOrder(testOrder1);
        
        Bill testBill2 = new Bill(2);
        testBill2.addOrder(testOrder2);
       // testBill2.addOrder(testOrder3);
        
       Bill testBill3 = new Bill(3);
       // testBill3.addOrder(testOrder4);
       // testBill3.addOrder(testOrder5);
       // testBill3.addOrder(testOrder6);
		
		
        Queue queue = new Queue();
        queue.addQueueBill(testBill1);
        queue.addQueueBill(testBill2);
        queue.addQueueBill(testBill3);
		
        System.out.print(queue.getQueueList());
        
        QueueDisplay queueView = new QueueDisplay(queue);
        
        QueueGUI gui = new QueueGUI();
        gui.setSize(700, 500);
        gui.addNorthPanel(queueView);
        //gui.addCenterPanel(staffView1, staffView2, staffView3);
        gui.setVisible(true);
        
        try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Firing");
        queue.removeQueueBill();  
        queueView.update();
        
        try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Firing");
        queue.removeQueueBill();  
        queueView.update();
        
        try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println("Firing");
        queue.addQueueBill(testBill1); 
        queueView.update();
        
        
        
        try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
