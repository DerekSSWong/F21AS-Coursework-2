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
	private JobDispatcher disp; // queue of bills

	public SlideController(SliderGUI view, JobDispatcher disp) {
		this.disp = disp;
		this.view = view;
		// Specify the listeners for the view
		view.addSetListener(new SlideListener());
		view.addActionListener(new StartListener());
	}

	// Needs to be edited, when we add buttons or events
	// inner class SetListener responds when user sets the time
	public class SlideListener implements ChangeListener {

		/*
		 * @Override public void stateChanged(ChangeEvent e) { // TODO Auto-generated
		 * method stub //disp.SetQueueDelay(10000); System.out.println("Working");
		 * //QueueDisplayTest.sendSpeed(10000)
		 */

		public void stateChanged(ChangeEvent e) {
			// Setting the initial time
			int intNewTime;
			// Getting the value from the slider
			JSlider slider = (JSlider) e.getSource();
			double value = slider.getValue();
			// If the value is greate than 0
			if (value > 0) {
				double newTime = 5000 / (value / 100);
				intNewTime = (int) newTime;
			} else {
				// So it doesn't divide by 0
				intNewTime = 100000000;
			}
			// Set the time a staff member takes to process an item
			Staff.setTimePerItem(intNewTime);
			// Set the delay in the queue to add an item
			disp.setQueueDelay(intNewTime);
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
