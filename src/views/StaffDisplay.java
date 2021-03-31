// Setting the package
package views;

//Importing the GUI components
import java.util.List;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;

//Importing the interface
import interfaces.Observer;

//Importing the models
import model.Bill;
import model.Order;
import model.Staff;

/**
 * using MVC pattern
 * 
 * @author Rose Ulldemolins
 *
 */

public class StaffDisplay extends JPanel implements Observer {
    private Staff staff; // The staff member for the panel
    // The GUI components
    JLabel totalLabel = new JLabel();
    JLabel discountLabel = new JLabel();
    JLabel discountedLabel = new JLabel();
    JLabel customerLabel = new JLabel();
    JTextArea itemLabel = new JTextArea();
    DecimalFormat decimalFormat;
    JButton removeStaff = new JButton("Remove Staff");
    JButton addStaff = new JButton("Add Staff");
    JPanel processingPanel = new JPanel();

    // Setting up the GUI
    public StaffDisplay(Staff staff) {

        this.staff = staff;
        // Creating a decimal format for currency
        this.decimalFormat = new DecimalFormat("#.00");
        // Setting the size of the window
        this.setPreferredSize(new Dimension(275, 300));
        staff.registerObserver(this);
        // Setting border and title
        this.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Server " + staff.getStaffID())));
        // Setting the label text
        customerLabel.setText("Not currently processing an order");
        // Adding all of the components to the JPanel
        this.add(customerLabel);
        this.add(itemLabel);
        this.add(removeStaff);
    }

    // Tells the Observer to update itself (to change the data in the labels)
    public void update() {
        // Checking to see if the staff member is on shift and if not remove the
        // relevant components
        if (!staff.isOnShift()) {
            processingPanel.removeAll();
            this.add(addStaff);
            this.remove(itemLabel);
            customerLabel.setText("Not currently working");
            this.remove(removeStaff);
            this.repaint();
        } else {
            // Checking to see if the staff has a bill
            if (staff.getBill() != null) {
                Bill newBill = staff.getBill();
                // Variables for the calculations
                double newTotal = newBill.calculateTotalPrice();
                double newDiscountedTotal = newBill.getDiscountedPrice();
                double newDiscount = newTotal - newDiscountedTotal;
                // Setting the labels with the values
                totalLabel.setText("Total: £" + decimalFormat.format(newTotal));
                discountLabel.setText("Discount: £" + decimalFormat.format(newDiscount));
                discountedLabel.setText("Discounted Total: £" + decimalFormat.format(newDiscountedTotal));
                customerLabel.setText("Processing for customer: " + staff.getBill().getCustomerID());
                // Adding a list to the JTextAre for each order being processed
                List<Order> orderList = staff.getBill().getOrderList();
                String itemList = "";
                for (Order order : orderList) {
                    itemList += order.getItem().getItemName() + "\n";
                    itemLabel.setText(itemList);
                }
                this.add(itemLabel);
                // Adding the labels to the panel
                processingPanel.setLayout(new BoxLayout(processingPanel, BoxLayout.Y_AXIS));
                processingPanel.add(totalLabel);
                processingPanel.add(discountLabel);
                processingPanel.add(discountedLabel);
                // Adding the labels to the overall panel
                this.add(processingPanel);
                this.remove(addStaff);
                this.add(removeStaff);
                this.repaint();
            }
        }
    }

    public void addActionListener(ActionListener actionListener) {
        // Listening for when the remove staff button is clicked
        removeStaff.addActionListener(actionListener);
        // Listening for when the add staff button is clicked
        addStaff.addActionListener(actionListener);
    }

    // Returning the remove button
    public JButton getRemoveButton() {
        return removeStaff;
    }

    // Returning the add button
    public JButton getAddButton() {
        return addStaff;
    }

}
