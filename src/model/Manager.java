package model;

//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Manager {

	private AllItems menu = new AllItems();
	private AllOrders orders = new AllOrders();
	private AllBills allBills = new AllBills();
	private String report;

	public void readFile(String filename) {

		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {
					// Could probably include processOrderLine here with Switch
					switch (filename) {
						case "../Menu.csv":
							processMenuLine(inputLine);
							break;
						case "../ExistingOrders.csv":
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
	 * Prints out the final report, once the queue is empty and the coffee shop
	 * closes.
	 */
	public void writeFile() {

		// System.out.println("The total income for all orders: " +
		// allBills.getNetIncome());
		report = "The total income for all orders: " + allBills.getNetIncome() + "\n\n";

		TreeSet<Item> list = menu.getItemList();
		report += String.format("%-50s", "All the items in the menu ");
		report += String.format("%-25s", "The number of times item was ordered\n\n");

		for (Item it : list) {
			// This adds each item from the menu
			report += String.format("%-50s", it.getItemName());
			// This adds how many times a item was ordered
			report += String.format("%10s", orders.frequencyOfItem(it));
			report += "\n";
		}

		try {
			FileWriter reportFile = new FileWriter("Report.csv");
			reportFile.write(report);
			reportFile.close();
			// Exit Program
			// System.exit(0);
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
				// System.out.println("Order from cus " + newOrder.getCustomerID() + " added");
				orders.addOrder(newOrder);
			} else {
				throw new ClassCastException();
			}
		} catch (NumberFormatException nfe) {
			String error = "Number conversion error in '" + line + "'  - " + nfe.getMessage();
			System.out.println(error);
		} catch (DateTimeParseException dtpe) {
			String error = "Error parsing order time in '" + line + "'  - " + dtpe.getMessage();
			System.out.println(error);
		} catch (ClassCastException cce) {
			String error = "Error processing item in '" + line + "'  - " + cce.getMessage();
			System.out.println(error);
		}

	}

	public void toBills() {
		int cusIndex = 1;
		ArrayList<Order> orderList = orders.findByID(cusIndex);
		while (orderList.size() > 0) {
			Bill bill = new Bill(cusIndex);
			for (Order o : orderList) {
				bill.addOrder(o);
			}
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
