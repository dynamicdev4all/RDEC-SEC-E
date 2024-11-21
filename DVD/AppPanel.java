
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
    int x1 = 0;
    int y1 = 0;

    AppPanel() {
        setSize(500, 500);
        setBackground(Color.BLUE);
        loadBgImage();
        // keyBoardControls();
        appLoop();
        setFocusable(true);
    }

    void loadBgImage() {
        try {
            bgImage = ImageIO.read(AppPanel.class.getResource("car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void appLoop() {

        timer = new Timer(50, (abc) -> {

            if (x > 360)
                x1 = 1;

            if (x < 0)
                x1 = 0;

            if (y > 380)
                y1 = 1;
            if (y < 0)
                y1 = 0;

            if (x1 == 0)
                x += 10;
            else
                x -= 10;
            if (y1 == 0)
                y += 10;
            else
                y -= 10;

            repaint();
        });
        timer.start();
    }

    void keyBoardControls() {
        System.out.println("YES called");
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KEVENT CALLED");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    x = x + 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    x = x - 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    y = y - 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    y = y + 1;
                }

                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method'keyReleased'");
            }

        });
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        pen.drawImage(bgImage, x, y, 120, 120, null);
    }
}