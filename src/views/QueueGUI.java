package views;
/**
 * GUI for main application
 * 
 * @author Rose Ulldemolins
 * 
 */

//Importing GUI classes
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
// Importing  models and controllers
import model.KitchenStaff;
import model.Queue;
import model.Staff;
import controllers.StaffController;

public class QueueGUI extends JFrame {

    // The GUI components
    JLabel queueLabel;
    JTable queueTable;
    JScrollPane queueScrollPane;

    // Constructor
    public QueueGUI(Queue queueModel, Staff staffModel, Staff staffModel2, Staff staffModel3,
            KitchenStaff kitchenStaffModel, KitchenStaff kitchenStaffModel2) {
        // Creating the views from the models
        QueueDisplay queueView = new QueueDisplay(queueModel);
        StaffDisplay staffView1 = new StaffDisplay(staffModel);
        StaffDisplay staffView2 = new StaffDisplay(staffModel2);
        StaffDisplay staffView3 = new StaffDisplay(staffModel3);
        KitchenStaffDisplay kitchenStaffView = new KitchenStaffDisplay(kitchenStaffModel);
        KitchenStaffDisplay kitchenStaffView2 = new KitchenStaffDisplay(kitchenStaffModel2);
        // Creating the controllers from the views and models
        StaffController staffController1 = new StaffController(staffView1, staffModel);
        StaffController staffController2 = new StaffController(staffView2, staffModel2);
        StaffController staffController3 = new StaffController(staffView3, staffModel3);

        // Setting title for window
        setTitle("Queue");
        // Setting the size of the window
        setSize(1000, 500);
        // Adding the panels
        addNorthPanel(queueView);
        addCenterPanel(staffView1, staffView2, staffView3);
        addSouthPanel(kitchenStaffView, kitchenStaffView2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Making the GUI visible
        setVisible(true);
        pack();
    }

    public void addNorthPanel(JPanel panel) {
        // Creating the container
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.NORTH);
    }

    public void addCenterPanel(JPanel panel1, JPanel panel2, JPanel panel3) {
        // Adding each staff to the panel
        JPanel staffPanel = new JPanel();
        staffPanel.add(panel1, BorderLayout.WEST);
        staffPanel.add(panel2, BorderLayout.CENTER);
        staffPanel.add(panel3, BorderLayout.EAST);
        this.add(staffPanel, BorderLayout.CENTER);
    }

    public void addSouthPanel(JPanel panel1, JPanel panel2) {
        // Adding both kitchen staff to panel
        JPanel kitchenStaffPanel = new JPanel();
        kitchenStaffPanel.add(panel1, BorderLayout.NORTH);
        kitchenStaffPanel.add(panel2, BorderLayout.SOUTH);
        this.add(kitchenStaffPanel, BorderLayout.SOUTH);
    }

}