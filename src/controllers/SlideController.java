/**
 @author Andrew Dalley, Dan Ryan, Rose Ulldemolins
 * This class is the Controller, which responds to user interaction.
 */

package controllers;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import model.JobDispatcher;
import model.Staff;
import views.SliderGUI;
import java.awt.event.ActionEvent;

public class SlideController {

	private SliderGUI view; // GUI to allow user to set the time
	private JobDispatcher disp; // dispatcher that holds the queue of bills

	public SlideController(SliderGUI view, JobDispatcher disp) {
		this.disp = disp;
		this.view = view;
		// Specify the listeners for the view
		view.addSetListener(new SlideListener());
		view.addActionListener(new StartListener());
	}

	// Responds when the user changes the time
	public class SlideListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			// Getting the value from the slider
			JSlider slider = (JSlider) e.getSource();
			double value = slider.getValue();
			// Calculating the new time
			double newTime = 4000 / (value / 100);
			// Casting it to an integer
			int intNewTime = (int) newTime;
			// Set the time a staff member takes to process an item
			Staff.setTimePerItem(intNewTime);
			// Set the delay in the queue to add an item
			disp.setQueueDelay(intNewTime / 2);
		}
	}

	public class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Disabling the slider and button
			view.getSlider().setEnabled(false);
			view.getStartButton().setEnabled(false);
			// Loads the Bills
			disp.loadBills();
			// Starts the dispatching
			disp.dispatch();
		}
	}
}
