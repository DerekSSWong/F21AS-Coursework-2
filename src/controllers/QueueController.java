/**
 @author Andrew Dalley
 * This class is the Controller, which responds to user interaction.
 */

package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Queue;
import views.QueueDisplay;


public class QueueController {

	private QueueDisplay view; // GUI to allow user to set the time
	private Queue queue; // queue of bills 

	public QueueController(QueueDisplay view, Queue queue) {
		this.queue = queue;
		this.view = view;
		// specify the listener for the view
		//addSetListener(new SetListener());
	}
/* Needs to be edited, when we add buttons or events 
	// inner class SetListener responds when user sets the time
	public class SetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int hour = Integer.parseInt(view.getHours());
			int min = Integer.parseInt(view.getMins());
			clock.setTime24(hour, min);
		}
	}
*/
}
