import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AppPanel extends JPanel {
    BufferedImage bgImage;
    Timer timer;
    int x = 0;
    int y = 100;
    int birdX = 50;
    int initialIndex = 0;
    Car car1;
    Car car2;
    Car car3;
    Car car4;
    Car car5;

    Bird b1 = new Bird();
    BufferedImage images[] = b1.getSingleImages();

    AppPanel() {
        setSize(500, 500);
        b1 = new Bird();
        // car1 = new Car(30, 300, 80, 120, "car.png", 1);
        car2 = new Car(0, 0, 90, 113, "doremon.png", 5);
        car5 = new Car(150, 50, 80, 120, "car.png", 5);
        car3 = new Car(250, 300, 80, 120, "car.png", 2);
        car4 = new Car(360, 300, 80, 120, "car.png", 10);
        // setBackground(Color.BLUE);
        keyBoardControls();
        appLoop();
        setFocusable(true);
    }

    void appLoop() {
        timer = new Timer(60, (abc) -> {
            // car1.moveUp();
            // car2.moveUp();
            // car3.moveUp();
            // car4.moveUp();
            birdX += 10;
            if (birdX > 500) {
                birdX = -100;
            }
            initialIndex++;
            if (initialIndex > 10) {
                initialIndex = 0;
            }
            repaint();
        });
        timer.start();
    }

    void keyBoardControls() {
        System.out.println("YES called");
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KEVENT CALLED");
                // TODO Auto-generated method stub
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x = x + 1;
                }

                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
            }

        });
    }

    void paintImg(Graphics pen) {
        // for (int i = 0; i < images.length; i++) {
        b1.paintBird(pen, images[initialIndex], birdX);
        // birdX += 10;
        // System.out.println(i);
        // }
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        // TODO Auto-generated method stub
        // car1.paintCar(pen);
        // car2.paintCar(pen);
        // car3.paintCar(pen);
        // car4.paintCar(pen);
        // car5.paintCar(pen);
        paintImg(pen);
    }
}
