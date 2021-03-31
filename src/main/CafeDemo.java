// Stating the package name
package main;

// Importing the controllers
import controllers.SlideController;
// Importing the views
import model.JobDispatcher;
import model.KitchenStaff;
import model.Manager;
import model.Staff;
import model.Queue;
// Importing the models
import views.QueueGUI;
import views.SliderGUI;

public class CafeDemo {
    private QueueGUI gui;
    private Staff staff1 = new Staff(1, "Charlie");
    private Staff staff2 = new Staff(2, "Mac");
    private Staff staff3 = new Staff(3, "Dennis");
    private KitchenStaff cook1 = new KitchenStaff(4, "Frank");
    private KitchenStaff cook2 = new KitchenStaff(5, "Dee");

    public void setUpGUI() {
        // Telling the manager (singleton) to read in the files and convert them to
        // bills
        Manager.getInstance().readFile("Menu.csv");
        Manager.getInstance().readFile("ExistingOrders.csv");
        Manager.getInstance().toBills();
        // Telling the job dispatcher (singleton) to add staff
        JobDispatcher dispatcher = JobDispatcher.getInstance();
        dispatcher.addStaff(staff1);
        dispatcher.addStaff(staff2);
        dispatcher.addStaff(staff3);
        dispatcher.addCookStaffList(cook1);
        dispatcher.addCookStaffList(cook2);
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
        KitchenStaff kitchenStaffModel = cook1;
        KitchenStaff kitchenStaffModel2 = cook2;
        SliderGUI sliderView = new SliderGUI();

        // Creates the GUI
        gui = new QueueGUI(queueModel, staffModel1, staffModel2, staffModel3, kitchenStaffModel, kitchenStaffModel2);

        // Slider needs to know about the view and the model
        SlideController slideController = new SlideController(sliderView, dispatcher);

    }

    public static void main(String arg[]) {
        CafeDemo cafeDemo = new CafeDemo();
        cafeDemo.setUpGUI();
    }
}
