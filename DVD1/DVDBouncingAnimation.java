 
 
 import javax.swing.*;
 import java.awt.*;
 
 public class DVDBouncingAnimation extends JPanel {
 
 
 
 // Panel dimensions
 private final int PANEL_WIDTH = 800;
 private final int PANEL_HEIGHT = 600;

 // DVD logo dimensions
 private final int LOGO_WIDTH = 100;
 private final int LOGO_HEIGHT = 50;

 // DVD logo position
 private int logoX = 100; // Initial X position
 private int logoY = 100; // Initial Y position

 // DVD logo movement speed
 private int xSpeed = 5; // Horizontal speed
 private int ySpeed = 5; // Vertical speed

 // DVD logo color
 private Color logoColor = Color.RED;

 // Constructor to set up the animation panel
 public DVDBouncingAnimation() {
     setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
     setBackground(Color.BLACK);

     // Timer for animation loop
     Timer timer = new Timer(20, e -> moveLogo());
     timer.start();
 }

 // Method to move the DVD logo and handle bouncing
 private void moveLogo() {
     // Update logo position
     logoX += xSpeed;
     logoY += ySpeed;

     // Bounce off left and right edges
     if (logoX <= 0 || logoX + LOGO_WIDTH >= PANEL_WIDTH) {
         xSpeed = -xSpeed;
         changeColor(); // Change color on bounce
     }

     // Bounce off top and bottom edges
     if (logoY <= 0 || logoY + LOGO_HEIGHT >= PANEL_HEIGHT) {
         ySpeed = -ySpeed;
         changeColor(); // Change color on bounce
     }

     // Repaint the panel to show the updated position
     repaint();
 }

 // Method to change the logo's color randomly
 private void changeColor() {
     logoColor = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
 }

 // Custom painting of the DVD logo
 @Override
 protected void paintComponent(Graphics g) {
     super.paintComponent(g);

     // Draw the DVD logo
     g.setColor(logoColor);
     g.fillRect(logoX, logoY, LOGO_WIDTH, LOGO_HEIGHT);

     // Draw "DVD" text on the logo
     g.setColor(Color.WHITE);
     g.setFont(new Font("Arial", Font.BOLD, 20));
     g.drawString("DVD", logoX + 25, logoY + 30);
 }

 // Main method to run the animation
 public static void main(String[] args) {
     JFrame frame = new JFrame("DVD Bouncing Animation");
     DVDBouncingAnimation animation = new DVDBouncingAnimation();

     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(animation);
     frame.pack();
     frame.setLocationRelativeTo(null); // Center the window
     frame.setVisible(true);
 }
}
