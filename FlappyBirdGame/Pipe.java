import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.*;

public class Pipe {
    int gap = GameConstants.HEIGHT / 4;
    int uh;
    int dh;
    int x;
    int y;
    int w = 50;
    Image pipeUp;
    Image pipeDown;
    Timer timer;
    GamePanel gp;
    Random random;

    Pipe(int x, int h) {

        loadImages();

    }

    void loadImages() {
        try {
            pipeDown = new ImageIcon(getClass().getResource("./Images/pipeU-red.png")).getImage();
            pipeUp = new ImageIcon(getClass().getResource("./Images/pipe-red.png")).getImage();

        } catch (Exception e) {
            System.out.println("Image can't find");
        }
    }

    void paintImage(Graphics pen) {
        pen.drawImage(pipeUp, x, 0, w, uh, null);
        pen.drawImage(pipeDown, x, 400 - dh, w, dh, null);
    }

}
