import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CarGamePanel extends JPanel implements ActionListener, KeyListener {
    private Image roadImage;
    private Image carImage;
    private Image[] obstacleImages;
    private int carX = 250;
    private int carY = 500;
    private int carSpeed = 10;
    private Timer timer;
    private ArrayList<Obstacle> obstacles; 
    private int obstacleSpeed = 5;
    private Random random;
    private boolean gameOver;
    private int score;

   
    private static class Obstacle {
        Rectangle rect;
        Image image;

        Obstacle(Rectangle rect, Image image) {
            this.rect = rect;
            this.image = image;
        }
    }

    public CarGamePanel() {
        try {
            roadImage = ImageIO.read(getClass().getResource("road.jpg"));
            carImage = ImageIO.read(getClass().getResource("CAR.jpg"));
            obstacleImages = new Image[3];
            obstacleImages[0] = ImageIO.read(getClass().getResource("obst.jpg"));
            obstacleImages[1] = ImageIO.read(getClass().getResource("obstr.jpg"));
            obstacleImages[2] = ImageIO.read(getClass().getResource("obste.jpg"));
        } catch (IOException e) {
            System.err.println("Error loading images:");
            e.printStackTrace();
        }

        obstacles = new ArrayList<>();
        random = new Random();
        timer = new Timer(40, this);
        timer.start();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        gameOver = false;
        score = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (roadImage != null) {
            g.drawImage(roadImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (carImage != null) {
            g.drawImage(carImage, carX, carY, 60, 90, this);
        }

        for (Obstacle obstacle : obstacles) {
            g.drawImage(obstacle.image, obstacle.rect.x, obstacle.rect.y, obstacle.rect.width, obstacle.rect.height, this);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over!", getWidth() / 2 - 100, getHeight() / 2);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press R to Restart", getWidth() / 2 - 90, getHeight() / 2 + 40);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            for (int i = 0; i < obstacles.size(); i++) {
                Obstacle obstacle = obstacles.get(i);
                obstacle.rect.y += obstacleSpeed;

                if (obstacle.rect.y > getHeight()) {
                    obstacles.remove(i);
                    i--;
                    score++;
                }

                if (obstacle.rect.intersects(new Rectangle(carX, carY, 60, 90))) {
                    gameOver = true;
                    timer.stop();
                }
            }

            if (random.nextInt(60) == 0) {
                int obstacleX = random.nextInt(280) + 120;
                int obstacleY = 0;
                int obstacleImageIndex = random.nextInt(obstacleImages.length); 
                Image obstacleImage = obstacleImages[obstacleImageIndex];

                boolean overlap = false;
                for (Obstacle existingObstacle : obstacles) {
                    if (existingObstacle.rect.intersects(new Rectangle(obstacleX, obstacleY, 60, 90))) {
                        overlap = true;
                        break;
                    }
                }

                if (!overlap) {
                    obstacles.add(new Obstacle(new Rectangle(obstacleX, obstacleY, 60, 90), obstacleImage));
                }
            }

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (gameOver && key == KeyEvent.VK_R) {
            carX = 250;
            carY = 500;
            obstacles.clear();
            score = 0;
            gameOver = false;
            timer.start();
            repaint();
        } else {
            if (key == KeyEvent.VK_LEFT && carX > 120) {
                carX -= carSpeed;
            }
            if (key == KeyEvent.VK_RIGHT && carX < 400) {
                carX += carSpeed;
            }
            if (key == KeyEvent.VK_UP && carY > 0) {
                carY -= carSpeed;
            }
            if (key == KeyEvent.VK_DOWN && carY < getHeight() - 90) {
                carY += carSpeed;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Game");
        CarGamePanel gamePanel = new CarGamePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}