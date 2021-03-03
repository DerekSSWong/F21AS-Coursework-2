import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class ManagerTest {
	Item testItem = new Item("Latte", "HTDK1", Item.ItemCat.HOTDRINK, 2.25, "Description");
	Manager testManager = new Manager();

	// Quick test to print out all the item names in the text file
	@Test
	void testReadMenuFile() {
		testManager.readMenuFile("Menu.csv");
		TreeSet<Item> itemList = testManager.getMenu().getItemList();
		Iterator<Item> i = itemList.iterator();
		while (i.hasNext()) {
			String itemName = i.next().getItemName();
			System.out.println(itemName);
		}

	}

	@Test
	void testProcessLine() {
		testManager.processMenuLine("Latte,HTDK1,HOTDRINK,2.25,DESCRIPTION");
		assertEquals(testManager.getMenu().getItem("HTDK1"), testItem.getItemID());
	}

}
