
//Imports
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the AllItems class
 * 
 * @author Derek Wong
 *
 */
class AllItemsTest {

	private Item testItem1 = new Item("TestItem", "tst001", Item.ItemCat.COLDDRINK, 12.99, "This is a test item");
	private Item testItem2 = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 12.99, "This is another test item");
	private AllItems testMenu = new AllItems();

	@BeforeEach
	public void setUp() throws Exception {
		testMenu.addItem(testItem1);
		testMenu.addItem(testItem2);
	}

	// Testing finding an item in the list
	@Test
	void testgetItem() {
		assertEquals(testItem1, testMenu.getItem("tst001"));
		assertEquals(testItem2, testMenu.getItem("tst002"));
		assertEquals(null, testMenu.getItem("non-existant itemID"));
	}

	// Test adding unique and duplicate items into the list
	@Test
	void testAddItem() {
		Item testItem3 = new Item("TestItem", "tst003", Item.ItemCat.MAIN, 3.00, "This is the third test item");
		Item testItem2a = new Item("TestItem", "tst002", Item.ItemCat.HOTDRINK, 2.99,
				"This is a duplicate of testItem2");

		assertEquals(true, testMenu.addItem(testItem3));
		assertEquals(false, testMenu.addItem(testItem2a));
	}

}
