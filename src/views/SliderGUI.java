package views;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 
 * 
 * 
 * @author Dan Ryan, Rose Ulldemolins
 *
 */

public class SliderGUI extends JFrame {
    // Setting the minimum speed of the simulation
    int minSpeed = 25;
    // Setting the maximum speed of the simulation
    int maxSpeed = 300;
    // Setting the default speed of the simulation
    int initSpeed = 100;
    // GUI components
    JFrame userCustomisationFrame;
    JPanel userCustomisationPanel;
    JLabel simulationSpeedLabel, timeLabelOne, timeLabelTwo ;
    JSlider simulationSpeedSlider;
    JButton startSimulation;

    public SliderGUI() {
        // Create new frame for the slider display
        userCustomisationFrame = new JFrame();
        // Create new panel for the slider
        userCustomisationPanel = new JPanel();
        // Setting the layout and border of the panel
        userCustomisationPanel.setLayout(new BoxLayout(userCustomisationPanel, BoxLayout.Y_AXIS));
        userCustomisationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Creating a new label for the speed % and setting the alignment
        simulationSpeedLabel = new JLabel("Simulation Speed Percentage", JLabel.CENTER);
        simulationSpeedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Creating the slider
        simulationSpeedSlider = new JSlider(JSlider.HORIZONTAL, minSpeed, maxSpeed, initSpeed);
        // Setting the tick measures on the sliders
        simulationSpeedSlider.setMajorTickSpacing(25);
        simulationSpeedSlider.setMinorTickSpacing(5);
        // Forcing the slider to skip to a tick interval
        simulationSpeedSlider.setSnapToTicks(true);
        // Painting the labels and ticks
        simulationSpeedSlider.setPaintTicks(true);
        simulationSpeedSlider.setPaintLabels(true);
        // Creating a label detailing the default times of the programme running at 100% speed
        timeLabelOne = new JLabel("Default times are 2 seconds for incoming orders to be");
        timeLabelOne.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabelTwo = new JLabel("added to the queue and 4 seconds to process items");
        timeLabelTwo.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Creating and aligning the button to start the simulation
        startSimulation = new JButton("Start simulation");
        startSimulation.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Adding the buttons and spacing to the panel
        userCustomisationPanel.add(simulationSpeedLabel);
        userCustomisationPanel.add(Box.createVerticalStrut(10));
        userCustomisationPanel.add(simulationSpeedSlider);
        userCustomisationPanel.add(Box.createVerticalStrut(20));
        userCustomisationPanel.add(timeLabelOne);
        userCustomisationPanel.add(timeLabelTwo);
        userCustomisationPanel.add(Box.createVerticalStrut(30));
        userCustomisationPanel.add(startSimulation);
        // Adding the panel to the frame and setting to exit on close
        userCustomisationFrame.add(userCustomisationPanel);
        userCustomisationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the title of the window
        userCustomisationFrame.setTitle("User Customisation");
        // Setting the size of the window
        userCustomisationFrame.setSize(600, 265);
        // Setting the GUI to show
        userCustomisationFrame.setVisible(true);
        // Setting the location of the frame
        userCustomisationFrame.setLocation(1000, 0);

    }

    public void addSetListener(ChangeListener changeListener) {
        simulationSpeedSlider.addChangeListener(changeListener);
    }

    public void addActionListener(ActionListener actionListener) {
        startSimulation.addActionListener(actionListener);
    }

    public JSlider getSlider() {
        return simulationSpeedSlider;
    }

    public JButton getStartButton() {
        return startSimulation;
    }
}
