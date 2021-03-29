package model;

import views.QueueDisplay;
import views.QueueGUI;
import views.StaffDisplay;
import controllers.QueueController;

public class CafeDemo {
    private QueueGUI gui;
    private Staff staff1 = new Staff(1, "Charlie");
    private Staff staff2 = new Staff(2, "Mac");
    private Staff staff3 = new Staff(3, "Dennis");;

    public void showGUI() {
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
        StaffDisplay staffView1 = new StaffDisplay(staffModel1);
        StaffDisplay staffView2 = new StaffDisplay(staffModel2);
        StaffDisplay staffView3 = new StaffDisplay(staffModel3);

        
        
        gui = new QueueGUI();
        gui.setSize(1000, 500);
        gui.addNorthPanel(queueView);
        gui.addCenterPanel(staffView1, staffView2, staffView3);
        gui.setVisible(true);
        dispatcher.dispatch();

        // it needs to know about the view and the model
        QueueController controller = new QueueController(queueView, queueModel);
        
    }

    QueueGUI getGUI() {
    	
    	return gui;
    }
    
    
    public static void main(String arg[]) {
        CafeDemo cafeDemo = new CafeDemo();
        cafeDemo.showGUI();
    }
}
