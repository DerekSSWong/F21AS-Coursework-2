package model;
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
import java.util.TreeSet;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class OrderGUI extends JFrame implements ActionListener {
    private Manager manager;
    private Bill bill;

    // The GUI components
    JLabel menuSortLabel, categoryFilterLabel, discountLabel, totalLabel, discountAmount, totalAmount, totalBeforeLabel,
            totalBeforeAmount;
    JButton idButton, nameButton, categoryButton, priceButton, addButton, deleteButton, acceptButton;
    JComboBox categoriesDropdown;
    JScrollPane menuScrollPane, orderScrollPane;
    JTable menuTable, orderTable;
    JTextArea dealsTextArea;
    DefaultTableModel orderTableModel;
    DecimalFormat decimalFormat;

    // Constructor
    public OrderGUI(Manager manager) {
        this.manager = manager;
        this.bill = new Bill(10);
        this.decimalFormat = new DecimalFormat("#.00");
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
        idButton.addActionListener(this);
        nameButton = new JButton("Name");
        nameButton.addActionListener(this);
        categoryButton = new JButton("Category");
        categoryButton.addActionListener(this);
        priceButton = new JButton("Price");
        priceButton.addActionListener(this);
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
        String categories[] = { "HOTDRINK", "COLDDRINK", "MAIN", "OTHER", "SNACK" };
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
        addButton.addActionListener(this);
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
        // Mapping through all of the orders to create the rows
        TreeSet<Item> itemList = manager.getMenu().getItemList();
        for (Item item : itemList) {
            rowData[0] = item.getItemID();
            rowData[1] = item.getItemName();
            rowData[2] = item.getItemCat();
            rowData[3] = decimalFormat.format(item.getItemPrice());
            rowData[4] = item.getItemDesc();
            // Adding each row to the table
            tableModel.addRow(rowData);
            // Adding autosort so column names can be clicked to sort
            menuTable.setAutoCreateRowSorter(true);
            menuTable.getColumnModel().getColumn(4).setMinWidth(150);
        }
        menuTable.setSize(new Dimension(200, 400));
        // Adding an action listener on the categories dropdown so the table can be
        // filtered
        categoriesDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Creating the filter by using the value of the dropdown and index 3 of the
                // table
                RowFilter<DefaultTableModel, Object> filter = RowFilter
                        .regexFilter(categoriesDropdown.getSelectedItem().toString(), 2);

                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
                // Adding the filter to the sorter
                sorter.setRowFilter(filter);
                // Adding the sorter to the table
                menuTable.setRowSorter(sorter);

            }

        });

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
        // Creating total and label and adding to order panel
        JPanel totalBeforePanel = new JPanel();
        totalBeforeAmount = new JLabel("£0.00");
        totalBeforeLabel = new JLabel("Price before discounts: ");
        totalBeforePanel.add(totalBeforeLabel);
        totalBeforePanel.add(totalBeforeAmount);
        orderPanel.add(totalBeforePanel);
        // Creating discount and label and adding to order panel
        JPanel discountPanel = new JPanel();
        discountAmount = new JLabel("£0.00");
        discountLabel = new JLabel("Discount: ");
        discountPanel.add(discountLabel);
        discountPanel.add(discountAmount);
        orderPanel.add(discountPanel);
        // Creating total and label and adding to order panel
        JPanel totalPanel = new JPanel();
        totalAmount = new JLabel("£0.00");
        totalLabel = new JLabel("Total price: ");
        totalPanel.add(totalLabel);
        totalPanel.add(totalAmount);
        orderPanel.add(totalPanel);

        // Creating the buttons and adding to panel
        JPanel orderButtonPanel = new JPanel();
        deleteButton = new JButton("Delete item");
        deleteButton.addActionListener(this);
        acceptButton = new JButton("Accept order");
        acceptButton.addActionListener(this);
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
                + "\n\n" + "• Spend over £10 and get £2 off" + "\n\n" + "• Get two hot drinks and two snacks for £6");
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
        String[] orderColumns = new String[] { "Item", "Price", "ID" };
        // Create table with data
        orderTableModel = new DefaultTableModel(orderColumns, 0);
        orderTableModel.setColumnIdentifiers(orderColumns);
        orderTable = new JTable(orderTableModel);
        orderTable.setModel(orderTableModel);
        Object rowData[] = new Object[2];
        // Adding autosort so column names can be clicked to sort
        menuTable.setAutoCreateRowSorter(true);
        // Adding the rows
        orderTable.setSize(new Dimension(200, 400));
        for (Order order : bill.getOrderList()) {
            rowData[0] = order.getItem().getItemName();
            rowData[1] = decimalFormat.format(order.getPrice());
            // Adding each row to the table
            orderTableModel.addRow(rowData);
        }
        return orderTable;
    }

    // When a button is clicked this will determine which one and what to do
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == idButton) {
            menuTable.getRowSorter().toggleSortOrder(0);
        } else if (event.getSource() == nameButton) {
            menuTable.getRowSorter().toggleSortOrder(1);
        } else if (event.getSource() == categoryButton) {
            menuTable.getRowSorter().toggleSortOrder(2);
        } else if (event.getSource() == priceButton) {
            menuTable.getRowSorter().toggleSortOrder(3);
        } else if (event.getSource() == addButton) {
            addItem();
        } else if (event.getSource() == deleteButton) {
            removeItem();
        } else if (event.getSource() == acceptButton) {
            addBill();
        }
    }

    // Adding an event listening to know which row a user selects and then creating
    // the order using the item ID and adding it to the bill
    public void addItem() {
        try {

            String itemId = menuTable.getValueAt(menuTable.getSelectedRow(), 0).toString();
            menuTable.convertRowIndexToModel(menuTable.getSelectedRow());
            Item item = manager.getMenu().getItem(itemId);
            Order order = new Order(LocalDateTime.now(), 10, item);
            bill.addOrder(order);
            Object rowData[] = new Object[3];
            rowData[0] = order.getItem().getItemName();
            rowData[1] = order.getPrice();
            rowData[2] = order.getItem().getItemID();
            // Adding each row to the table
            orderTableModel.addRow(rowData);
            // Updating the total price
            updatePrices();

        } catch (

        IndexOutOfBoundsException error) {
            JOptionPane.showMessageDialog(null, "Please select something to add", "alert", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Adding an event listening to know which row a user selects and then creating
    // the order using the item ID and adding it to the bill
    public void removeItem() {

        orderTable.convertRowIndexToModel(orderTable.getSelectedRow());

        try {
            int index = orderTable.getSelectedRow();
            // Removing the order from the order list (need to reverse the index)
            bill.removeOrderByIndex(index);
            // Removing it from the table
            orderTableModel.removeRow(index);
            // Updating prices
            updatePrices();

        } catch (IndexOutOfBoundsException error) {
            JOptionPane.showMessageDialog(null, "Oops - nothing to delete!", "alert", JOptionPane.ERROR_MESSAGE);
        }
    };

    public void updatePrices() {
        totalBeforeAmount.setText("£" + decimalFormat.format(bill.calculateTotalPrice()));
        totalAmount.setText("£" + decimalFormat.format(bill.getDiscountedPrice()));
        discountAmount.setText("- £" + decimalFormat.format(bill.calculateTotalPrice() - bill.getDiscountedPrice()));
    }

    public void addBill() {
        manager.addBill(bill);
        JOptionPane.showMessageDialog(null, "Order placed", "success", JOptionPane.INFORMATION_MESSAGE);

    }
}
