/**
 * GUI for main application
 * 
 * @author Rose Ulldemolins
 * 
 */

//Importing GUI classes
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowFilter;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class QueueGUI extends JFrame {
    private Manager manager;

    // The GUI components
    JLabel queueLabel;
    JTable queueTable;
    JScrollPane queueScrollPane;

    // Constructor
    public QueueGUI(Manager manager) {
        this.manager = manager;
        // Setting title for window
        setTitle("Queue");
        // Creating the container
        Container contentPane = getContentPane();
        // Setting the size of the window
        setSize(500, 500);
        // Adding the top panel
        contentPane.add(createNorthPanel(), BorderLayout.NORTH);
        pack();
    }

    private JPanel createNorthPanel() {
        JPanel queuePanel = new JPanel();
        // Creating the label for the list
        queueLabel = new JLabel("Here's everything currently in the queue:");
        // Array of headings for the table
        String[] columns = new String[] { "Customer ID", "Number of items" };
        // Creating a table
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        tableModel.setColumnIdentifiers(columns);
        queueTable = new JTable(tableModel);
        queueTable.setModel(tableModel);
        Object rowData[] = new Object[2];
        // Mapping through the queue to get all of the orders
        List<Bill> queueList = manager.getQueue().getQueueList();
        for (Bill bill : queueList) {
            rowData[0] = bill.getCustomerID();
            rowData[1] = bill.getOrderList().size();
            // Adding the rows to the table
            tableModel.addRow(rowData);
        }
        // Creating the table and adding it to a scroll pane
        queuePanel.add(queueTable);
        queueScrollPane = new JScrollPane(queueTable);
        queueScrollPane.setPreferredSize(new Dimension(100, 300));
        queuePanel.add(queueScrollPane);
        // Returning the panel
        return queuePanel;
    }

}