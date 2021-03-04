
//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Manager {

	private AllItems menu = new AllItems();
	private AllOrders orders = new AllOrders();
	private AllBills allBills = new AllBills();

	public void readFile(String filename) {

		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {
					// Could probably include processOrderLine here with Switch
					switch (filename) {
						case "Menu.csv":
							processMenuLine(inputLine);
							break;
						case "ExistingOrders.csv":
							processOrderLine(inputLine);
							break;
					}
					
				}
			}
			scanner.close();
		} catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
	}

	/**
	 * Attempts to create an Item instance from data provided as input
	 * 
	 * @param line
	 */
	public void processMenuLine(String line) {

		try {
			String parts[] = line.split(",");

			String itemName = parts[0].trim();
			String itemID = parts[1].trim();

			// Checks if the category is a part of ItemCat
			// Throws exception if not
			String catString = parts[2].trim();
			Item.ItemCat itemCat;
			if (checkEnum(catString) == true) {
				itemCat = Item.ItemCat.valueOf(catString);
			} else {
				throw new ClassCastException();
			}

			double itemPrice = Double.parseDouble(parts[3].trim());
			String itemDesc = parts[4].trim();

			Item newItem = new Item(itemName, itemID, itemCat, itemPrice, itemDesc);
			menu.addItem(newItem);

		} catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.out.println(error);
		} // catch
		catch (ClassCastException cce) {
			String error = "Trouble reading item category in '" + line + "'  - " + cce.getMessage();
			System.out.println(error);
		} // catch

	}
	
	public void processOrderLine(String line) {
		
		try {
			String parts[] = line.split(",");
			
			String timeString = parts[0].trim();
			LocalDateTime time = LocalDateTime.parse(timeString);
			
			int cusID = Integer.parseInt(parts[1].trim());
			
			String itemID = parts[2].trim();
			Item item = menu.getItem(itemID);
			
			double price = Double.parseDouble(parts[3].trim());
			
			if (item != null) {
			Order newOrder = new Order(time, cusID, item);
			orders.addOrder(newOrder);
			}
		} 
		catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.out.println(error);
		}
		catch (DateTimeParseException dtpe) {
			String error = "Error parsing order time in '" + line + "'  - " + dtpe.getMessage();
			System.out.println(error);
		}
	}

	/**
	 * Method which compares the input string with the list of valid item categories
	 * 
	 * @param strEnum
	 * @return boolean
	 */
	public boolean checkEnum(String strEnum) {

		boolean enumFound = false;
		for (Item.ItemCat cat : Item.ItemCat.values()) {
			if (cat.toString().equals(strEnum)) {
				enumFound = true;
				break;
			}
		}
		return enumFound;
	}

	/**
	 * Returns the AllItems class
	 * 
	 * @return AllItems
	 */
	public AllItems getMenu() {
		return menu;
	}
	
	public void addOrder(Order order) {
		orders.addOrder(order);
	}
	/**
	 * Adds a bill to the list of all bills
	 * 
	 * @param bill
	 */
	public void addBill(Bill bill) {
		allBills.addBill(bill);
	}

	/**
	 * Returns all of the bills
	 * 
	 * @return AllItems
	 */
	public AllBills getAllBills() {
		return allBills;
	}

}
