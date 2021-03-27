
/**
 * The display (or panel) for the queue part of the GUI
 * 
 * @author Rose Ulldemolins
 * 
 */

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// This is an observer of the queue so that when it changes te GUI updates
public class QueueDisplay extends JPanel implements Observer {
    private Queue queue; // The queue which contains the current orders
    // The GUI components
    JLabel queueLabel;
    JTable queueTable;
    JScrollPane queueScrollPane;
    DefaultTableModel tableModel;

    // Setting up the GUI
    public QueueDisplay(Queue queue) {
        this.queue = queue;
        // Creating the label for the list
        queueLabel = new JLabel("Here's everything currently in the queue:");
        // Array of headings for the table
        String[] columns = new String[] { "Customer ID", "Number of items", "Total" };
        // Creating a table
        tableModel = new DefaultTableModel(columns, 0);
        tableModel.setColumnIdentifiers(columns);
        queueTable = new JTable(tableModel);
        queueTable.setModel(tableModel);
        Object rowData[] = new Object[3];
        // Mapping through the queue to get all of the orders
        List<Bill> queueList = queue.getQueueList();
        for (Bill bill : queueList) {
            rowData[0] = bill.getCustomerID();
            rowData[1] = bill.getOrderList().size();
            rowData[2] = bill.getDiscountedPrice();
            // Adding the rows to the table
            tableModel.addRow(rowData);
        }
        // Creating the table and adding it to a scroll pane
        this.add(queueTable);
        queueScrollPane = new JScrollPane(queueTable);
        queueScrollPane.setPreferredSize(new Dimension(300, 100));
        this.add(queueScrollPane);
        update();
    }

    // Tells the Observer to update itself (to change the data in the table)
    public void update() {
        tableModel.fireTableDataChanged();
    }
}
