
package views;

import interfaces.Observer;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.Bill;
import model.Order;
import model.Staff;

import java.awt.*;
import java.text.DecimalFormat;

import java.awt.event.ActionListener;

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
    JButton removeStaff = new JButton("Remove");
    JPanel processingPanel = new JPanel();

    // Setting up the GUI
    public StaffDisplay(Staff staff) {
        this.staff = staff;
        this.decimalFormat = new DecimalFormat("#.00");
        this.setPreferredSize(new Dimension(250, 300));
        staff.registerObserver(this);

        // Creating a label for the heading at the top of a page
        // Setting border and title
        this.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Server " + staff.getStaffID())));

        customerLabel.setText("Not currently processing an order");
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
            this.remove(itemLabel);
            this.remove(customerLabel);
            this.remove(removeStaff);
            this.repaint();
            // Checking to see if the staff has a bill
        } else if (staff.getBill() != null) {
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
            // Adding the labels to the panel
            processingPanel.setLayout(new BoxLayout(processingPanel, BoxLayout.Y_AXIS));
            processingPanel.add(totalLabel);
            processingPanel.add(discountLabel);
            processingPanel.add(discountedLabel);
            // Adding the labels to the overall panel
            this.add(processingPanel);
        }
    }

    public void addActionListener(ActionListener actionListener) {
        // Listening for when the remove staff button is clicked
        removeStaff.addActionListener(actionListener);
    }

}
