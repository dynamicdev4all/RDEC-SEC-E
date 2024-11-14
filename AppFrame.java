import javax.swing.JFrame;

public class AppFrame extends JFrame {

    public AppFrame() {
        setTitle("ABCD");
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AppPanel fd = new AppPanel();
        add(fd);
        setVisible(true);
    }
}

