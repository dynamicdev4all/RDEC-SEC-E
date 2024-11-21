import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class HomePanel extends JPanel {
    Image bgImage;
    JButton startButton;
    JButton exitButton;

    HomePanel() {
        setBounds(0, 0, 800, 680);
        setLayout(null);
        loadImages();
        loadButtons();
        startGame();
        exitGame();

    }

    void loadImages() {
        try {
            BufferedImage bufferBgImg = ImageIO.read(HomePanel.class.getResource("./Images/Logo.png"));
            bgImage = bufferBgImg.getScaledInstance(800, 680, Image.SCALE_DEFAULT);
          
        } catch (Exception e) {
            System.out.println("Image can't find");
        }
    }

    void startGame() {
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GameFrame();
            }

        });

    }

    void loadButtons() {
        startButton = new JButton("START");
        startButton.setFont(
                new Font("Agency FB", Font.BOLD, 32));

        startButton.setBackground(Color.GREEN);
        startButton.setBounds(200, 540, 100, 50);

        exitButton = new JButton("EXIT");
        exitButton.setFont(
                new Font("Agency FB", Font.BOLD, 32));
        exitButton.setBackground(Color.RED);
        exitButton.setBounds(400, 540, 100, 50);
        add(startButton);
        add(exitButton);

    }

    void exitGame() {
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Are you sure?", "EXIT", JOptionPane.YES_NO_CANCEL_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // JPanel panel = new QuestionOnExitPanel();
                // JOptionPane.showMessageDialog(null,panel,"EXIT",JOptionPane.PLAIN_MESSAGE);

            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
    }
}
