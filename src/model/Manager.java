package model;

import java.io.BufferedReader;
//Imports for classes
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Manager {

	static Manager manager = new Manager();
	private AllItems menu = new AllItems();
	private AllOrders orders = new AllOrders();
	private AllBills allBills = new AllBills();
	private String report;

	// As it's a singleton
	private Manager() {
	}

	public static Manager getInstance() {
		return manager;
	}

	// Reads a file given a filename
	public void readFile(String filename) {
		try {
			// Tries to find the file
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filename)));
			// Whilst there are more lines
			String inputLine = null;
			try {
				inputLine = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (inputLine != null) {
				if (inputLine.length() != 0) {
					// Calls a different method depending on the file
					switch (filename) {
					case "Menu.csv":
						processMenuLine(inputLine);
						break;
					case "ExistingOrders.csv":
						processOrderLine(inputLine);
						break;
					default:
						throw new FileNotFoundException("File could not be found");
					}
 
				}
				try {
					inputLine = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Exception if the file can't be found and closes
		} catch (FileNotFoundException fnf) {
			System.out.println(fnf);
			System.exit(0);
		}
	}

	/**
	 * Prints out the final report, once the queue is empty and the coffee shop
	 * closes.
	 */
	public void writeFile() {
		// Starts of the report
		report = "The total income for all orders: " + allBills.getNetIncome() + "\n\n";
		TreeSet<Item> list = menu.getItemList();
		// Adds the items to the report and amount of times ordered
		report += String.format("%-50s", "All the items in the menu ");
		report += String.format("%-25s", "The number of times item was ordered\n\n");
		// Loops through the items in the list
		for (Item it : list) {
			// This adds each item from the menu
			report += String.format("%-50s", it.getItemName());
			// This adds how many times a item was ordered
			report += String.format("%10s", orders.frequencyOfItem(it));
			report += "\n";
		}

		try {
			// Trying to read the file, if so writes the report and closes it
			FileWriter reportFile = new FileWriter("Report.csv");
			reportFile.write(report);
			reportFile.close();
		}
		// Displays an error if the file isn't in the folder
		catch (FileNotFoundException fnf) {
			System.out.println("Report.csv" + " not found ");
			System.exit(0);
		}
		// stack trace
		catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Attempts to create an Item instance from data provided as input
	 * 
	 * @param line
	 */
	public void processMenuLine(String line) {
		try {
			// Splits up the columns
			String parts[] = line.split(",");
			String itemName = parts[0].trim();
			String itemID = parts[1].trim();
			// Checks if the category is a part of ItemCat
			String catString = parts[2].trim();
			Item.ItemCat itemCat;
			if (checkEnum(catString) == true) {
				// If it is then converts it to an enum
				itemCat = Item.ItemCat.valueOf(catString);
			} else {
				// Throws exception if not
				throw new ClassCastException();
			}
			// Trims and converts the price to a double
			double itemPrice = Double.parseDouble(parts[3].trim());
			// Prepares the item description
			String itemDesc = parts[4].trim();
			// Makes a new item from the parts
			Item newItem = new Item(itemName, itemID, itemCat, itemPrice, itemDesc);
			// Adds it to the menu
			menu.addItem(newItem);

		} catch (NumberFormatException nfe) {
			// Catches if a number is not given
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.out.println(error);
		} catch (ClassCastException cce) {
			// Catches if the category isn't an enum
			String error = "Trouble reading item category in '" + line + "'  - " + cce.getMessage();
			System.out.println(error);
		}

	}

	/**
	 * Attempts to create an Order instance from data provided as input
	 * 
	 * @param line
	 */
	public void processOrderLine(String line) {
		try {
			// Splits up and prepares the columns
			String parts[] = line.split(",");
			String timeString = parts[0].trim();
			LocalDateTime time = LocalDateTime.parse(timeString);
			int cusID = Integer.parseInt(parts[1].trim());
			String itemID = parts[2].trim();
			Item item = menu.getItem(itemID);
			double price = Double.parseDouble(parts[3].trim());
			// If the item is found create and add an order
			if (item != null) {
				Order newOrder = new Order(time, cusID, item);
				orders.addOrder(newOrder);
			} else {
				// Throw exception if cannot be found
				throw new ClassCastException();
			}
		} catch (NumberFormatException nfe) {
			// Catch if a number isn't entered
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.out.println(error);
		} catch (DateTimeParseException dtpe) {
			// Catch if a valid date/time isn't entered
			String error = "Error parsing order time in '" + line + "'  - " + dtpe.getMessage();
			System.out.println(error);
		} catch (ClassCastException cce) {
			// Catch the cast exception thrown
			String error = "Error processing item in '" + line + "'  - " + cce.getMessage();
			System.out.println(error);
		}

	}

	// Converts the orders to bills
	public void toBills() {
		int cusIndex = 1;
		ArrayList<Order> orderList = orders.findByID(cusIndex);
		// Whilst there are orders in the list
		while (orderList.size() > 0) {
			Bill bill = new Bill(cusIndex);
			// Loop through them
			for (Order o : orderList) {
				// Add each to the bill
				bill.addOrder(o);
			}
			// Add the bill to all bills
			allBills.addBill(bill);
			cusIndex++;
			orderList = orders.findByID(cusIndex);
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

	public AllOrders getOrders() {
		return orders;
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
