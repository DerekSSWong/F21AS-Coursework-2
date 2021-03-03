
//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Manager {

	private AllItems menu = new AllItems();

	public void readMenuFile(String filename) {

		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {
					// Could probably include processOrderLine here with Switch
					processMenuLine(inputLine);
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
	private void processMenuLine(String line) {

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
}
