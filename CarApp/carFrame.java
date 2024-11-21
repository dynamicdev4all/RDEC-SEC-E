import javax.swing.JFrame;

public class carFrame extends JFrame{
    carFrame(){
        initApp();
    }
    public void initApp() {
       setTitle("Car Game");
       setSize(550, 550);
       setLocationRelativeTo(null);
       carPannel ap=new carPannel();
       add(ap);
        setResizable(false);
        setVisible(true);
        ap.requestFocusInWindow();
    }
}    