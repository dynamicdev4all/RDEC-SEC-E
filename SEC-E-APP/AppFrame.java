import javax.swing.JFrame;

public class AppFrame implements AppConstants{

    private JFrame frame = new JFrame();
    LoadBundle lb;

    AppFrame() {
        frame.setTitle("MCA");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        AppPanel appPanel = new AppPanel();
        frame.add(appPanel);
        frame.setVisible(true);
    }
}
