import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    AppFrame()    {
        setTitle("RACE");
        setSize(500,500);
        setLocationRelativeTo(null);

       dvd appPanel = new dvd();
        add(appPanel);
        setVisible(true);
//        b.setBackground(Color.blue);

    }
}