package main;

import controllers.QueueController;
import controllers.SlideController;
import views.QueueDisplay;
import views.QueueGUI;
import javax.swing.JOptionPane;
import model.JobDispatcher;
import model.Staff;
import model.Queue;
import views.SliderGUI;
import views.StaffDisplay;

public class CafeDemo {
    private QueueGUI gui;
    private Staff staff1 = new Staff(1, "Charlie");
    private Staff staff2 = new Staff(2, "Mac");
    private Staff staff3 = new Staff(3, "Dennis");;

    public void setUpGUI() {
        JobDispatcher dispatcher = JobDispatcher.getInstance();
        dispatcher.addStaff(staff1);
        dispatcher.addStaff(staff2);
        dispatcher.addStaff(staff3);
        dispatcher.loadBills();
        // model maintains the queue and broadcasts changes
        Queue queueModel = dispatcher.q;
        /*
         * This view displays the GUI, it contains the QueueDisplay that updates when //
         * the queue is changed, and a Main QueueGUI which the QueueDisplay is attached
         * // to.
         */
        Staff staffModel1 = staff1;
        Staff staffModel2 = staff2;
        Staff staffModel3 = staff3;
        QueueDisplay queueView = new QueueDisplay(queueModel);

        // Showing an option pane to start the simulation
        int response = JOptionPane.showConfirmDialog(null, "Would you like to start the simulation?", "alert",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            // If yes creates main GUI and dispatches
            gui = new QueueGUI(queueModel, staffModel1, staffModel2, staffModel3);
            dispatcher.dispatch();
        } else {
            // If no shows a message and closes program
            JOptionPane.showMessageDialog(null, "Exiting...");
            System.exit(0);
        }

        // it needs to know about the view and the model
        QueueController controller = new QueueController(queueView, queueModel);
        SlideController controllr = new SlideController(sliderView, dispatcher);

    }

    public static void main(String arg[]) {

        CafeDemo cafeDemo = new CafeDemo();
        cafeDemo.setUpGUI();
    }
}
