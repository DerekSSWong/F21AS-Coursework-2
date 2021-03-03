import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

class ManagerTest {
	
	Manager testManager = new Manager();
	
	//Quick test to print out all the item names in the text file
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

}
