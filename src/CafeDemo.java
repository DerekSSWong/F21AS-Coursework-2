
public class CafeDemo {
    private Manager manager;
    private QueueGUI gui;

    public CafeDemo() {
        manager = new Manager();
        manager.readFile("../Menu.csv");
    }

    public void showGUI() {
        gui = new QueueGUI(manager);
        gui.setVisible(true);
    }

    public static void main(String arg[]) {
        CafeDemo cafeDemo = new CafeDemo();
        cafeDemo.showGUI();
    }

}
