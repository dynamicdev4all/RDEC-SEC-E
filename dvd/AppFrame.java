import javax.swing.JFrame;

public class AppFrame extends JFrame {
    AppFrame(){
        initApp();
    }

    public void initApp(){
        setTitle("DVD ANIMATION");
        setSize(570,570);
        setLocationRelativeTo(null);//Set the location of the frame to be in the middle of the screen.
        AppPanel ap = new AppPanel();
        add(ap);
        setResizable(false);
        setVisible(true);
        ap.requestFocusInWindow();
    }
}