
package views;

import interfaces.Observer;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import model.KitchenStaff;
import java.awt.*;

/**
 * using MVC pattern
 * 
 * @author Rose Ulldemolins
 *
 */

public class KitchenStaffDisplay extends JPanel implements Observer {
    private KitchenStaff cook; // The kitchen staff member for the panel
    // The GUI components
    JLabel statusLabel = new JLabel();
    JTextArea itemLabel = new JTextArea();
    JPanel processingPanel = new JPanel();

    // Setting up the GUI
    public KitchenStaffDisplay(KitchenStaff cook) {
        this.cook = cook;
        // Setting the size and layout of the panel
        this.setPreferredSize(new Dimension(300, 175));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Registering as an Observer of cook
        cook.registerObserver(this);
        // Setting border and title
        this.setBorder(new CompoundBorder(new EmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Cook " + cook.getStaffID())));
        // Setting the status text, adding it and updating
        statusLabel.setText("Not currently cooking an order");
        this.add(statusLabel);
        this.add(itemLabel);
        this.repaint();
        update();
    }

    // Tells the Observer to update itself (to change the data in the labels and
    // text area)
    public void update() {
        // If the cook isn't cooking
        if (cook.getCookingState() == "Waiting to cook") {
            // Remove the box for items and set the label
            statusLabel.setText("Not currently cooking an order");
            remove(itemLabel);
            this.repaint();
        } else {
            // Add the box for items and set the label
            statusLabel.setText("Currently cooking");
            itemLabel.setText(cook.getCookingState());
            add(itemLabel);
            this.repaint();
        }

    }
}
