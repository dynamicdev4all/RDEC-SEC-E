import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Car {
    int x;
    int y;
    int w;
    int h;
    int speed;
    String fileName;
    BufferedImage carPhoto;

    Car(int x, int y, int w, int h, String fileName, int speed) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fileName = fileName;
        this.speed = speed;
        loadBgImage();
    }

    void loadBgImage() {
        try {
            carPhoto = ImageIO.read(AppPanel.class.getResource(fileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void paintCar(Graphics pen) {
        pen.drawImage(carPhoto, x, y, w, h, null);
    }

    void moveUp() {
        y = y - speed;
    }
}