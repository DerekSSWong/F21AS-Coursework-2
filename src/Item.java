
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
	
	//Set and get for itemID
	public void setItemID(String itemID) { this.itemID = itemID;}
	
	public String getItemID() { return this.itemID; }
	
	//Set and get for itemCat
	public void setItemCat(String itemCat) { this.itemCat = ItemCat.valueOf(itemCat); }
	
	public ItemCat getItemCat() { return this.itemCat; }
	
	//Set and get for itemName
	public void setItemName(String itemName){ this.itemName = itemName; }
	
	public String getItemName() { return this.itemName; }
	
	
	//Set and get for itemDesc
	public void setItemDesc(String itemDesc) { this.itemDesc = itemDesc; }
	
	public String getItemDesc() { return this.itemDesc; }
	
	//Set and get for itemPrice
	public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }
	
	public double getItemPrice() { return this.itemPrice; }
	
}


