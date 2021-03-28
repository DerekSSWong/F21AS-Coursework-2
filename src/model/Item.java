package model;

/**
 * Class for storing information of items
 * 
 * @author Derek Wong
 * 
 */

public class Item implements Comparable<Item> {

	private String itemID;

	public static enum ItemCat {
		HOTDRINK, COLDDRINK, MAIN, OTHER, SNACK
	};

	private ItemCat itemCat;
	private String itemName;
	private String itemDesc;
	private double itemPrice;

	/**
	 * Constructor for the class
	 * 
	 * @param itemID    The ID of the item, in the form of itemCat + integer (e.g.
	 *                  HOTDRINK3)
	 * @param itemCat   The category of the item, which are currently: HOTDRINK,
	 *                  COLDDRINK, MAIN, OTHER
	 * @param itemName  The name of the item
	 * @param itemDesc  A short description of the item
	 * @param itemPrice The price of the item, non-negative, two d.p.
	 */
	public Item(String itemName, String itemID, ItemCat itemCat, double itemPrice, String itemDesc) {

		this.itemName = itemName;
		this.itemID = itemID;
		this.itemCat = itemCat;
		this.itemPrice = itemPrice;
		this.itemDesc = itemDesc;
	}

	/**
	 * Updates the item ID to the String provided in the parameter
	 * 
	 * @param ID
	 */
	public void setItemID(String ID) {
		itemID = ID;
	}

	/**
	 * Returns the current ID of the item
	 * 
	 * @return String
	 */
	public String getItemID() {
		return itemID;
	}

	/**
	 * Updates the item category to the enum provided in the parameter
	 * 
	 * @param cat
	 */
	public void setItemCat(ItemCat cat) {
		itemCat = cat;
	}

	/**
	 * Returns the current category of the item
	 * 
	 * @return ItemCat
	 */
	public ItemCat getItemCat() {
		return itemCat;
	}

	/**
	 * Updates the item name to the String provided in the parameter
	 * 
	 * @param name
	 */
	public void setItemName(String name) {
		itemName = name;
	}

	/**
	 * Returns the current name of the item
	 * 
	 * @return String
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Updates the item description to the String provided in the parameter
	 * 
	 * @param desc
	 */
	public void setItemDesc(String desc) {
		this.itemDesc = desc;
	}

	/**
	 * Returns the current description of the item
	 * 
	 * @return String
	 */
	public String getItemDesc() {
		return itemDesc;
	}

	/**
	 * Updates the item price to the double provided in the parameter
	 * 
	 * @param desc
	 */
	public void setItemPrice(double price) {
		itemPrice = price;
	}

	/**
	 * Returns the current price of the item
	 * 
	 * @return double
	 */
	public double getItemPrice() {
		return itemPrice;
	}

	/**
	 * Comparator to sort Item by ID
	 */
	@Override
	public int compareTo(Item i) {
		return this.getItemID().compareTo(i.getItemID());
	}

}
