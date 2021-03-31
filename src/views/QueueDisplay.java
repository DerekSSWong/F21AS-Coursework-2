
package views;

import interfaces.Observer;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Queue;

/**
 * using MVC pattern
 * 
 * @author Andrew Dalley, Rose Ulldemolins
 *
 */

public class QueueDisplay extends JPanel implements Observer {
	private Queue queue; // The queue which contains the current orders
	// The GUI components
	JTable queueTable = new JTable();
	JScrollPane queueScrollPane;
	// Table Model and columns
	DefaultTableModel tableModel = new DefaultTableModel(3, 0);
	Vector<String> columns = new Vector<String>(3);

	// Setting up the GUI
	public QueueDisplay(Queue queue) {
		this.queue = queue;
		// Registering to be an observer of queue
		queue.registerObserver(this);
		// Array of headings for the table
		columns.add(0, "Customer ID");
		columns.add(1, "Number of items");
		columns.add(2, "Total");
		// Creating a table and adding it to the scroll pane
		tableModel.setColumnIdentifiers(columns);
		queueTable.setModel(tableModel);
		this.add(queueTable);
		queueScrollPane = new JScrollPane(queueTable);
		queueScrollPane.setPreferredSize(new Dimension(300, 150));
		this.add(queueScrollPane);
		update();

	}

	// Tells the Observer to update itself (to change the data in the table)
	public synchronized void update() {
		System.out.println("Thread " + Thread.currentThread().getName() + " is updating");
		Vector<Vector<String>> table = queue.getTable();
		tableModel.setDataVector(table, columns);
	}

}
