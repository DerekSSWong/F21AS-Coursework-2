
package views;

import interfaces.Observer;
import java.awt.*;
import java.util.List;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.Bill;
import model.Item;
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
    JLabel customerLabel, itemLabel, totalLabel, discountLabel, discountedLabel;
    JTable queueTable;
    JScrollPane queueScrollPane;
    JPanel staffPanel;

    // Setting up the GUI
    public StaffDisplay(Staff staff) {
        this.staff = staff;
        staff.registerObserver(this);

        // Creating a label for the heading at the top of a page
        staffPanel = new JPanel();
        staffPanel.setLayout(new BoxLayout(staffPanel, BoxLayout.Y_AXIS));
        // Setting border and title
        staffPanel.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Server " + staff.getStaffID())));
        customerLabel = new JLabel("Processing for customer: " + staff.getBill().getCustomerID());
        List<Order> orderList = staff.getBill().getOrderList();

        for (Order order : orderList) {
            itemLabel = new JLabel(order.getItem().getItemName());
            this.add(itemLabel);
        }
        double total = staff.getBill().calculateTotalPrice();
        double discountedTotal = staff.getBill().getDiscountedPrice();
        double discount = total - discountedTotal;

        totalLabel = new JLabel("Total (before discount): " + total);
        discountLabel = new JLabel("Discount: " + discount);
        discountedLabel = new JLabel("Total: " + discountedTotal);
        this.add(totalLabel);
        this.add(discountLabel);
        this.add(discountedLabel);
        update();

    }

    // Tells the Observer to update itself (to change the data in the labels)

    public void update() {
        Bill newBill = staff.getBill();
        double newTotal = newBill.calculateTotalPrice();
        double newDiscountedTotal = newBill.getDiscountedPrice();
        double newDiscount = newTotal - newDiscountedTotal;
        totalLabel.setText("Total (before discount): " + newTotal);
        discountLabel.setText("Discount: " + newDiscount);
        discountedLabel.setText("Total: " + newDiscountedTotal);

    }

}