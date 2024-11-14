import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class AppPanel extends JPanel {
    Random r = new Random();
    static ImageIcon imageIcon;
    BufferedImage car;
    static ImageIcon imageIcon1;
    static ImageIcon imageIcon2;

    Timer timer;
    int xAxis1, yAxis1;
    int xAxis2, yAxis2;

    // Player car position (controlled by arrow keys)
    int playerX = 435; // Starting X position of the player's car on the road
    int playerY = 400; // Fixed Y position of the player's car

    // Constants for road lanes (positions where enemy cars can appear)
    final int[] roadLanes = {210, 435, 620}; // Lane positions on the road

    // Game over flag
    boolean gameOver = false;

    AppPanel() {
        setSize(400, 400);
        showBgImage();
        initializeEnemyCars();
        recallPaintComp();
        keyboardControl();
        setFocusable(true);
    }

    void showBgImage() {
       try {
        car=ImageIO.read(getClass().getResourceAsStream("/roads.jpeg"));
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } // Road background
        imageIcon1 = new ImageIcon(getClass().getResource("carh.png")); // Player car image
        imageIcon2 = new ImageIcon(getClass().getResource("carh.png")); // Enemy car image
    }

    void initializeEnemyCars() {
        // Initialize enemy cars at random lanes and positions
        xAxis1 = roadLanes[r.nextInt(roadLanes.length)];
        xAxis2 = roadLanes[r.nextInt(roadLanes.length)];
        
        // Ensure the two enemy cars are in different lanes initially
        while (xAxis1 == xAxis2) {
            xAxis2 = roadLanes[r.nextInt(roadLanes.length)];
        }

        // Set initial Y positions above the screen for smooth entry
        yAxis1 = -50;
        yAxis2 = -300;
    }

    @Override
    protected void paintComponent(Graphics g) {

      g.drawImage(car, 0, 0, 1000,720,null);
        if (imageIcon1 != null) {
            imageIcon1.paintIcon(this, g, playerX, playerY); // Draw player's car
        }

        // Draw enemy cars only if within screen bounds and game is not over
        if (!gameOver) {
            if (yAxis1 < 720) {
                imageIcon2.paintIcon(this, g, xAxis1, yAxis1);
            }
            if (yAxis2 < 720) {
                imageIcon2.paintIcon(this, g, xAxis2, yAxis2);
            }
        }

        // Display Game Over message and replay instructions
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 250, 250);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press 'R' to Replay", 250, 300);
        }
    }

    void recallPaintComp() {
        timer = new Timer(30, e -> {
            if (!gameOver) {
                yAxis1 += 5; //enemy car move
                yAxis2 += 5; // 2 enemy car

                // reset the position of first car
                if (yAxis1 > 720) {
                    yAxis1 = -50; // Reset position above the screen
                    xAxis1 = roadLanes[r.nextInt(roadLanes.length)]; // Randomize lane
                }

                // Ensure car 2 is in a different lane from car 1
                if (yAxis2 > 720) {
                    yAxis2 = -300; // Reset position above the screen
                    do {
                        xAxis2 = roadLanes[r.nextInt(roadLanes.length)];
                    } while (xAxis2 == xAxis1); // Keep generating until lane is different from car 1
                }

                checkCollision(); // Check for collisions between player and enemy cars
                repaint(); // Repaint the panel to reflect updated positions
            }
        });
        timer.start();
    }

    void checkCollision() {
        // Check collision with enemy car 1
        if (Math.abs(playerX - xAxis1) < 50 && Math.abs(playerY - yAxis1) < 50) {
            gameOver = true;
            timer.stop(); // Stop the game timer
        }
        // Check collision with enemy car 2
        if (Math.abs(playerX - xAxis2) < 50 && Math.abs(playerY - yAxis2) < 50) {
            gameOver = true;
            timer.stop(); // Stop the game timer
        }
    }

    void resetGame() {
        // Reset the player and enemy positions and restart the timer
        playerX = 435;
        yAxis1 = -50;
        yAxis2 = -300;
        initializeEnemyCars();
        gameOver = false;
        timer.start(); // Restart the game timer
        repaint();
    }

    void keyboardControl() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
                    resetGame(); // Reset the game when 'R' is pressed after game over
                }
                if (!gameOver) { // Only allow movement if the game is not over
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        playerX = Math.min(playerX + 225, roadLanes[2]); // Move right within bounds
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        playerX = Math.max(playerX - 225, roadLanes[0]); // Move left within bounds
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}
