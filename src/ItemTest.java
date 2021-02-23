import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemTest {
	
	@Test
	void testItem() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemID() {
		Item testItem = new Item("tst001", "COLDDRINK", "TestItem", "This is a test item", 12.99);
		assertEquals("tst001", testItem.getItemID());
	}

	@Test
	void testGetItemCat() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemName() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemDesc() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemPrice() {
		fail("Not yet implemented");
	}

}
