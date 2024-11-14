import javax.swing.*;

class AppFrame extends JFrame {
    public AppFrame() {
        AppSurface surface = new AppSurface();
        add(surface);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
