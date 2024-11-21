import java.awt.*;

import javax.swing.*;

public class HomeFrame  implements GameConstants {
   private JFrame jf= new JFrame();
    Image icon;

    HomeFrame() {
        jf.setTitle("Flappy-Bird");
        jf.setSize(GameConstants.WIDTH, GameConstants.HEIGHT);
        jf.setLocationRelativeTo(null);

        jf.add(new HomePanel());
        icon = Toolkit.getDefaultToolkit().getImage("./Images/Logo.jpeg");
        jf.setIconImage(icon);
        jf.setLayout(null);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}