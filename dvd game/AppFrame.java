import javax.swing.JFrame;
public class AppFrame extends JFrame {
    AppFrame(){
        initApp();
    }
    public void initApp(){
        setTitle("JAVA SEC E APP");
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        AppPanel ap = new AppPanel();
        add(ap);
        setResizable(false);
    }
}