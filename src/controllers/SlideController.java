/**
 @author Andrew Dalley, Dan
 * This class is the Controller, which responds to user interaction.
 */

package controllers;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.JobDispatcher;
import model.Staff;

import views.SliderGUI;


public class SlideController {

	private SliderGUI     view; // GUI to allow user to set the time
	private JobDispatcher disp; // queue of bills 

	public SlideController(SliderGUI view, JobDispatcher disp) {
		this.disp = disp;
		this.view = view;
		// specify the listener for the view
		view.addSetListener(new slideListener() );
	}
	
	//Needs to be edited, when we add buttons or events 
	// inner class SetListener responds when user sets the time
	public class slideListener implements ChangeListener {
		
		/*
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			//disp.SetQueueDelay(10000);
			System.out.println("Working");
			//QueueDisplayTest.sendSpeed(10000) 
			*/	
						
		public void stateChanged(ChangeEvent e) {
			int intNewTime;
			
	        JSlider slider= (JSlider)e.getSource();
	        double value = slider.getValue();
	        if (value > 0) {
	            double newTime = 5000 / (value/100);
	            intNewTime = (int) newTime;
	        } else {
	        	intNewTime = 100000000;
	        }
	        Staff.setTimePerItem(intNewTime);
	        disp.setQueueDelay(intNewTime);
	    }
	}
}
