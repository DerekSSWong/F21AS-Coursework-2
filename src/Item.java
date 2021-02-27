
/**
 * Class for storing information of items
 * @author Derek Wong
 * 
 */

public class Item {
	
	private String itemID;
	public static enum ItemCat {HOTDRINK, COLDDRINK, MAIN, OTHER};
	private ItemCat itemCat;
	private String itemName;
	private String itemDesc;
	private double itemPrice;
	
	/**
	 * Constructor for the class
	 * @param itemID The ID of the item, in the form of itemCat + integer (e.g. HOTDRINK3)
	 * @param itemCat The category of the item, which are currently: HOTDRINK, COLDDRINK, MAIN, OTHER
	 * @param itemName The name of the item
	 * @param itemDesc A short description of the item
	 * @param itemPrice The price of the item, non-negative, two d.p.
	 */
	public Item(String itemID, String itemCat, String itemName, String itemDesc, double itemPrice) {
		
		this.itemID = itemID;
		this.itemCat = ItemCat.valueOf(itemCat);
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
	}
	
	/**
	 * Changes the ID of an Item instance to the input parameter
	 * @param ID 
	 */
	public void setItemID(String ID) { itemID = ID; }
	
	/**
	 * Returns the current ID of the Item instance
	 * @return
	 */
	public String getItemID() { return itemID; }
	
	/**
	 * Changes the category of an Item instance to the input parameter
	 * @param cat
	 */
	public void setItemCat(String cat) { itemCat = ItemCat.valueOf(cat); }
	
	/**
	 * Returns the current category of the Item instance
	 * @return
	 */
	public ItemCat getItemCat() { return itemCat; }
	
	/**
	 * Changes the name of an Item instance to the input parameter
	 * @param name 
	 */
	public void setItemName(String name){ itemName = name; }
	
	/**
	 * Returns the current name of the Item instance
	 * @return
	 */
	public String getItemName() { return itemName; }
	
	/**
	 * Changes the description of an Item instance to the input parameter
	 * @param desc 
	 */
	public void setItemDesc(String desc) { this.itemDesc = desc; }
	
	/**
	 * Returns the current description of the Item instance
	 * @return
	 */
	public String getItemDesc() { return itemDesc; }
	
	/**
	 * Changes the price of an Item instance to the input parameter
	 * @param desc 
	 */
	public void setItemPrice(double price) { itemPrice = price; }
	
	/**
	 * Returns the current price of the Item instance
	 * @return
	 */
	public double getItemPrice() { return itemPrice; }
	
}


