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

    Car car1;
    Car car2;
    Car car3;
    Car car4;
    Car car5;

    AppPanel() {
        setSize(500, 500);
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
        timer = new Timer(30, (abc) -> {
            // car1.moveUp();
            // car2.moveUp();
            // car3.moveUp();
            // car4.moveUp();
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

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        // TODO Auto-generated method stub
        // car1.paintCar(pen);
        car2.paintCar(pen);
        // car3.paintCar(pen);
        // car4.paintCar(pen);
        // car5.paintCar(pen);
    }
}
