
package views;

import interfaces.Observer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

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
	JTable queueTable = new JTable();
	JScrollPane queueScrollPane;
	DefaultTableModel tableModel = new DefaultTableModel(3, 0);
	Vector<String>columns = new Vector<String>(3);

	// Setting up the GUI
	public QueueDisplay(Queue queue) {
		this.queue = queue;
		queue.registerObserver(this);
		// Creating the label for the list
		queueLabel = new JLabel("Here's everything currently in the queue:");
		// Array of headings for the table
		columns.add(0,"Customer ID");
		columns.add(1,"Number of items");
		columns.add(2,"Total" );
		// Creating a table
		tableModel.setColumnIdentifiers(columns);
		queueTable.setModel(tableModel);

		// Creating the table and adding it to a scroll pane
		this.add(queueTable);
		queueScrollPane = new JScrollPane(queueTable);
		queueScrollPane.setPreferredSize(new Dimension(300, 100));
		this.add(queueScrollPane);
		update();

	}

	// Tells the Observer to update itself (to change the data in the table)

	public synchronized void update() {
		
		//Thread.currentThread().setName("updateThread");
		System.out.println("Thread "+  Thread.currentThread().getName() + " is updating");
		
				
		Vector<Vector<String>> table = queue.getTable();
		//System.out.println("Table is" + queue.getTable());
		tableModel.setDataVector(table,columns);
		
		/*
		//Old way
		  System.out.println("updated");
		tableModel.setRowCount(0);
		queueTable.setModel(tableModel);
		Object rowData[] = new Object[3];
		Vector<Vector<String>> table = queue.getTable();
		// Mapping through the queue to get all of the orders
		for (int i = 0; i < table.size(); i++) {
			for (int j = 0; j < table.get(i).size(); j++) {
				rowData[j] = table.get(i).get(j);
			}
			tableModel.addRow(rowData);
			queueTable.setModel(tableModel);

		} */
			
	}
			
}


