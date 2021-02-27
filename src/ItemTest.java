import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {
	
	Item testItem = new Item("tst001", "COLDDRINK", "TestItem", "This is a test item", 12.99);
	
	//Set and get itemID
	@Test
	void testGetItemID() {
		assertEquals("tst001", testItem.getItemID());
	}
	
	@Test
	void testSetItemID() {
		testItem.setItemID("tst002");
		assertEquals("tst002", testItem.getItemID());
	}
	
	//Set and get itemCat
	@Test
	void testGetItemCat() {
		assertEquals("COLDDRINK", testItem.getItemCat().name());
	}
	
	@Test
	void testSetItemCat() {
		testItem.setItemCat("MAIN");
		assertEquals("MAIN", testItem.getItemCat().name());
	}
	
	//Set and get itemName
	@Test
	void testGetItemName() {
		assertEquals("TestItem", testItem.getItemName());
	}
	
	@Test
	void testSetItemName() {
		testItem.setItemName("TestItem2");
		assertEquals("TestItem2", testItem.getItemName());
	}
	
	//Set and get itemDesc
	@Test
	void testGetItemDesc() {
		assertEquals("This is a test item", testItem.getItemDesc());
	}
	
	@Test
	void testSetItemDesc() {
		testItem.setItemDesc("Changed desc");
		assertEquals("Changed desc", testItem.getItemDesc());
	}

	//Set and get itemPrice
	@Test
	void testGetItemPrice() {
		assertEquals(12.99, testItem.getItemPrice());
	}
	
	@Test
	void testSetItemPrice() {
		testItem.setItemPrice(49.99);
		assertEquals(49.99, testItem.getItemPrice());
	}

}
