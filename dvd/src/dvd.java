
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class dvd extends JPanel {
    BufferedImage bgImage;
    Timer timer;
    int x = 0, y = 0; // Position of the image
    int dx = 2, dy = 2;
    final int IMAGE_WIDTH = 80;
    final int IMAGE_HEIGHT = 80;

    dvd() {
        setSize(500, 500);
        setBackground(new Color(58,35,33));
        loadBgImage();
        keyBoardControls();
        appLoop();
        setFocusable(true);
    }

    void loadBgImage() {
        try {
            bgImage = ImageIO.read(dvd.class.getResource("dvd.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void appLoop() {
        timer = new Timer(1, (abc) -> {

            x += dx;
            y += dy;

            // Check for collisions with panel boundaries
            if (x + IMAGE_WIDTH > getWidth() || x < 0) {
                dx = -dx; // Reverse horizontal direction
                x = Math.max(0, Math.min(x, getWidth() - IMAGE_WIDTH));
            }
            if (y + IMAGE_HEIGHT > getHeight() || y < 0) {
                dy = -dy; // Reverse vertical direction
                y = Math.max(0, Math.min(y, getHeight() - IMAGE_HEIGHT));
            }

            repaint();
        });
        timer.start();
    }

    void keyBoardControls() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {


                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x=x+20;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    x =x-20;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    y = y - 20;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y = y -20;
                }


                x = Math.max(0, Math.min(x, getWidth() - IMAGE_WIDTH));
                y = Math.max(0, Math.min(y, getHeight() - IMAGE_HEIGHT));

                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        pen.drawImage(bgImage, x, y, IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }
}
