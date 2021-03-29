package tests;
import model.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Iterator;
import java.util.TreeSet;


import org.junit.jupiter.api.Test;

class ManagerTest {
	LocalDateTime testDateTime = LocalDateTime.of(20, Month.JULY, 19, 20, 30, 40);
	Item testItem = new Item("Latte", "HTDK1", Item.ItemCat.HOTDRINK, 2.25, "Description");
	Item testItem2 = new Item("Muesli Bircher", "CLDK14", Item.ItemCat.COLDDRINK, 4.45, "Description");
	Bill testBill = new Bill(1);
	Order testOrder1 = new Order(testDateTime, 1, testItem);
	Order testOrder2 = new Order(testDateTime, 1, testItem2);
	Manager testManager = new Manager();
	Manager testManager2 = new Manager();

	// Quick test to print out all the item names in the text file
	@Test
	void testReadMenuFile() {
		testManager.readFile("Menu.csv");
		testManager.getMenu().addItem(testItem);
		TreeSet<Item> itemList = testManager.getMenu().getItemList();
		Iterator<Item> i = itemList.iterator();
		while (i.hasNext()) {
			String itemName = i.next().getItemName();
			System.out.println(itemName);
		}
		assertEquals(testManager.getMenu().getItemList().contains(testItem), true);
		assertEquals(testManager.getMenu().getItemList().contains(testItem2), true);
	}

	// Test to check the process line method works in isolation

	@Test
	void testProcessLine() {
		testManager2.processMenuLine("Latte,HTDK1,HOTDRINK,2.25,DESCRIPTION");
		assertEquals(testManager2.getMenu().getItemList().contains(testItem), true);
	}

	@Test
	// Test to see if a bill can be added successfully
	void testAddBill() {
		testBill.addOrder(testOrder1);
		testManager.addBill(testBill);
		assertEquals(testManager.getAllBills().findBill(1), testBill);
	}

	@Test
	// Test to see if all of the menu items are returned
	void testGetMenu() {
		testManager.getMenu().addItem(testItem);
		assertEquals(testManager.getMenu().getItem("HTDK1"), testItem);
	}

	
	@Test
	// Test to see if final report generates
	void testWriteFile() {
		 	Manager manager = new Manager();
	        manager.readFile("Menu.csv");
	        manager.readFile("ExistingOrders.csv");
	        manager.addOrder(testOrder1);
	        manager.addOrder(testOrder2);
	        manager.writeFile(); 
		
		//testManager.getMenu().addItem(testItem);
		//assertEquals(testManager.getMenu().getItem("HTDK1"), testItem);
	}
	
}
