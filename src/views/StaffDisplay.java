
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
    JTable queueTable;
    JScrollPane queueScrollPane;
    DecimalFormat decimalFormat;

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

    }

    // Tells the Observer to update itself (to change the data in the labels)

    public void update() {
        if (staff.getBill() != null) {
            Bill newBill = staff.getBill();
            double newTotal = newBill.calculateTotalPrice();
            double newDiscountedTotal = newBill.getDiscountedPrice();
            double newDiscount = newTotal - newDiscountedTotal;
            totalLabel.setText("Total: £" + decimalFormat.format(newDiscount));
            discountLabel.setText("Discount: £" + decimalFormat.format(newDiscount));
            discountedLabel.setText("Total: £" + decimalFormat.format(newDiscountedTotal));
            customerLabel.setText("Processing for customer: " + staff.getBill().getCustomerID());
            List<Order> orderList = staff.getBill().getOrderList();
            String itemList = "";
            for (Order order : orderList) {
                itemList += order.getItem().getItemName() + "\n";
                itemLabel.setText(itemList);
            }

            this.add(totalLabel);
            this.add(discountLabel);
            this.add(discountedLabel);
        }
    }

}
