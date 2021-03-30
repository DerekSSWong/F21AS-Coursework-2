/**
 @author Rose Ulldemolins
 * This class is the Controller for the Staff, which responds to user interaction.
 */

package controllers;

import java.awt.event.ActionListener;

import model.JobDispatcher;
import model.Staff;
import views.StaffDisplay;

import java.awt.event.ActionEvent;

public class StaffController {

    private StaffDisplay view; // GUI to show staff members
    private Staff staff; // staff member the display is of

    public StaffController(StaffDisplay view, Staff staff) {
        this.staff = staff;
        this.view = view;
        // Specify the listeners for the view
        view.addActionListener(new ButtonListener());
    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.getRemoveButton()) {
                // Tells the job dispatcher to remove the staff
                JobDispatcher.getInstance().removeStaff(staff);
            } else if (e.getSource() == view.getAddButton()) {
                // Tells the job dispatcher to add staff
                JobDispatcher.getInstance().addStaff(staff);
            }
        }
    }
}