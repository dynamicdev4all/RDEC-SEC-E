import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Flappy Bird"); // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        setSize(800, 600); // Define the dimensions of the frame
        setResizable(false); // Prevent resizing to avoid graphical issues

        // Add the game panel
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        // Start the game by creating the GameFrame
        new GameFrame();
    }
}
