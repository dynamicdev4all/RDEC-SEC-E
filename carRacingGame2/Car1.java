
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Car1 extends JLabel {
    int x;
    int y;
    int h;
    int w;
    int speed;
    Image image;
    String fileName;
    Timer timer;
    GamePanel gp;

    Car1(int x, int y, int h, int w, String fileName, int speed, GamePanel gp) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        this.fileName = fileName;
        this.speed = speed;
        loadBgImage();
        paintImage();
        move();
    }

    void loadBgImage() {
        try {
            BufferedImage bufferImg = ImageIO.read(GamePanel.class.getResource(fileName));
            image = bufferImg.getScaledInstance(w, h, Image.SCALE_DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void paintImage() {
        // pen.drawImage(image, x, y, w, h, null);
        setIcon(new ImageIcon(image));
        setBounds(x, y, w, h);
    }

    void move() {
        timer = new Timer(30, (a) -> {
            y = y + speed;

            setLocation(x, y);

        });
        timer.start();
    }
}