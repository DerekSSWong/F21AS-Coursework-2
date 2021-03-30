/**
 @author Rose Ulldemolins
 * This class is the Controller for the Staff, which responds to user interaction.
 */

package controllers;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.JobDispatcher;
import model.Staff;
import views.StaffDisplay;

import java.awt.event.ActionEvent;

public class StaffController {

    private StaffDisplay view; // GUI to show staff members
    private Staff staff; // queue of bills

    public StaffController(StaffDisplay view, Staff staff) {
        this.staff = staff;
        this.view = view;
        // Specify the listeners for the view
        view.addActionListener(new RemoveListener());
    }

    public class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JobDispatcher.getInstance().removeStaff(staff);

        }
    }
}
