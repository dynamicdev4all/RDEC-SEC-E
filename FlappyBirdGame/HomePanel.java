import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class HomePanel extends JPanel implements GameConstants {
    Image bgImage;
    Image birdImg;
    Image pressEnterImage;
    JButton startButton;
    JButton exitButton;

    HomePanel() {
        setBounds(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
        setLayout(null);
        loadImages();
        keyboardControl();
        setFocusable(true);

    }

    void loadImages() {
        try {
            BufferedImage bufferBgImg = ImageIO.read(HomePanel.class.getResource("./Images/background-night.png"));
            bgImage = bufferBgImg.getScaledInstance(GameConstants.WIDTH, GameConstants.HEIGHT, Image.SCALE_DEFAULT);
            BufferedImage bufferBirdImg = ImageIO.read(HomePanel.class.getResource("./Images/redbird-midflap.png"));
            birdImg = bufferBirdImg.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
            BufferedImage bufferPressImg = ImageIO.read(HomePanel.class.getResource("./Images/PressEnter.png"));
            pressEnterImage = bufferPressImg.getScaledInstance(70, 70, Image.SCALE_DEFAULT);

        } catch (Exception e) {
            System.out.println("Image can't find");
        }
    }

    void keyboardControl() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new GameFrame();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(birdImg, 100, 300, null);
        g.drawImage(pressEnterImage, 200, 300, null);
    }
}