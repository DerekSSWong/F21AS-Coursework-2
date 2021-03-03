//Imports
import java.util.*; 

/**
 * Class for storing all items
 * 
 * @author Andrew Dalley, Derek Wong
 *
 */

public class AllItems {

  private TreeSet<Item> itemList;

  public AllItems() {
      itemList = new TreeSet<Item>(); 
  }  

  /**
   * Adds an item to the list if if its ID is unique, and returns true
   * Returns false otherwise
   * 
   * @param itm
   * @return boolean
   */
  public boolean addItem(Item itm) {
	  boolean result = false;
	  Item dupe = this.getItem(itm.getItemID());
	  
	  if (dupe == null) {
		  itemList.add(itm);
		  result = true;
	  }//if
	  
	  return result;
  }//addItem

  /**
   * Attempts to find an item in the list by its ID, and returns it
   * Returns null otherwise
   * 
   * @param itmID
   * @return Item
   */
  public Item getItem(String itmID) {
	  
	  Iterator<Item> i = itemList.iterator();
	  Item searchResult = null;
	  while (i.hasNext()) {
		  Item currentItem = i.next();
		  if (currentItem.getItemID().equals(itmID)) {
			  searchResult = currentItem;
		  }//if
	  }//while
	  return searchResult;
  }//getItem
  
  /**
   * returns the item list
   * @return TreeSet<Item>
   */
  public TreeSet<Item> getItemList() {
	  return itemList;
  }

}
