import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
    int x;
    int y;
    int w;
    int h;
    int speed;
    String fileName;
    static BufferedImage completeBirdPhoto;
    static BufferedImage[] birdImages = new BufferedImage[10];

    Bird() {
        loadBgImage();
    }

    void loadBgImage() {
        try {
            completeBirdPhoto = ImageIO.read(AppPanel.class.getResource("bird.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static BufferedImage[] getSingleImages() {
        birdImages[0] = completeBirdPhoto.getSubimage(0, 0, 194, 180);
        birdImages[1] = completeBirdPhoto.getSubimage(194, 0, 194, 180);
        birdImages[2] = completeBirdPhoto.getSubimage(388, 0, 194, 180);
        birdImages[3] = completeBirdPhoto.getSubimage(582, 0, 194, 180);
        birdImages[4] = completeBirdPhoto.getSubimage(776, 0, 194, 180);
        birdImages[5] = completeBirdPhoto.getSubimage(0, 180, 194, 180);
        birdImages[6] = completeBirdPhoto.getSubimage(194, 180, 194, 180);
        birdImages[7] = completeBirdPhoto.getSubimage(388, 180, 194, 180);
        birdImages[8] = completeBirdPhoto.getSubimage(582, 180, 194, 180);
        birdImages[9] = completeBirdPhoto.getSubimage(776, 180, 194, 180);
        return birdImages;
    }

    void paintBird(Graphics pen, BufferedImage img, int x) {
        pen.drawImage(img, x, 150, 100, 100, null);
    }

    void moveUp() {
        y = y - speed;
    }
}
