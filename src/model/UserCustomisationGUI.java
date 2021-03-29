import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class UserCustomisationGUI extends JFrame implements ChangeListener {

    int minSpeed = 0;
    int maxSpeed = 300;
    int initSpeed = 100;
    JFrame userCustomisationFrame;
    JPanel userCustomisationPanel;
    JLabel simulationSpeedLabel, numberOfStaff;
    JSlider simulationSpeedSlider;
    JButton addStaff, removeStaff;
    Staff s;
    JobDispatcher j;


    public UserCustomisationGUI () {
        userCustomisationFrame = new JFrame();
        userCustomisationPanel = new JPanel();
        userCustomisationPanel.setLayout(new BoxLayout(userCustomisationPanel, BoxLayout.Y_AXIS));
        userCustomisationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        simulationSpeedLabel = new JLabel("Simulation Speed Percentage", JLabel.CENTER);
        simulationSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        simulationSpeedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed, maxSpeed, initSpeed);
        simulationSpeedSlider.setMajorTickSpacing(25);
        simulationSpeedSlider.setMinorTickSpacing(5);
        simulationSpeedSlider.setSnapToTicks(true);
        simulationSpeedSlider.setPaintTicks(true);
        simulationSpeedSlider.setPaintLabels(true);
        simulationSpeedSlider.addChangeListener(this);

        addStaff = new JButton("Add staff member");
        addStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeStaff = new JButton("Remove staff member");
        removeStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        numberOfStaff = new JLabel("Number of staff: 5", JLabel.CENTER);
        numberOfStaff.setAlignmentX(Component.CENTER_ALIGNMENT);

        userCustomisationPanel.add(simulationSpeedLabel);
        userCustomisationPanel.add(Box.createVerticalStrut(10));
        userCustomisationPanel.add(simulationSpeedSlider);
        userCustomisationPanel.add(Box.createVerticalStrut(30));
        userCustomisationPanel.add(addStaff);
        userCustomisationPanel.add(Box.createVerticalStrut(10));
        userCustomisationPanel.add(removeStaff);
        userCustomisationPanel.add(Box.createVerticalStrut(10));
        userCustomisationPanel.add(numberOfStaff);


        userCustomisationFrame.add(userCustomisationPanel);
        userCustomisationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userCustomisationFrame.setTitle("User Customisation");
        userCustomisationFrame.setSize(600,300);
        userCustomisationFrame.setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider slider= (JSlider)e.getSource();
        double value = slider.getValue();
        double newTime = 200 * (value/100);
        int intNewTime = (int) newTime;
        s.setTimePerItem(intNewTime);
        j.setQueueDelay(intNewTime);
    }
}
