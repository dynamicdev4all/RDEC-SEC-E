import javax.swing.JFrame;

public class AppFrame implements AppConstants{

    private JFrame frame;
    LoadBundle lb;

    AppFrame() {
        frame.setTitle("MCA");
        frame.setSize(FRAME_WIDTH, lb.readBundle("APP_HEIGHT"));
        frame.setLocationRelativeTo(null);
        AppPanel appPanel = new AppPanel();
        frame.add(appPanel);
        frame.setVisible(true);
    }
}
