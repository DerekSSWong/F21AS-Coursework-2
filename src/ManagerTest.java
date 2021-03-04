import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class ManagerTest {
	Item testItem = new Item("Latte", "HTDK1", Item.ItemCat.HOTDRINK, 2.25, "Description");
	Item testItem2 = new Item("Muesli Bircher", "CLDK14", Item.ItemCat.COLDDRINK, 4.45, "Description");
	Manager testManager = new Manager();
	Manager testManager2 = new Manager();

	// Quick test to print out all the item names in the text file
	@Test
	void testReadMenuFile() {
		testManager.readMenuFile("Menu.csv");
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

	@Test
	void testProcessLine() {
		testManager2.processMenuLine("Latte,HTDK1,HOTDRINK,2.25,DESCRIPTION");
		assertEquals(testManager2.getMenu().getItemList().contains(testItem), true);
	}

}
