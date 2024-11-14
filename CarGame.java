import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class CarGame extends JPanel implements ActionListener {

    private int carX = 250; 
    private int carY = 400; 
    private final int CAR_WIDTH = 50; 
    private final int CAR_HEIGHT = 100; 
    private Timer timer;

    // Enemy car properties
    private int enemyCarX;
    private int enemyCarY = 0; // Start from the top
    private final int ENEMY_CAR_WIDTH = 50;
    private final int ENEMY_CAR_HEIGHT = 100;
    private final int ENEMY_SPEED = 5; // Speed of the enemy car
    private Random random;

    // Image variables
    private BufferedImage playerCarImage;
    private BufferedImage enemyCarImage;
    private BufferedImage backgroundImage;

    public CarGame() {
        setBackground(Color.green); 
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveCar(e);
            }
        });
        timer = new Timer(20, this); 
        timer.start();
        random = new Random();
        spawnEnemyCar();
        loadImages(); // Load images
    }

    private void loadImages() {
        try {
            // Load images from files
            playerCarImage = ImageIO.read(new File("/Users/ice/JAVA IDE PROGRAMS/istockphoto-1256522275-612x612.jpg")); // Update with your image path
            enemyCarImage = ImageIO.read(new File("/Users/ice/JAVA IDE PROGRAMS/pngtree-car-top-view-rendering-still-life-png-image_4083069.png.jpeg")); // Update with your image path
            backgroundImage = ImageIO.read(new File("/Users/ice/JAVA IDE PROGRAMS/istockphoto-1425173064-612x612.jpg")); // Update with your image path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        drawBackground(pen); // Draw background
        drawCar(pen);
        drawEnemyCar(pen);
    }

    private void drawBackground(Graphics pen) {
        pen.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null); // Draw background image
    }

    private void drawCar(Graphics pen) {
        pen.drawImage(playerCarImage, carX, carY, CAR_WIDTH, CAR_HEIGHT, null); // Draw player's car image
    }

    private void drawEnemyCar(Graphics pen) {
        pen.drawImage(enemyCarImage, enemyCarX, enemyCarY, ENEMY_CAR_WIDTH, ENEMY_CAR_HEIGHT, null); // Draw enemy car image
    }

    private void spawnEnemyCar() {
        enemyCarX = random.nextInt(getWidth() - ENEMY_CAR_WIDTH); // Random horizontal position
        enemyCarY = 0; // Reset to the top
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        enemyCarY += ENEMY_SPEED; // Move enemy car down
        if (enemyCarY > getHeight()) {
            spawnEnemyCar(); // Respawn enemy car when it goes off screen
        }
       checkCollision(); // Check for collision
               repaint(); 
           }
           private void checkCollision() {
            Rectangle playerCarRect = new Rectangle(carX, carY, CAR_WIDTH, CAR_HEIGHT);
            Rectangle enemyCarRect = new Rectangle(enemyCarX, enemyCarY, ENEMY_CAR_WIDTH, ENEMY_CAR_HEIGHT);
            
            if (playerCarRect.intersects(enemyCarRect)) {
                // Handle collision (e.g., stop the game, reset position, etc.)
                System.out.println("Collision detected!");
                // For example, you could stop the timer or show a game over screen.
                timer.stop(); // Stop the game
                JOptionPane.showMessageDialog(this, "Game Over!"); // Show game over message
                System.exit(0); // Exit the game
            }
        } 
           
       
           private void moveCar(KeyEvent e) {
               int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && carX > 0) {
                    carX -= 5; 
               }
               if (key == KeyEvent.VK_RIGHT && carX < getWidth() - CAR_WIDTH) {
                    carX += 5; 
               }
      if (key == KeyEvent.VK_UP && carY > 0) {
           carY -= 5; 
      }
      if (key == KeyEvent.VK_DOWN && carY < getHeight() - CAR_HEIGHT) {
           carY += 5; 
      }
  }

   public static void main(String[] args) {
      JFrame frame = new JFrame("Simple 2D Car Game");
       CarGame game = new CarGame();
     frame.add(game);
      frame.setSize(800, 600); 
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
  }
}