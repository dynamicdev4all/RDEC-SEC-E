import java.awt.*;

import javax.swing.*;

public class GameFrame extends JFrame {
   Image icon;

   GameFrame() {
      setTitle("RaceStorm");
      setSize(800, 680);
      setLocationRelativeTo(null);
      GamePanel gp = new GamePanel(this);
      add(gp);
      icon = Toolkit.getDefaultToolkit().getImage("./Images/Logo.png");
      setIconImage(icon);
      setLayout(null);
      setVisible(true);
      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);


   }

}
