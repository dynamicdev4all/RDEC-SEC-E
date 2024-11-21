import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bird implements GameConstants {
    Image Bird1;
    Image Bird2;
    Image Bird3;
    Image img;
    int width=34;
    int height=24;
    int x = GameConstants.WIDTH / 8;
    int y = GameConstants.HEIGHT / 2;
    int i = 1;
    int velocityY = 0; // move bird up/down speed.
    int gravity = 1;
    GamePanel gp;

    Bird(GamePanel gp) {
        loadImages();
        moveFlap();
        this.gp = gp;

    }

    void moveFlap() {

        if (i > 3) {
            i = 1;
        }
        if (i == 1) {
            img = Bird1;
        }
        if (i == 2) {
            img = Bird2;
        }
        if (i == 3) {
            img = Bird3;
        }
        i++;

    }

    void move() {
        velocityY += gravity;
        y += velocityY;
        y = Math.max(y, 0);
        if (y > GameConstants.HEIGHT-50) {
            gp.gameover = true;
        }
        
    }

    void paintImage(Graphics pen) {
        pen.drawImage(img, x, y,width,height, null);

    }

    void loadImages() {

        try {
            Bird1 = new ImageIcon(getClass().getResource("./Images/redbird-downflap.png")).getImage();
            Bird2 = new ImageIcon(getClass().getResource("./Images/redbird-midflap.png")).getImage();
            Bird3 = new ImageIcon(getClass().getResource("./Images/redbird-upflap.png")).getImage();
        } catch (Exception e) {
            System.out.println("Image can't be find");
        }

    }

}
