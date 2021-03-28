
package views;

import interfaces.Observer;
import java.awt.*;
//import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Queue;

/**
 * using MVC pattern
 * 
 * @author Andrew Dalley , Rose Ulldemolins
 *
 */

public class QueueDisplay extends JPanel implements Observer {
	private Queue queue; // The queue which contains the current orders
	// The GUI components
	JLabel queueLabel;
	JTable queueTable;
	JScrollPane queueScrollPane;
	DefaultTableModel tableModel;
	int counter= 0; 

	// Setting up the GUI
	public QueueDisplay(Queue queue) {
		this.queue = queue;
		queue.registerObserver(this);
		// Creating the label for the list
		queueLabel = new JLabel("Here's everything currently in the queue:");
		// Array of headings for the table
		String[] columns = new String[] { "Customer ID", "Number of items", "Total" };
		// Creating a table
		tableModel = new DefaultTableModel(columns, 0);
		tableModel.setColumnIdentifiers(columns);
		queueTable = new JTable(tableModel);
		queueTable.setModel(tableModel);

		// Creating the table and adding it to a scroll pane
		this.add(queueTable);
		queueScrollPane = new JScrollPane(queueTable);
		queueScrollPane.setPreferredSize(new Dimension(300, 100));
		this.add(queueScrollPane);

		update();

	}

	// Tells the Observer to update itself (to change the data in the table)

	public void update() {
		
		
		
		tableModel.setRowCount(0);
		System.out.println("Called update - queue");
		// tableModel.fireTableDataChanged();
		;

		Object rowData[] = new Object[3];
		
		ArrayList<ArrayList<String>>  table =queue.getTable();
		// Mapping through the queue to get all of the orders
		for (int i = 0; i < table.size(); i++) {
			for (int j = 0; j < table.get(i).size(); j++) {
				rowData[j] = table.get(i).get(j);
			}
				
			tableModel.addRow(rowData);
					
		}
			

		// repaint();

	}

}
