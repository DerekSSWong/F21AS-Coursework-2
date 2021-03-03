//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Manager {
	
	AllItems menu = new AllItems();
	
	public void readMenuFile(String filename) {
		
		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {
					//Could probably include processOrderLine here with Switch
					processMenuLine(inputLine);
				}
			}
			scanner.close();
		}
		catch (FileNotFoundException fnf){
			 System.out.println( filename + " not found ");
			 System.exit(0);
		 }
	}
	
	private void processMenuLine(String line) {
		
		try {
			String parts[] = line.split(",");
			
			String itemName = parts[0].trim();
			String itemID = parts[1].trim();
			String catString = parts[2].trim();
			Item.ItemCat itemCat = Item.ItemCat.valueOf(catString);
			double itemPrice = Double.parseDouble(parts[3].trim());
			String itemDesc = parts[4].trim();
			
			Item newItem = new Item(itemID, itemCat, itemName, itemDesc, itemPrice);
			menu.addItem(newItem);
			
			
		}
		  catch (NumberFormatException nfe) {
				String error = "Number conversion error in '" + line + "'  - " 
				                  + nfe.getMessage();
				System.out.println(error);
			}

	}
	
	public AllItems getMenu() {
		return menu;
	}
}
