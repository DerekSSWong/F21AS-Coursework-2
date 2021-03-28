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
   // private Manager manager;
   // private Queue queue;

    // The GUI components
    JLabel queueLabel;
    JTable queueTable;
    JScrollPane queueScrollPane;

    // Constructor
    public QueueGUI() {
      //  this.manager = manager;
       // this.queue = queue;
        // Setting title for window
        setTitle("Queue");
        // Setting the size of the window
        setSize(500, 500);
        pack();
    }

    public void addNorthPanel(JPanel panel) {
        // Creating the container
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.NORTH);
    }

}