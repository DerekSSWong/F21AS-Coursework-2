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

public class QueueGUI extends JFrame {

    // The GUI components
    JLabel queueLabel;
    JTable queueTable;
    JScrollPane queueScrollPane;

    // Constructor
    public QueueGUI() {
        // Setting title for window
        setTitle("Queue");
        // Setting the size of the window
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

}