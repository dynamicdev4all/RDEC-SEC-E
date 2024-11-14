import java.awt.*;

import javax.swing.*;

public class HomeFrame extends JFrame {
    Image icon;

    HomeFrame() {
        setTitle("RaceStorm");
        setSize(800,680);
        setLocationRelativeTo(null);
        
        add(new HomePanel());
        icon = Toolkit.getDefaultToolkit().getImage("./Images/Logo.png");
        setIconImage(icon);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



    }

}
