
package views;

import interfaces.Observer;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import model.KitchenStaff;
import model.Order;

import java.awt.*;

/**
 * using MVC pattern
 * 
 * @author Rose Ulldemolins
 *
 */

public class KitchenStaffDisplay extends JPanel implements Observer {
    private KitchenStaff cook; // The staff member for the panel
    // The GUI components
    JLabel customerLabel = new JLabel();
    JTextArea itemLabel = new JTextArea();
    JPanel processingPanel = new JPanel();

    // Setting up the GUI
    public KitchenStaffDisplay(KitchenStaff cook) {

        this.cook = cook;
        this.setPreferredSize(new Dimension(250, 100));
        cook.registerObserver(this);

        // Creating a label for the heading at the top of a page
        // Setting border and title
        this.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Cook " + cook.getStaffID())));

        customerLabel.setText("Not currently cooking an order");
        this.add(customerLabel);
        this.add(itemLabel);
        this.repaint();
        update();
    }

    // Tells the Observer to update itself (to change the data in the labels)

    public void update() {
        customerLabel.setText(cook.getCookingState());
        this.repaint();

    }
}
