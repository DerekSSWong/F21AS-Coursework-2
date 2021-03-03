//Imports
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing the AllItems class
 * @author Derek Wong
 *
 */
class AllItemsTest {
	
	private Item testItem1 = new Item("tst001", Item.ItemCat.COLDDRINK, "TestItem", "This is a test item", 12.99);
    private Item testItem2 = new Item("tst002", Item.ItemCat.HOTDRINK, "TestItem", "This is another test item", 2.99);
    private AllItems testMenu = new AllItems();
    
    @BeforeEach
    public void setUp() throws Exception {
    	testMenu.addItem(testItem1);
    	testMenu.addItem(testItem2);
    }
	
    //Testing finding an item in the list
	@Test
	void testgetItem() {
		assertEquals(testItem1 , testMenu.getItem("tst001"));
		assertEquals(null, testMenu.getItem("non-existant itemID"));
	}
	
	//Test adding unique and duplicate items into the list
	@Test
	void testAddItem() {
		Item testItem3 = new Item("tst003", Item.ItemCat.MAIN, "TestItem", "This is the third test item", 3.00);
		Item testItem2a = new Item("tst002", Item.ItemCat.HOTDRINK, "TestItem", "This is a duplicate of testItem2", 2.99);
		
		assertEquals(true , testMenu.addItem(testItem3));
		assertEquals(false, testMenu.addItem(testItem2a));
	}

}
