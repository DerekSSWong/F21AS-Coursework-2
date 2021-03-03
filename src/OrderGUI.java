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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class OrderGUI extends JFrame {
    // The GUI components
    JLabel menuSortLabel, categoryFilterLabel, discountLabel, totalLabel, discountAmount, totalAmount;
    JButton idButton, nameButton, categoryButton, priceButton, addButton, deleteButton, acceptButton;
    JComboBox categoriesDropdown;
    JScrollPane menuScrollPane, orderScrollPane;
    JTable menuTable, orderTable;
    JTextArea dealsTextArea;

    // Constructor
    public OrderGUI() {
        // Set up title for window
        setTitle("Cafe");
        // Create container and add panels
        Container contentPane = getContentPane();

        // Set the size of the window
        setSize(500, 650);

        contentPane.add(createLeftPanel(), BorderLayout.WEST);
        contentPane.add(createRightPanel(), BorderLayout.EAST);
        pack();

    }

    private JPanel createLeftPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        // Create label for sort
        menuSortLabel = new JLabel("Sort by:");
        // Create button panel and buttons for each sort
        JPanel sortButtonPanel = new JPanel();
        idButton = new JButton("ID");
        nameButton = new JButton("Name");
        categoryButton = new JButton("Category");
        priceButton = new JButton("Price");
        // Create label for filter and add to the panel
        categoryFilterLabel = new JLabel("Choose a category to filter the menu:");
        sortButtonPanel.add(menuSortLabel);
        // Add the buttons to the panel
        sortButtonPanel.add(idButton);
        sortButtonPanel.add(nameButton);
        sortButtonPanel.add(categoryButton);
        sortButtonPanel.add(priceButton);
        // Create filter panel
        JPanel filterPanel = new JPanel();
        // Array of categories
        String categories[] = { "Hot drinks", "Cold drinks", "Main", "Other", "Snack" };
        // Create the dropdown
        categoriesDropdown = new JComboBox<String>(categories);
        // Add label and dropdown to the panel and add border
        filterPanel.add(categoryFilterLabel);
        filterPanel.add(categoriesDropdown);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Add all of the components to the main panel
        menuPanel.add(filterPanel);
        menuPanel.add(sortButtonPanel);
        // Creating the table and adding it to a scroll pane
        menuPanel.add(createMenuTable());
        menuScrollPane = new JScrollPane(menuTable);
        menuScrollPane.setPreferredSize(new Dimension(100, 300));
        menuPanel.add(menuScrollPane);
        // Create the add button and add it to the panel
        addButton = new JButton("Add item to order");
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(addButton);
        // Setting border and title
        menuPanel.setBorder(
                new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Menu")));
        return menuPanel;
    }

    private JTable createMenuTable() {
        // Set the column header names
        String[] columns = new String[] { "ID", "Name", "Category", "Price", "Description" };
        // Create table with data
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        tableModel.setColumnIdentifiers(columns);
        menuTable = new JTable(tableModel);
        menuTable.setModel(tableModel);
        Object rowData[] = new Object[5];
        // Adding the rows
        tableModel.addRow(rowData);
        menuTable.setSize(new Dimension(200, 400));
        return menuTable;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(createDealsPanel());
        rightPanel.add(createOrderPanel());
        return rightPanel;
    }

    private JPanel createOrderPanel() {
        // Creating overall panel and setting layout
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        // Creating the table and adding it to a scroll pane
        orderPanel.add(createOrderTable());
        orderScrollPane = new JScrollPane(orderTable);
        orderScrollPane.setPreferredSize(new Dimension(100, 300));
        orderPanel.add(orderScrollPane);
        // Creating discount and label and adding to order panel
        JPanel discountPanel = new JPanel();
        discountAmount = new JLabel("£3.99");
        discountLabel = new JLabel("Discount");
        discountPanel.add(discountLabel);
        discountPanel.add(discountAmount);
        orderPanel.add(discountPanel);
        // Creating total and label and adding to order panel
        JPanel totalPanel = new JPanel();
        totalAmount = new JLabel("£23.99");
        totalLabel = new JLabel("Total price");
        totalPanel.add(totalLabel);
        totalPanel.add(totalAmount);
        orderPanel.add(totalPanel);
        // Creating the buttons and adding to panel
        JPanel orderButtonPanel = new JPanel();
        deleteButton = new JButton("Delete item");
        acceptButton = new JButton("Accept order");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderButtonPanel.add(acceptButton);
        orderButtonPanel.add(deleteButton);
        orderPanel.add(orderButtonPanel);
        // Setting border and title
        orderPanel.setBorder(
                new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Order")));
        return orderPanel;
    }

    private JPanel createDealsPanel() {
        // Creating initial panel
        JPanel dealsPanel = new JPanel();
        // Adding text area for deals and adding to panel
        dealsTextArea = new JTextArea("• Get a main, snack and cold drink for £5" + "\n\n" + "• 20% off hot drinks"
                + "\n\n" + "• Spend over £10 and get £2 off" + "\n\n" + "• Get two hot drinks and two snacks for £10");
        dealsTextArea.setEditable(false);
        dealsTextArea.setMinimumSize(new Dimension(400, 200));
        dealsPanel.add(dealsTextArea);
        // Setting border and title
        dealsPanel.setBorder(
                new CompoundBorder(new EmptyBorder(20, 20, 20, 20), BorderFactory.createTitledBorder("Deals")));
        return dealsPanel;
    }

    private JTable createOrderTable() {
        // Set the column header names
        String[] orderColumns = new String[] { "Item", "Price" };
        // Create table with data
        DefaultTableModel orderTableModel = new DefaultTableModel(orderColumns, 0);
        orderTableModel.setColumnIdentifiers(orderColumns);
        orderTable = new JTable(orderTableModel);
        orderTable.setModel(orderTableModel);
        Object rowData[] = new Object[2];
        // Adding the rows
        orderTableModel.addRow(rowData);
        orderTable.setSize(new Dimension(200, 400));
        return orderTable;
    }

}