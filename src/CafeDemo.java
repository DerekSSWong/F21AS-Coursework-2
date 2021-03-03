
public class CafeDemo {
    private Manager manager;
    private OrderGUI gui;

    public CafeDemo() {
        manager = new Manager();
    }

    public void showGUI() {
        gui = new OrderGUI(manager);
        gui.setVisible(true);
    }

    public static void main(String arg[]) {
        CafeDemo cafeDemo = new CafeDemo();
        cafeDemo.showGUI();
    }

}
