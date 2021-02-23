
public class Item {
	
	private String itemID;
	private enum ItemCat {HOTDRINK, COLDDRINK, MAIN, OTHER};
	private ItemCat itemCat;
	private String itemName;
	private String itemDesc;
	private double itemPrice;
	
	public Item(String itemID, String itemCat, String itemName, String itemDesc, double itemPrice) {
		
		this.itemID = itemID;
		this.itemCat = ItemCat.valueOf(itemCat);
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
	}
	
	public void setItemID(String ID) { this.itemID = ID;}
	
	public String getItemID() { return this.itemID; }

	public ItemCat getItemCat() { return this.itemCat; }
	
	public String getItemName() { return this.itemName; }
	
	public String getItemDesc() { return this.itemDesc; }
	
	public double getItemPrice() { return this.itemPrice; }
	
}


