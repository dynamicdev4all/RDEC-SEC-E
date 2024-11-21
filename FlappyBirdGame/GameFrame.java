import java.awt.*;

import javax.swing.*;

public class GameFrame  implements GameConstants {
   private JFrame jf=new JFrame();
   Image icon;

   GameFrame() {
         jf.setTitle("Flappy-Bird");
         jf.setSize(GameConstants.WIDTH, GameConstants.HEIGHT);
         jf.setLocationRelativeTo(null);
         GamePanel gp = new GamePanel();
         jf.add(gp);
         icon = Toolkit.getDefaultToolkit().getImage("./Images/Logo.jpeg");
         jf.setIconImage(icon);
         jf.setLayout(null);
         jf.setVisible(true);
         jf.setResizable(false);
         jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

}