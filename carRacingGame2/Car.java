
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Car {
    int xDI;
    int yDI;
    int x;
    int y;
    int h=100;
    int w=100;
    int speed;
    Image image;
    String fileName;
    Timer timer;
    GamePanel gp;
    int xD[] = { 190, 290, 390, 490 };
    int yD[] = { -200, -400, -600, -800, -1000 };

    Car(int xDI, int yDI, String fileName, int speed, GamePanel gp) {
        this.gp = gp;
        this.xDI = xDI;
        this.yDI = yDI;
        this.x = xD[xDI];
        this.y = yD[yDI];
        this.fileName = fileName;
        this.speed = speed;
        loadBgImage();
    }

    void loadBgImage() {
        try {
            BufferedImage bufferImg = ImageIO.read(GamePanel.class.getResource(fileName));
            image = bufferImg.getScaledInstance(w, h, Image.SCALE_DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void paintImage(Graphics pen) {
        pen.drawImage(image, x, y, w, h, null);
    }

    void move() {
        timer = new Timer(30, (a) -> {
            
            gp.car1.y += 10; // increment the y position of the opponent car
            gp.car2.y += 10;
            gp.car3.y += 10;
            if (gp.car1.y > 680) {
                gp.car1 = gp.randomCarGenerator(gp.car1);

            }
            if (gp.car2.y > 680) { // if the opponent car goes out of the screen then reset the opponent car
                gp.car2 = gp.randomCarGenerator(gp.car2);

            }
            if (gp.car3.y > 680) {
                gp.car3 = gp.randomCarGenerator(gp.car3);
            }

        });
       
        timer.start();


    }
}