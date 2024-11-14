import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

class AppSurface extends JPanel implements ActionListener, KeyListener {
    private ImageIcon playerCarImage, roadImage, opponentCarImage;
    private Timer timer;
    private int playerX = 350, playerY = 500;
    private int roadOffset = 0;
    private ArrayList<Rectangle> opponentCars;
    private Random random = new Random();

    public AppSurface() {

        playerCarImage = new ImageIcon(getClass().getResource("car.jpg"));
        roadImage = new ImageIcon(getClass().getResource("road.jpeg"));
        opponentCarImage = new ImageIcon(getClass().getResource("car1.jpg"));


        opponentCars = new ArrayList<>();
        spawnOpponentCar();

        timer = new Timer(30, this); // Timer for the game loop
        timer.start();
        setPreferredSize(new Dimension(1500, 1000)); // Set panel size

        addKeyListener(this);
        setFocusable(true);
    }

    private void spawnOpponentCar() {
        int xPosition = random.nextInt(700); // Spawn within a specific range
        opponentCars.add(new Rectangle(xPosition, 5, 100, 100));
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = roadOffset; i < getHeight(); i += roadImage.getIconHeight()) {
            g.drawImage(roadImage.getImage(), 0, i, getWidth(), roadImage.getIconHeight(), null);
        }

        roadOffset +=0;
        if (roadOffset >= roadImage.getIconHeight()) {
            roadOffset = 0;
        }

        g.drawImage(playerCarImage.getImage(), playerX, playerY, 60, 100, null);

        for (Rectangle car : opponentCars) {
            g.drawImage(opponentCarImage.getImage(), car.x, car.y, car.width, car.height, null);
            car.y += 10;

            if (car.intersects(new Rectangle(playerX, playerY, 60, 100))) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Game Over!");
            }
        }

        opponentCars.removeIf(car -> car.y > getHeight());

        if (random.nextInt(100) > 95) {
            spawnOpponentCar();
        }
    }


    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && playerX > 0) {
            playerX -= 20;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && playerX + 60 < getWidth()) {
            playerX += 20;
        }
    }


    public void keyReleased(KeyEvent e) {

    }


    public void keyTyped(KeyEvent e) {

    }
}

